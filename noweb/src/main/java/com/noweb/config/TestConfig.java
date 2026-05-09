package com.noweb.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:application.properties")
public class TestConfig {
    @Value("${test.a}")
    String a;
    @Value("${test.b:123}")
    String b;

//    private String a;
//    private String b;
}
