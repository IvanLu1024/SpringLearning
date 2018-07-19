package com.ivan.configure;

import com.ivan.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MainConfig2 {


    /**
     * @Scope 情况说明：
     *
     * SCOPE_PROTOTYPE：多实例的,IOC容器启动并不会去调用方法创建对象放在容器中，
     *                  每次获取的时候才会调用方法调用对象，并且每次调用均会执行一次
     *
     * SCOPE_SINGLETON：单实例的（默认值）,IOC容器启动会调用方法创建放到IOC容器中
     *                  以后每次获取就是直接从容器中拿，即从map中get()
     *
     *      懒加载：
     *          针对单实例bean：默认在容器启动的时候创建对象；
     *          懒加载：容器启动不创建对象，第一次使用（获取）Bean创建对象，并进行初始化
     * ---------------下面使用较少
     * request:同义词请求创建一个实例
     * session：同一个session创建一个实例
     *
     */
    //TODO 默认都是单实例的
    @Scope("prototype")
    @Bean("person")
    public Person person(){
        return new Person("Kobe",40);
    }


    /**
     * @Conditional，按照一定的条件进行判断，满足条件给容器中注册bean
     *
     */

    @Bean("kobe")
    public Person person01(){


        return new Person("Kobe Byrant",39);
    }

    @Bean("linus")
    public Person person02(){

        return new Person("Linus",48);
    }


}
