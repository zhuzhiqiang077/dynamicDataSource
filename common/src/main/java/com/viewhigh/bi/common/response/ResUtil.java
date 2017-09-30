package com.viewhigh.bi.common.response;

/**
 * Created by zzq on 2017/4/18.
 */
public class ResUtil {
    /**
     * 操作成功
     *
     * @param data 所携带的响应信息
     * @return
     */
    public static GlobalResult success(Object data) {
        GlobalResult globalResult = new GlobalResult();
        ResType resType = ResType.Success;
        globalResult.setCode(resType.getCode());
        globalResult.setData(data);
        globalResult.setMsg(resType.getMsg());
        return globalResult;
    }

    /**
     * 操作成功不携带任何相应信息
     *
     * @return
     */
    public static GlobalResult success() {
        GlobalResult globalResult = new GlobalResult();
        ResType resType = ResType.Success;
        globalResult.setCode(resType.getCode());
        globalResult.setData(null);
        globalResult.setMsg(resType.getMsg());
        return globalResult;
    }

    /**
     * 已知异常
     *
     * @param code
     * @param msg
     * @return
     */
    public static GlobalResult error(int code, String msg) {
        GlobalResult globalResult = new GlobalResult();
        globalResult.setCode(code);
        globalResult.setData(null);
        globalResult.setMsg(msg);
        return globalResult;
    }

    /**
     * 未知异常（程序本身抛错）
     *
     * @param unknownException
     * @return
     */
    public static GlobalResult error(String unknownException) {
        GlobalResult globalResult = new GlobalResult();
        globalResult.setCode(99999);
        globalResult.setData(null);
        if (unknownException == null || unknownException.trim().equals(""))
            unknownException = "未知错误，请查阅log";
        globalResult.setMsg(unknownException);
        return globalResult;
    }
}