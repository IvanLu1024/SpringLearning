package com.ivan.configure;

import org.springframework.context.annotation.Configuration;

/**
 * Profile:
 *          Spring为我们提供的可以根据当前环境，动态地激活和切换一系列组件的功能：
 *
 *
 *  开发环境、测试环境、生产环境
 *  数据源：
 *
 * @Profile:指定组件在哪个环境的情况下才能被注册到容器中，不指定任何环境下都能注册这个组件
 *  (1)家里环境表示的bean，只要这个环境被激活才能注册到容器中，默认是default
 *  (2)写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 *  (3)没有标注环境标记的bean在任何环境下都是加载的；
 *
 */

@Configuration
public class MyConfigOfProfile {
}
