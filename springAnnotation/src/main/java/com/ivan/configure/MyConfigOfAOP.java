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
 */
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
