package com.viewhigh.bi.dynamicdatasource;

import com.viewhigh.bi.common.utils.FileUtil;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by zzq on 2017/6/14.
 * 负责初始化数据源配置
 */
public class DataSourceRegister<T> implements EnvironmentAware, ImportBeanDefinitionRegistrar {
    private javax.sql.DataSource defaultTargetDataSource;
    static final String MAINDATASOURCE = "mainDataSource";

    public final void setEnvironment(Environment environment) {
        DruidEntity druidEntity = FileUtil.readYmlByClassPath("db_info", DruidEntity.class);

        defaultTargetDataSource = DataSourceUtil.createMainDataSource(druidEntity);
    }

    public final void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        // 0.将主数据源添加到数据源集合中
        DataSourceSet.putTargetDataSourcesMap(MAINDATASOURCE, defaultTargetDataSource);
        //1.创建DataSourceBean
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        //spring名称约定为defaultTargetDataSource和targetDataSources
        mpv.addPropertyValue("defaultTargetDataSource", defaultTargetDataSource);
        mpv.addPropertyValue("targetDataSources", DataSourceSet.getTargetDataSourcesMap());
        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);
    }
}