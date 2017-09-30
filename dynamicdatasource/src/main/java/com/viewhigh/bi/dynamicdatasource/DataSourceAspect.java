package com.viewhigh.bi.dynamicdatasource;

import com.viewhigh.bi.common.log.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by zzq on 2017/6/14.
 */
@Aspect
@Order(-88)// AOP在@Transactional之前执行，否则会事物执行失败
@Component
public class DataSourceAspect {
    @Before("@annotation(ads)")
    public void activateDataSource(JoinPoint point, ActivateDataSource ads) throws Throwable {
        String keyDataSource = ads.value();
        if (!process(keyDataSource, point))
            return;
        LogUtil.info("method：{} ", point.getSignature().getName());
        DataSourceUtil.activateDataSource(keyDataSource, null);
    }

    @After("@annotation(ads)")
    public void resetDataSource(JoinPoint point, ActivateDataSource ads) {
        String keyDataSource = ads.value();
        if (!process(keyDataSource, point))
            return;
        LogUtil.info("method：{} ", point.getSignature().getName());
        DataSourceUtil.resetDataSource(keyDataSource);
    }

    private boolean process(String keyDataSource, JoinPoint point) {
        if (keyDataSource == null) {
            LogUtil.info("数据源注解已经标识，但value为null[{}]", point.getSignature().getName());
            return false;
        }
        if (keyDataSource.equals(DataSourceRegister.MAINDATASOURCE)) return false;
        return true;
    }
}