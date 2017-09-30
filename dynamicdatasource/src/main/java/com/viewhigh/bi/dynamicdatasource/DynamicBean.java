package com.viewhigh.bi.dynamicdatasource;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

/**
 * Created by zzq on 2017/6/19.
 */
public class DynamicBean {
    /**
     * 实体Object
     */

    private Object object = null;

    /**
     * 属性map
     */

    private BeanMap beanMap = null;

    @SuppressWarnings("unchecked")
    public DynamicBean(Map propertyMap) {

        this.object = generateBean(propertyMap);//将map转换成beanMap对象


        this.beanMap = BeanMap.create(this.object);

    }

    /**
     * 给bean属性赋值      * @param property 属性名      * @param value 值
     */

    public void setValue(String property, Object value)

    {

        beanMap.put(property, value);

    }

    /**
     * 通过属性名得到属性值      * @param property 属性名      * @return 值
     */

    public Object getValue(String property)

    {

        return beanMap.get(property);

    }

    /**
     * 得到该实体bean对象      * @return
     */

    public Object getObject()

    {

        return this.object;

    }

    /**
     * @param propertyMap * @return
     */

    @SuppressWarnings("unchecked")

    private Object generateBean(Map propertyMap) {

        BeanGenerator generator = new BeanGenerator();

        generator.addProperties(generator, propertyMap);    // 简写的方式

        return generator.create();

    }
}
