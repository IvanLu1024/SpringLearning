package com.ivan.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener {

    // TODO: 2018/7/23  当容器中发布此事件以后，该方法会被触发
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("收到事件："+event);
        
    }
}
