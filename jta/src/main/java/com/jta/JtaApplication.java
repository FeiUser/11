package com.jta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = { "com.jta", "org.nebula.contrib" })
@EnableTransactionManagement
public class JtaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JtaApplication.class, args);
    }
}
