package com.ivan.tx;


import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 声明式事务：
 *  *
 *  * 环境搭建：
 *  * 1、导入相关依赖
 *  * 		数据源、数据库驱动、Spring-jdbc模块
 *  * 2、配置数据源、JdbcTemplate（Spring提供的简化数据库操作的工具）操作数据
 *  * 3、给方法上标注 @Transactional 表示当前方法是一个事务方法；
 *  * 4、 @EnableTransactionManagement 开启基于注解的事务管理功能；
 *  * 		@EnableXXX
 *  * 5、配置事务管理器来控制事务;
 *  * 		@Bean
 *  * 		public PlatformTransactionManager transactionManager()
 *  *
 *  *
 *  * 原理：
 *  * 1）、@EnableTransactionManagement
 *  * 			利用TransactionManagementConfigurationSelector为容器中会导入两种组件：
 *  * 			    AutoProxyRegistrar
 *  * 			    ProxyTransactionManagementConfiguration
 *
 *  * 2）、AutoProxyRegistrar：
 *  * 			给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件；
 *  * 			InfrastructureAdvisorAutoProxyCreator：是什么？
 *                  Auto-proxy creator that considers infrastructure Advisor beans only,
 *                      ignoring any application-defined Advisors.
 *                  (只考虑基础增强器实例的自动代理创建器，忽略任何应用程序定义的增强器)
 *  * 			    利用后置处理器机制,在对象创建以后，包装对象，返回一个代理对象（增强器），
 *                      代理对象执行方法利用拦截器链进行调用；与AOP过程类似
 *  *
 *  * 3）、ProxyTransactionManagementConfiguration 做了什么？
 *  * 			给容器中注册事务增强器；设置以下内容：
 *  * 				1）、事务增强器要用事务注解的信息：advisor.setTransactionAttributeSource(transactionAttributeSource())
 *                          AnnotationTransactionAttributeSource解析事务注解
 *
 *  * 				2）、事务拦截器：
 *                      advisor.setAdvice(transactionInterceptor());
 *  * 					TransactionInterceptor；保存了事务属性信息，事务管理器；
 *  * 					这是一个 MethodInterceptor（方法拦截器）；
 *  * 					在目标方法执行的时候；
 *  * 						执行拦截器链；
 *  * 						事务拦截器：
 *  * 							1）、先获取事务相关的属性
 *  * 							2）、再获取PlatformTransactionManager，如果事先没有添加指定任何transactionmanger
 *  * 								最终会从容器中按照类型获取一个PlatformTransactionManager；
 *  * 							3）、执行目标方法
 *  * 								如果异常，获取到事务管理器，利用事务管理回滚操作；
 *  * 								如果正常，利用事务管理器，提交事务
 *
 *                  3）、事务的次序：为增强器设置事务的次序
 *                      advisor.setOrder(this.enableTx.<Integer>getNumber("order"));
 *  *
 *
 */

@EnableTransactionManagement
@Configuration
public class TxConfig {
}
