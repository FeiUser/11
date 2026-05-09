package noweb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("test")
public class TestConfig {
//    @Value("${test.a}")
//    String a;
//    @Value("${test.b:123}")
//    String b;

    private String a;
    private String b;
}
