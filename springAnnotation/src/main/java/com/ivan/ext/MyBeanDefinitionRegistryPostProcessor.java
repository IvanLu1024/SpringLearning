package com.ivan.ext;

import com.ivan.beans.Blue;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {


    //TODO BeanDefinitionRegistry Bean定义信息的保存中心,以后beanFactory就是按照BeanDefinitionRegistry里面保存的每一个bean定义信息创建bean实例
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        System.out.println("MyBeanDefinitionRegistryPostProcessor--postProcessBeanDefinitionRegistry---bean的数量："+registry.getBeanDefinitionCount());
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Blue.class);
        registry.registerBeanDefinition("hello",rootBeanDefinition);
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor---postProcessBeanFactory---bean的数量："+beanFactory.getBeanDefinitionCount());

    }
}
