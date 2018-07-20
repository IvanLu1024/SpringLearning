package com.ivan.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog {

    public Dog(){

        System.out.println("Dog Constructor ---");
    }

    @PostConstruct
    public void init(){

        System.out.println("Dog --- Post---constructor");
    }

    @PreDestroy
    public void destory(){

        System.out.println("Dog --- pre Destroy");
    }

}
