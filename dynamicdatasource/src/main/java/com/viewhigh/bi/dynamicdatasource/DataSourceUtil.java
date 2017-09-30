package com.viewhigh.bi.dynamicdatasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.viewhigh.bi.common.global.App;
import com.viewhigh.bi.common.log.LogUtil;
import com.viewhigh.bi.common.response.CustomException;
import com.viewhigh.bi.common.response.ResType;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by zzq on 2017/6/14.
 */
public class DataSourceUtil {
    static final String DATASOURCEGETSTRATEGY = "dataSourceGetStrategy";
    private static final ThreadLocal<String> importTmpDataSourceKey = new ThreadLocal();
    private static DataSourceGetStrategy dataSourceGetStrategy;

    static final void setDataSource2Context(String keyDataSource, DataSource importTmpDataSource) {
        //0.通过DataSourceGetStrategy策略创建数据源信息的code字段查找对应数据源的信息
        DataSource newDataSource;
        if (importTmpDataSource == null)
            newDataSource = loadDataSource(keyDataSource);
        else {
            newDataSource = importTmpDataSource;
            importTmpDataSourceKey.set(keyDataSource);//存储临时数据源，使用结束后回收
        }
        //1.检查数据源是否合法
        newDataSource = checkDataSource(newDataSource);
        //2.存储新的数据源到数据源集合中
        DataSourceSet.putTargetDataSourcesMap(keyDataSource, newDataSource);
        //3.同步数据源
        syncDataSource();
        //4.数据源创建完成后立即使用
        DataSourceSet.setCurrDataSource(keyDataSource);
    }

    private static void syncDataSource() {
        //1.获得已经注册的dataSourceBean并重新设置包含数据源的map
        com.viewhigh.bi.dynamicdatasource.DataSource dataSource = (com.viewhigh.bi.dynamicdatasource.DataSource) App.getContext().getBean("dataSource");
        //2.将DataSourcesMap设置回数据源
        dataSource.setTargetDataSources(DataSourceSet.getTargetDataSourcesMap());
        //3.确保后添加到targetDataSources的数据源同步到resolvedDataSources;
        dataSource.afterPropertiesSet();
    }

    /**
     * 从抽象类bean中获取数据源
     *
     * @param keyDataSource
     * @return
     */
    private static DataSource loadDataSource(String keyDataSource) {
        if (dataSourceGetStrategy == null) {
            synchronized (DataSourceUtil.class) {
                if (dataSourceGetStrategy == null) {
                    if (!App.getContext().containsBeanDefinition(DATASOURCEGETSTRATEGY))
                        throw new CustomException(ResType.OverrideGetDataSourceInfo);
                    dataSourceGetStrategy = (DataSourceGetStrategy) App.getContext().getBean(DATASOURCEGETSTRATEGY);
                }
            }
        }
        return dataSourceGetStrategy.getDataSource(keyDataSource);
    }

    /**
     * 检查DataSource是否合法
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    private static DataSource checkDataSource(DataSource dataSource) {
        if (dataSource == null)
            throw new CustomException(ResType.GetDataSourceInfoGResultNull);

        long maxWait = -1l;
        try {//建立数据源之后检查是否设置了最大的等待时间
            maxWait = Long.valueOf(dataSource.getClass().getMethod("getMaxWait").invoke(dataSource).toString());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.info("该连接池没有实现getMaxWait方法，不能获取最大的等待的超时时间");
            throw new CustomException(ResType._getMaxWai_MethodUndefined);
        }
        if (maxWait <= 0)
            throw new CustomException(ResType.MustSetWaitTime);

        return dataSource;
    }

    /**
     * 连接测试
     * 连接不上则抛出异常
     *
     * @return
     * @throws SQLException
     */
    public final static boolean testConnection(String keyDataSource) {
        try {
            loadDataSource(keyDataSource).getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResType.DataBaseConnectionError);
        }
        return true;
    }

    /**
     * 激活默认数据源 （mainDataSource）
     *
     * @throws Exception
     */
    public static final void activateDataSource() {
        activateDataSource(DataSourceRegister.MAINDATASOURCE, null);
    }

    /**
     * 激活数据源
     *
     * @param keyDataSource
     * @throws Exception
     */
    public static final void activateDataSource(String keyDataSource, DataSource importTmpDataSource) {
        if (!DataSourceSet.containsDataSource(keyDataSource)) {
            if (importTmpDataSource != null)
                LogUtil.info("即将创建临时数据源[{}]", keyDataSource);
            LogUtil.info("正在尝试创建数据源[{}] ", keyDataSource);
            setDataSource2Context(keyDataSource, importTmpDataSource);
            LogUtil.info("数据源[{}]创建成功", keyDataSource);
        } else {
            LogUtil.info("[{}]数据源被激活", keyDataSource);
            DataSourceSet.setCurrDataSource(keyDataSource);
        }
    }

    /**
     * 重置为默认数据源
     */
    public static void resetDataSource() {
        resetDataSource(null);
    }

    /**
     * 重置为默认数据源
     *
     * @param keyDataSource
     */
    public static void resetDataSource(String keyDataSource) {
        if (keyDataSource != null)
            LogUtil.info("数据源[{}]使用完毕，重置为默认数据源", keyDataSource);
        else
            LogUtil.info("已经重置为默认数据源");
        DataSourceSet.clearCurrDataSource();
        //重置数据源时，如果是临时数据源则从dataSource上下文中清除
        if (keyDataSource != null && keyDataSource.equals(importTmpDataSourceKey.get())) {
            DataSourceSet.removeTargetDataSourcesMap(keyDataSource);
            syncDataSource();
            LogUtil.info("临时数据源[{}]从dataSourceBean中清除完成", keyDataSource);
        }
    }

    /**
     * 创建主数据源
     *
     * @param druidEntity
     * @return
     */
    static DataSource createMainDataSource(DruidEntity druidEntity) {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(druidEntity.getDbUrl());
        datasource.setUsername(druidEntity.getUsername());
        datasource.setPassword(druidEntity.getPassword());
        datasource.setDriverClassName(druidEntity.getDriverClassName());

        //configuration
        datasource.setInitialSize(druidEntity.getInitialSize());
        datasource.setMinIdle(druidEntity.getMinIdle());
        datasource.setMaxActive(druidEntity.getMaxActive());
        datasource.setMaxWait(druidEntity.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(druidEntity.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(druidEntity.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(druidEntity.getValidationQuery());
        datasource.setTestWhileIdle(druidEntity.isTestWhileIdle());
        datasource.setTestOnBorrow(druidEntity.isTestOnBorrow());
        datasource.setTestOnReturn(druidEntity.isTestOnReturn());
        datasource.setPoolPreparedStatements(druidEntity.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(druidEntity.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(druidEntity.getFilters());
        } catch (SQLException e) {
            LogUtil.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(druidEntity.getConnectionProperties());
        LogUtil.info("默认数据源已创建");
        return datasource;
    }
}