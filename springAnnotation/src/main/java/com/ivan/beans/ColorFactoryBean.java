package com.ivan.beans;

import org.springframework.beans.factory.FactoryBean;

/**
 * 创建Spring定义的FactoryBean
 */
public class ColorFactoryBean implements FactoryBean {

    //返回一个Color对象
    public Object getObject() throws Exception {
        System.out.println("ColorFactoryBean ------- Color");
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }


    //是否单例？true：单例，在容器中保存一份，false，则反之
    public boolean isSingleton() {

        return true;
    }
}
