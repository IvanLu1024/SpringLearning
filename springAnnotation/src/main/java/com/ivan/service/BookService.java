package com.ivan.service;

import com.ivan.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class BookService {


    @Autowired(required = false)
    private BookDao bookDao2;

    public String sayHello(String name){
        return "Hello"+name;
    }

    public void print(){
        System.out.println("BookService:---"+bookDao2);

    }



    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao2 +
                '}';
    }
}
