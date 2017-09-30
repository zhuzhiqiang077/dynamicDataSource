package com.viewhigh.bi.dynamicdatasource;

import java.io.Serializable;

/**
 * Created by zzq on 2017/4/18.
 *
 * content 配置
 *
 *
druid:
 # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
 driverClassName: com.mysql.jdbc.Driver
 dbUrl: jdbc:mysql://127.0.0.1:3306/bi?characterEncoding=utf8
 username: root
 password: root
 # 下面为连接池的补充设置，应用到上面所有数据源中
 # 初始化大小，最小，最大
 initialSize: 5
 minIdle: 5
 maxActive: 20
 # 配置获取连接等待超时的时间
 maxWait: 60000
 # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
 timeBetweenEvictionRunsMillis: 80000
 # 配置一个连接在池中最小生存的时间，单位是毫秒
 validationQuery: SELECT 1 FROM DUAL
 testWhileIdle: true
 testOnBorrow: false
 testOnReturn: false
 # 打开PSCache，并且指定每个连接上PSCache的大小
 poolPreparedStatements: true
 maxPoolPreparedStatementPerConnectionSize: 20
 # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
 filters: stat,wall,log4j
 # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
 connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
 # 合并多个DruidDataSource的监控数据
 #spring.datasource.useGlobalDataSourceStat=true
 */
public class DruidEntity implements Serializable {
    private String dbUrl;

    private String username;

    private String password;

    private String driverClassName;

    private int initialSize;

    private int minIdle;

    private int maxActive;


    private int maxWait;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;


    private String validationQuery;


    private boolean testWhileIdle;


    private boolean testOnBorrow;


    private boolean testOnReturn;


    private boolean poolPreparedStatements;


    private int maxPoolPreparedStatementPerConnectionSize;


    private String filters;


    private String connectionProperties;

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }
}