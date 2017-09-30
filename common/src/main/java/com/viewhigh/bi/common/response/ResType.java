package com.viewhigh.bi.common.response;

/**
 * Created by zzq on 2017/4/18.
 */
public enum ResType {
    Success(200, "成功"),

    DataSource301(301, "DataSrcTypeRepository 对象未创建~空指针异常"),
    DataSource302(302, "非正常操作数据，由于根据表主键未查询到数据或查询出多条数据"),

    ParamsIncomplete(460, "参数传递不完整"),
    OverrideGetDataSourceInfo(410, "未找到DataSourceReadStrategy类中注册的Bean；没有实现抽象类DataSourceReadStrategy的抽象方法getDataSourceInfo，在使用动态数据源时该方法用于获取动态数据源的信息"),
    GetDataSourceInfoGResultNull(420, "重写抽象类DataSourceReadStrategy的抽象方法getDataSourceInfo返回值为null，***可能由于数据源表中存储了，不存在或有问题的数据库信息***"),
    DataBaseConnectionError(430, "尝试连接数据库失败，无法创建该数据源"),
    _getMaxWai_MethodUndefined(440, "该连接池没有实现getMaxWait方法，不能获取最大等待的超时时间"),
    MustSetWaitTime(450, "必须设置数据源获取连接的超时时间，一般数据源对象会提供setMaxWait方法来设置"),

    MustSetFromTable(510, "构建sql语句时from后必须要有table"),

    TableNameCheck(511, "构建sql语句时from表名为空字符串或null");


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private int code;

    public ResType superadditionMsg(String msg) {
        this.msg += msg;
        return this;
    }

    private String msg;

    ResType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}