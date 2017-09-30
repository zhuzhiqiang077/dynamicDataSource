package com.viewhigh.bi.dynamicdatasource;

import com.viewhigh.bi.common.response.CustomException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zzq on 2017/6/13.
 */
public class DataSourceSet {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal();

    private static List<String> dataSourceKeyList = new CopyOnWriteArrayList<String>();

    private static Map targetDataSourcesMap = new ConcurrentHashMap();

    public static Object putTargetDataSourcesMap(Object key, Object dataSource) {
        dataSourceKeyList.add(key.toString());
        return targetDataSourcesMap.put(key, dataSource);
    }

    public static Object removeTargetDataSourcesMap(Object key) {
        try {
            dataSourceKeyList.remove(key);
            return targetDataSourcesMap.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(00000, "移除DataSourceSet数据源信息时出现异常，可能由于dataSourceKeyList或targetDataSourcesMap没有该item项");
        }
    }

    public static Map getTargetDataSourcesMap() {
        return targetDataSourcesMap;
    }

    public static void setCurrDataSource(String ds) {
        contextHolder.set(ds);
    }

    public static String getCurrDataSource() {
        return contextHolder.get();
    }

    public static void clearCurrDataSource() {
        contextHolder.remove();
    }

    public static boolean containsDataSource(String dataSourceKey) {
        return dataSourceKeyList.contains(dataSourceKey);
    }
}
