package com.viewhigh.bi.dynamicdatasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zzq on 2017/6/14.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivateDataSource {
    String value() default DataSourceRegister.MAINDATASOURCE;
}
