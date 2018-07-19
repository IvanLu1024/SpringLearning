package com.ivan.test;

import com.ivan.configure.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * IOC容器的测试类
 */

public class MainTest01 {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String n:names){
            System.out.println(n);
        }


    }


    public void test01(){}

}
