package com.ivan.configure;

import com.ivan.aop.LogAspects;
import com.ivan.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP:
 *      指在程序运行期间动态的将某段代码切入到指定方法的指定位置进行运行的编程方式
 *
 *
 *  1.导入AOP模块：Spring AOP(Spring-aspects)
 *  2.定义一个业务逻辑类：在业务逻辑运行的时候讲日志进行打印（方法之前、方法运行结束、方法出现异常等）
 *  3.定义一个日志切面类：切面类的方法需要动态感知方法运行到哪
 *          通知方法：
 *              前置通知(@Before)：logStart
 *              后置通知(@After)：logEnd
 *              返回通知(@AfterReturning)：logReturn
 *              异常通知(@AfterThrowing):logException
 *              环绕通知(@Around):动态代理，手动推进目标方法运行
 *  4.给切面类的目标方法标注何时何地运行
 * *5.将切面类和业务逻辑类（目标方法所在类）都加入到容器中;
 *  6.必须告诉Spring哪个类是切面类（给切面类上加注解）
 *  [7].给配置类中加@EnableAspectJAutoProxy（开启基于注解的aop模式）
 *          在Spring中有很多的@EnableXXX
 *
 *  三步：
 *      （1）将业务逻辑组件和切面类都加入到容器中：告诉Spring哪个是切面类
 *      （2）在切面类上的每一个通知方法上标注通知注解，告诉Spirng何时何地运行
 *      （3）开启基于注解的AOP模式
 *
 *   AOP原理:[看给容器中注册什么组件，这个组件什么时候工作，这个组件工作时候的功能是什么？]
 *          @EnableAspectJAutoProxy
 *   1.@EnableAspectJAutoProxy是什么？
 *      @Import(AspectJAutoProxyRegistrar.class)：给容器中导入AspectJAutoProxyRegistrar
 *              利用AspectJAutoProxyRegistrar自定义给容器中注册bean;
 *              internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 *
 *      作用：给容器中注册一个AnnotationAwareAspectJAutoProxyCreator（注解驱动的切面自动代理创建器）;
 *
 *   2.AnnotationAwareAspectJAutoProxyCreator：
 *      AnnotationAwareAspectJAutoProxyCreator
 *          ->AspectJAwareAdvisorAutoProxyCreator
 *              ->AbstractAdvisorAutoProxyCreator
 *                      implements SmartInstantiationAwareBeanPostProcessor(Bean的后置处理器),
 *                                  BeanFactoryAware（）
 *
 *                 关注后置处理器（bean初始化完成前后做的事情）、自动装配BeanFactoryAware
 *
 * 可以打上断点的方法：
 *      AbstractAutoProxyCreator.setBeanFactory()
 *      AbstractAutoProxyCreator.有后置处理器的逻辑；
 *
 *          AbstractAdvisorAutoProxyCreator.setBeanFactory()-》initBeanFactory()
 *
 *              AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 *
 *  流程：
 *  *      1.传入配置类，创建ioc容器
 *  *      2.注册配置类，调用refresh()方法刷新容器；
 *  *      3.registerBeanPostProcessors(beanFactory);注册bean的后置处理器来方便拦截bean的创建；
 *  *          1）先获取ioc容器已经定义了的需要创建对象的所有BeanPostProcessor(@EnableAspectJAutoProxy为容器中注册的后置处理器)
 *  *          2）给容器中加别的PostProcessor
 *  *          3）优先注册实现了PriorityOrdered接口的BeanPostProcessor;
 *  *          4）再给容器中注册实现了Ordered接口的BeanPostProcessor；
 *  *          5）注册没实现优先级接口的BeanPostProcessor；
 *  *          6）注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中
 *  *              创建internalAutoProxyCreator的BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]
 *                  (1)创建Bean实例
 *                  (2)populateBean：给bean的各种属性赋值
 *                  (3)initializeBean:初始化bean
 *                      1.invokeAwareMethods():处理Aware接口的方法回调
 *                      2.applyBeanPostProcessorBeforeInitialization():应用后置处理器
 *                      3.invokeInitMethods():执行自定义的初始化方法
 *                      4.applyBeanPostProcessorsAfterInitialization():执行后置处理器的postProcessAfterInitialization();
 *                  (4)BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]创建成功：-->aspectJAdvisorsBuilder()
 *
 *              7）把BeanPostProcessor注册到BeanFactory中：
 *                  BeanFactory、addBeanPostProcessor(postProcessor);
 *
 *==========================以上的创建和注册AnnotationAwareAspectJAutoProxyCreator的过程=====================================
 *
 *              AnnotationAwareAspectJAutoProxyCreator => InstantiationAwareBeanPostProcessor
 *       4.finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作，创建剩下的单实例对象
 *              1.遍历获取容器中所有的Bean，依次创建对象getBean(beanName);
 *                  getBean()->doGetBean()->getSingleton()->
 *              2.创建bean
 *                  [AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前就会有一个拦截：InstantiationAwareBeanPostProcessor:会调用postProcessorsBeforeInstantiation]
 *                  1）先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用；否则再创建
 *                      只要是被创建好的Bean都会被缓存起来
 *                  2) createBean():创建bean：
 *                      [BeanPostProcessor是在Bean是在创建Bean实例之前就先尝试用后置处理器返回对象的]
 *                      1.resolveBeforeInstantiation(beanName,mbd):解析BeforeInstantiation
 *                          希望后置处理器在此返回一个代理对象；如果能返回代理对象就使用，如果不能就继续
 *                          （1）后置处理器先尝试返回对象：AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先尝试返回
 *                              bean=applyBeanPostProcessorsBeforeInstantiation()
 *                                  拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor，
 *                                  就执行postProcessorsBeforeInstantiation
 *
 *                      2.doCreateBean():真正地去创建一个bean实例；和3.6流程类似
 *
 *                      3.
 *
 *
 *
 *  AnnotationAwareAspectJAutoProxyCreator[InstantiationAwareBeanPostProcessor]的作用：
 *  1）每一个bean创建之前，调用postProcessorsBeforeInstantiation()
 *      关心MathCalcuator和LogAspect的创建
 *      1）判断当前bean是否在advisedBeans中（保存了所有需要增强的bean）
 *      2）判断当前bean是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean
 *          或者是否为切面（@Aspect）isAspect
 *      3)是否需要跳过
 *          1.获取候选的增强器的集合（切面里面的通知方法）[]
 *              每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor;
 *              判断每一个增强器是否是AspectJPointcutAdvisor类型的：返回true
 *          2.永远返回false
 *   2）创建对象
 *      在postProcessorsAfterInitialization:
 *  *                          return warpIfNecessary；包装如果需要的情况下
 *  *                              （1）获取当前的bean的所有增强器（通知方法）
 *  *                                   1.找到候选的所有的增强器(找那些通知方法是需要切入当前bean方法的)
 *  *                                   2.获取到能在bean使用的增强器
 *  *                                   3.给增强器排序
 *  *                              （2）保存当前bean在advisedBeans中
 *  *                              （3）如果当前bean需要增强，创建当前bean的代理对象
 *  *                                   1.获取所有增强器（通知方法）
 *  *                                   2.保存到proxyFactory
 *  *                                   3.创建代理对象:Spring自动决定
 *  *  *                                     JdkDynamicAopProxy（config）：jdk的动态代理
 *  *  *                                     ObjenesisCglibAopProxy(config):cglib的动态代理
 *  *  *                            4)给容器中返回当前组件使用cglib增强了的代理对象
 *  *                               5)以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程：
 *  *
 *   3）、目标方法执行	；
 *  * 		容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象，xxx）；
 *  * 		1）、CglibAopProxy.intercept();拦截目标方法的执行
 *  * 		2）、根据ProxyFactory对象获取将要执行的目标方法拦截器链；
 *  * 			List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *  * 			1）、List<Object> interceptorList：保存所有拦截器 长度为5
 *  * 				包括一个默认的ExposeInvocationInterceptor 和 4个增强器；
 *  * 			2）、遍历所有的增强器，将其转为Interceptor；
 *  * 				registry.getInterceptors(advisor);
 *  * 			3）、将增强器转为List<MethodInterceptor>；
 *  * 				如果是MethodInterceptor，直接加入到集合中
 *  * 				如果不是，使用AdvisorAdapter（增强器的适配器）将增强器转为MethodInterceptor；
 *  * 				转换完成返回MethodInterceptor数组；
 *  *
 *  * 		3）、如果没有拦截器链，直接执行目标方法;
 *  * 			*拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 *  * 		4）、如果有拦截器链，把需要执行的目标对象，目标方法，
 *  * 			拦截器链等信息传入创建一个 CglibMethodInvocation 对象，
 *  * 			并调用 Object retVal =  mi.proceed();
 *  * 		5）、拦截器链的触发过程;
 *  * 			1)、如果没有拦截器执行执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（执行到了最后一个拦截器）执行目标方法；
 *  * 			2)、链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行；
 *  * 				拦截器链的机制，保证通知方法与目标方法的执行顺序；
 *
 *      总结：
 *          1.@EnableAspectJAutoProxy 开启AOP功能
 *          2.@EnableAspectJAutoProxy 会给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator
 *          3.AnnotationAwareAspectJAutoProxyCreator是一个后置处理器；
 *          4.容器的创建流程：
 *              1）、registerBeanPostProcessors（）注册后置处理器；创建AnnotationAwareAspectJAutoProxyCreator对象
 *  * 			2）、finishBeanFactoryInitialization（）初始化剩下的单实例bean
 *  * 				1）、创建业务逻辑组件和切面组件
 *  * 				2）、AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 *  * 				3）、组件创建完之后，判断组件是否需要增强（包装）
 *  * 					是：切面的通知方法，包装成增强器（Advisor）;给业务逻辑组件创建一个代理对象（cglib）；
 *  * 		5.执行目标方法：
 *  * 			1）、代理对象执行目标方法
 *  * 			2）、CglibAopProxy.intercept()；进行拦截
 *                  拦截过程：
 *  * 				1）、得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
 *  * 				2）、利用拦截器的链式机制，依次进入每一个拦截器进行执行；
 *  * 				3）、效果：
 *  * 					正常执行：前置通知-》目标方法-》后置通知-》返回通知
 *  * 					出现异常：前置通知-》目标方法-》后置通知-》异常通知
 *
 */
//开启基于注解的aop模式
@EnableAspectJAutoProxy
@Configuration
public class MyConfigOfAOP {

    //将业务逻辑类加入容器中
    @Bean
    public MathCalculator calculator(){
        return new MathCalculator();
    }

    //将切面类加入容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

}
