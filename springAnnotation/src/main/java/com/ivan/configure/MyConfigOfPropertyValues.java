package com.ivan.configure;

import com.ivan.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//使用@PropertySource读取外部配置文件中的k/v保存到运行的环境变量中
@PropertySource(value = {"classpath:/person.properties"})
@Configuration
public class MyConfigOfPropertyValues {

    @Bean
    public Person person(){

        return new Person("赵黎",18);
    }
}
