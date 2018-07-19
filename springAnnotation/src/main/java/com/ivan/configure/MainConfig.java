package com.ivan.configure;

import com.ivan.beans.Person;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * 配置类=配置文件
 */

@Configurable   //告诉Spring这是一个配置类
@ComponentScan(value = "com.ivan",includeFilters={
        /*@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE.ANNOTATION,classes =
                {Controller.class,Service.class})*/
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})

} ,useDefaultFilters = false)
//@ComponentScan value:指定扫描的包
//excludeFilters = Filter[],指定扫描的时候按照什么规则排除那些组件
//includeFilters = Filter[],指定扫描的时候只需要包含那些组件

//FilterType.ANNOTATION,按照注解
//FilterType.ASSIGNABLE_TYPE,按照给定的类型
//FilterType.CUSTOM，使用自定义规则


public class MainConfig {

    //给容器中注册一个Bean；类型为返回值类型，id默认是用方法名
    @Bean(value ="person")
    public Person person(){
        return new Person("Ivan",22);

    }

}
