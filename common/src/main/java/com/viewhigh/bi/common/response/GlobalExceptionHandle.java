package com.viewhigh.bi.common.response;

import com.viewhigh.bi.common.log.LogUtil;
import com.viewhigh.bi.common.utils.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by zzq on 2017/4/18.
 */
@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public GlobalResult handle(Exception e) {
        globalProcessException(e);//异常日志
        if (e instanceof CustomException) {
            return ResUtil.error(((CustomException) e).getResCode(), e.getMessage());
        }
        String error;//异常msg寻找两次
//        if (org.apache.commons.lang3.StringUtils.isBlank(e.getMessage()))
//            error = e.getCause().getMessage();
//        else
        error = e.getMessage();

        return ResUtil.error(error);
    }

    private void globalProcessException(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException _e) {
                    _e.printStackTrace();//过程中如果出错直接打印堆栈
                }
            }
            if (pw != null) {
                pw.close();
            }
        }

        LogUtil.info("\n-----开始-----\n*****异常堆栈*****：\n{}\n{}\n-----结束-----", StringUtils.getCurrDateStr(), sw.toString());
    }
}