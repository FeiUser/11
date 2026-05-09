package com.noweb.main;

import com.noweb.config.AppConfig;
import com.noweb.config.TestConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestMain2 {
    public static void main(String[] args) {
        // 创建 Spring 应用程序上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 获取需要的 Bean
        TestConfig bean = context.getBean(TestConfig.class);

        // 使用 Bean
        System.out.println(bean);

        // 关闭上下文
        context.close();
    }
}
