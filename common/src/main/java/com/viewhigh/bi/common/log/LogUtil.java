package com.viewhigh.bi.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zzq on 2017/4/19.
 */
public class LogUtil {
    private static Logger _logger = LoggerFactory.getLogger("---bi--- info ");
    private static boolean _enable = false;

    public static void setEnabled(boolean enable) {
        _enable = enable;
    }

    public static void info(String msg) {
        if (_enable)
            _logger.info(msg);
    }

    public static void info(String msg, Throwable t) {
        if (_enable)
            _logger.info(msg, t);
    }

    public static void info(String msg, Object... objects) {
        if (_enable)
            _logger.info(msg, objects);
    }


    public static void warn(String msg, Throwable t) {
        if (_enable)
            _logger.warn(msg, t);
    }

    public static void warn(String msg) {
        if (_enable)
            _logger.warn(msg);
    }

    public static void warn(String msg, Object... objects) {
        if (_enable)
            _logger.warn(msg, objects);
    }

    public static void error(String msg, Throwable t) {
        if (_enable)
            _logger.error(msg, t);
    }

    public static void error(String msg) {
        if (_enable)
            _logger.error(msg);
    }

    public static void error(String msg, Object... objects) {
        if (_enable)
            _logger.error(msg, objects);
    }

    public static void debug(String msg, Throwable t) {
        if (_enable)
            _logger.debug(msg, t);
    }

    public static void debug(String msg) {
        if (_enable)
            _logger.debug(msg);
    }

    public static void debug(String msg, Object... objects) {
        if (_enable)
            _logger.debug(msg, objects);
    }
}