package com.viewhigh.bi.dynamicdatasource;

import com.viewhigh.bi.common.log.LogUtil;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by zzq on 2017/6/13.
 */
public class DataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String keyDataSource = DataSourceSet.getCurrDataSource();
        LogUtil.info("***当前数据源为[{}]", keyDataSource == null ? "默认数据源" : keyDataSource);
        return keyDataSource;
    }
}
