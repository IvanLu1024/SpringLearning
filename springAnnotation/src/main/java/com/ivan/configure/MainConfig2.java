package com.ivan.configure;

import com.ivan.beans.Color;
import com.ivan.beans.ColorFactoryBean;
import com.ivan.beans.Person;
import com.ivan.conditions.LinuxCondition;
import com.ivan.conditions.MyImportBeanDefinitionRegistrar;
import com.ivan.conditions.MyImportSelector;
import com.ivan.conditions.WindowsCondition;
import org.springframework.context.annotation.*;

@Configuration
@Import({Color.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
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
     * @Conditional（{Condition}），按照一定的条件进行判断，满足条件给容器中注册bean
     *
     * 需求：如果系统为Windows，给容器中注册Bill
     *      如果系统为Linux，给容器中注册Linus
     *
     */


    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01(){


        return new Person("Bill Gates",39);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person02(){

        return new Person("Linus",48);
    }

    /**
     * 给容器中注册组件的方式：
     *      1.包扫描+组件组件标注注解（）
     *      2.@Bean[导入的第三方包里面的组件]
     *      3.@Import[快速给容器导入第三方包]
     *          (1)@Import(要导入到容器中的组件)：容器中就会自动注册这个组件，id默认是全类名 注：这时不能重载构造器
     *          (2)ImportSelector:需要自定义一个ImportSelector的实现类，用来返回需要导入的组件的全类名 --SpringBoot中常用
     *          (3)ImportBeanDefinitionRegistrar:手动注册bean到容器中
     *      4.使用Spring提供的FactoryBean(工厂Bean)
     *          (1)默认是获取到工厂bean调用GetObject创建的对象
     *          (2)需要获取工厂Bean本身，我们需要在id前面添加$符号
     *
     *
     *
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();


    }


}
