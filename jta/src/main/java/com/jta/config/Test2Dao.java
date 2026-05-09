package com.jta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryTest2",
        transactionManagerRef="transactionManagerTest2",//事务工厂
        basePackages= {"com.jta.entity.mysql.test2"}) //设置Repository所在位置
public class Test2Dao {
    @Autowired
    @Qualifier("test2DataSource")
    private DataSource test2DataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "entityManagerTest2")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryUser(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryTest2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryUser (EntityManagerFactoryBuilder builder) {
        return builder.
                dataSource(test2DataSource)
//                .properties(getVendorProperties())
                .packages("com.jta.entity.mysql.test2") //设置实体类所在位置
                .persistenceUnit("test2PersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerTest2")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryUser(builder).getObject());
    }
}
