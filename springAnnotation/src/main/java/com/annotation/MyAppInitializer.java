package com.annotation;

import com.annotation.config.RootConfig;
import com.annotation.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Web容器启动的时候创建对象，调用方法来初始化容器以前前端控制器
 *
 */

public class MyAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // TODO: 2018/7/25 获取根容器的配置类(Spring的配置文件)：  父容器
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class
        };
    }

    // TODO: 2018/7/25  获取Web容器的配置类(SpringMVC配置文件)；子容器
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // TODO: 2018/7/25  获取DispatcherServlet的映射信息
    // /：拦截所有请求（包括静态资源（xxx.js,xx.png）），但不包括*.jsp
    // /*:拦截所有请求：；；连*.jsp页面都会拦截；jsp页面是Tomcat的警示牌引擎解析的
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
