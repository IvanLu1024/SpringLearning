package com.ivan.conditions;

import com.ivan.beans.Rainbow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *手工注册
     * @param importingClassMetadata：当前类的注解信息
     * @param registry：BeanDefinitionRegistry的注册类
     *
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean definition = registry.containsBeanDefinition("com.ivan.beans.Yellow");
        boolean definition1 = registry.containsBeanDefinition("com.ivan.beans.Blue");
        if (definition && definition1){

            //指定bean定义信息：类型等信息
            //
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Rainbow.class);
            //注册一个Bean，并指定bean名
            registry.registerBeanDefinition("rainBow",beanDefinition);

        }


    }
}
