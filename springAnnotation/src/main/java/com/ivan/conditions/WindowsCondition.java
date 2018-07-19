package com.ivan.conditions;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

//判断是否为Windows系统
public class WindowsCondition implements Condition {
    /**
     *
     * @param context 判断条件能使用的上下文（环境）
     * @param metadata  注释信息
     * @return
     */

    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        //TODO 判断是否为windows系统
        //1.能获取到IOC使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        //2.能获取到类加载器
        ClassLoader classLoader = context.getClassLoader();

        //3.获取当前环境信息
        Environment environment = context.getEnvironment();

        //4.获取到bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        String property = environment.getProperty("os.name");
        if (property.contains("Windows")){
            return true;
        }else {

            return false;
        }

    }
}
