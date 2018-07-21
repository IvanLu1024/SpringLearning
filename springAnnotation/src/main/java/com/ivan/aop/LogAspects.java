package com.ivan.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 *切面类
 * @Aspect:告诉Spring这是切面类
 */

@Aspect
public class LogAspects {

    //抽取公共的切入点表达式
    //1.本类引用
    //2.其他的切面引用
    @Pointcut("execution(public int com.ivan.aop.MathCalculator.*(..))")
    public void pointCut(){}

    //代表目标方法之前切入，切入点的表达式（指定在哪个方法切入）
    @Before(value = "pointCut()")
    public void logStart(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"方法运行------参数列表是:"+joinPoint.getArgs());

    }
    //
    @After("com.ivan.aop.LogAspects.pointCut()")
    public void logEnd(){
        System.out.println("除法结束----");

    }
    //JoinPoint一定要出现在参数表的第一位
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(Object result){
        System.out.println("除法正常返回----运行结果为："+result);
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println("除法异常信息------"+joinPoint.getSignature().getName());
    }

}
