package com.annotation.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//SpringMVC只扫描Controller：子容器
//useDefaultFilters = false 禁用默认的过滤规则；
@ComponentScan(value = "com.ivan",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
},useDefaultFilters = false)
public class WebConfig extends WebMvcConfigurerAdapter {

    //定制环节

    //视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {


        super.configureViewResolvers(registry);
    }
}
