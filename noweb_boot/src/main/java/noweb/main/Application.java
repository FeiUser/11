package noweb.main;//package com.noweb.main;

import noweb.config.TestConfig;
import noweb.service.TestB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "noweb") // 指定要扫描的包路径
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        TestConfig bean1 = context.getBean(TestConfig.class);
        System.out.println(bean1.getA());

        TestB bean2 = context.getBean(TestB.class);
        bean2.test3();

        System.out.println(bean1.getB());
    }
}