package com.viewhigh.bi.dynamicdatasource;

import org.springframework.context.annotation.Bean;

/**
 * 该抽象类必须由子类实现其抽象方法，用于负责动态数据源信息获取
 * <p>
 * Created by zzq on 2017/6/19.
 */
public abstract class DataSourceGetStrategy {
    public abstract javax.sql.DataSource getDataSource(String keyDataSource);

    @Bean(name = DataSourceUtil.DATASOURCEGETSTRATEGY)
    public DataSourceGetStrategy getDataSourceReadStrategy() {
        return this;
    }
}