package com.ivan.configure;


import com.ivan.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配：
 *      Spring利用依赖注入(DI),完成对IOC容器中各个组件的依赖关系赋值
 *
 *  1.Autowired:Spring定义的，自动注入
 *      (1)默认有限按照类型去容器中找对应的组件：applicationContext.getBean(BookDao.class)
 *      (2)如果找到了多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
 *                                  applicationContext.getBean(BookDao.class)
 *      (3)@Qualifier("bookDao"),使用@Qualifier指定需要装配的组件的id
 *      (4)自动装配默认一定要将属性赋值好，没有就会报错
 *          可以使用@Autowired(required = false);
 *      (5)@Primary,让Spring进行自动装配的时候,默认使用首选的bean
 *                  也可以继续使用@Qualifier指定需要装配的组件
 *      BookService{
 *           @Autowired
 *           private BookDao bookDao;
 *
 *      }
 *
 *  2.Spring还支持使用@Resource(JSR250)和@Inject(JSR330)[java规范的注解]
 *                @Resource：
 *                      可以和@Autowired一样实现自动装配功能，默认是按照组件名称进行装配的；
 *                      没有能支持@Primary功能没有支持@Autowired(reqirued=false)
 *
 *                @Inject:
 *                      需要导包
 *                      功能与@Autowired类似
 *  3.@Autowired：构造器，参数，方法，属性: 都是从容器中获取参数组件的值
 *                (1)标注在方法位置：@Bean+方法参数；参数从容器中获取；默认不写@Authowired效果是一样的，都能自动装配
 *                (2)标在构造器上：如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略，
 *                (3)放在参数位置
 *
 *  4.自定义组件想要使用Spirng容器底层的一些组件ApplicationContext
 *      自定义组件实现xxxAware:在创建对象的时候，会调用接口规定的方法注入相关组件：Aware;
 *      把Spring底层一些组件注入到自定义的Bean对象中；
 *      xxxAware:功能使用xxxProcessor;
 *          ApplicationContextAware==>ApplicationContextAwareProcessor
 *
 */
@Configuration
@ComponentScan({"com.ivan.service","com.ivan.controller","com.ivan.dao","com.ivan.beans"})
public class MyConfigOfAutowired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");

        return bookDao;
    }

}
