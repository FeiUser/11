package noweb.main;

import noweb.config.TestConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "noweb") // 指定要扫描的包路径
public class TestMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestMain.class, args);
        TestConfig bean = context.getBean(TestConfig.class);
        System.out.println(bean.getA());
    }
}