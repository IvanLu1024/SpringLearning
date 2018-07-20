package com.ivan.configure;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配：
 *      Spring利用依赖注入(DI),完成对IOC容器中各个组件的依赖关系赋值
 *
 *  1.Autowired
 *
 */
@Configuration
@ComponentScan({"com.ivan.service.BookService","com.ivan.controller.BookController","com.ivan.dao.BookDao"})
public class MyConfigOfAutowired {
}
