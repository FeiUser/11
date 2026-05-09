package com.lyf.neo4j.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = { "com.lyf.neo4j.neo4j", "org.nebula.contrib" })
//@EnableNeo4jRepositories(basePackages = "com.lyf.neo4j.neo4j.dao")
@EnableTransactionManagement // 激活SDN隐式事务
public class Neo4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jApplication.class, args);
    }

}
