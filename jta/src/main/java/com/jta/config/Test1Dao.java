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
        entityManagerFactoryRef="entityManagerFactoryTest1",
        transactionManagerRef="transactionManagerTest1",//事务工厂
        basePackages= {"com.jta.entity.mysql.test1"}) //设置Repository所在位置
public class Test1Dao {
    @Autowired
    @Qualifier("test1DataSource")
    private DataSource test1DataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "entityManagerTest1")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryUser(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryTest1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryUser (EntityManagerFactoryBuilder builder) {
        return builder.
                dataSource(test1DataSource)
//                .properties(getVendorProperties())
                .packages("com.jta.entity.mysql.test1") //设置实体类所在位置
                .persistenceUnit("test1PersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerTest1")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryUser(builder).getObject());
    }
}
