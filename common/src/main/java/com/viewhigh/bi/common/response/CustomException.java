package com.viewhigh.bi.common.response;

/**
 * Created by zzq on 2017/4/18.
 */
public class CustomException extends RuntimeException {
    public int getResCode() {
        return resCode;
    }

    //响应编号
    private int resCode;

    public CustomException(ResType resType) {
        super(resType.getMsg());
        this.resCode = resType.getCode();
    }

    public CustomException(int code, String msg) {
        super(msg);
        this.resCode = code;
    }

    public CustomException() {
        super("没有定义异常信息描述，默认的BIException");
        this.resCode = 99998;
    }
}