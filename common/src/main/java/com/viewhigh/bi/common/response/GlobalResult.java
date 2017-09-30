package com.viewhigh.bi.common.response;

import com.viewhigh.bi.common.utils.StringUtils;

/**
 * Created by zzq on 2017/4/18.
 */
public class GlobalResult<Data> {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private int code;
    private String msg;
    private Data data;

    public String getCurrDate() {
        return StringUtils.getCurrDateStr();
    }

    @Override
    public String toString() {
        return "GlobalResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", metadata=" + data +
                '}';
    }
}