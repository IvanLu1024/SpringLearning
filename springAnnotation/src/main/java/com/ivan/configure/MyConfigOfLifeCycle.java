package com.ivan.configure;

import com.ivan.beans.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean的生命周期：
 *      bean创建---初始化---销毁的过程
 *
 * 容器管理bean的生命周期
 * 我们可以自定义初始化和销毁方法；容器在bean进行当前生命周期的时候来调用我们自定义的初始化
 *
 * 流程如下：
 *
 * 构造（对象创建）
 *      单实例：在容器启动的时候创建对象
 *      多实例：在每次获取的时候创建对象
 *
 * BeanPostProcessor.postProcessBeforeInitialization
 *初始化：
 *      对象创建完成，并赋值好，调用初始化方法
 *
 * BeanPostProcessor.postProcessBeforeInitialization
 *
 *销毁：
 *      单实例：容器关闭的时候
 *      多实例：容器不会管理这个bean，容器则不会调用销毁方法
 *
 * 遍历得到容器中所有的BeanPostProcessor:挨个执行beforeInitialization
 * 一旦返回null，跳出for循环，不会执行后面的BeanPostProcessor.ProcessorsBeforeInitialization
 *
 *
 *BeanPostProcessor的原理：
 * 先赋值在初始化
 * populateBean(beanName, mbd, instanceWrapper);给bean的属性赋值的
 * initializeBean(beanName, exposedObject, mbd)
 *{applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *   invokeInitMethods(beanName, wrappedBean, mbd);初始化
 *   applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *  }
 *
 *
 *
 *
 * (1)指定初始化和销毁方法：
 *          通过@Bean注解指定init-method和destory-method
 *
 * (2)通过让Bean实现InitializingBean(定义初始化逻辑)、
 *                  DisposableBean(定义销毁逻辑) 这两个接口
 *
 * (3)通过JSR250：
 *              @PostConstruct，在bean创建完成并且属性赋值完成，来执行初始化方法
 *              @PreDestroy,在容器销毁bean之前通知我们进行清理工作
 * (4)BeanPostProcessor[interface]，bean的后置处理器：
 *      在bean初始化前后进行一些处理工作:
 *      postProcessBeforeInitialization:在初始化之前工作
 *      postProcessAfterInitialization:在初始化之后工作
 *
 * Spring底层BeanPostProcessor的使用：
 *      bean的赋值，注入其他组件，@Autowired，生命周期注解功能，@Async ...
 *
 */

@ComponentScan("com.ivan.beans")
@Configuration
public class MyConfigOfLifeCycle {

    @Bean(initMethod = "init",destroyMethod = "destory")
    public Car car(){
        return new Car();
    }

}
