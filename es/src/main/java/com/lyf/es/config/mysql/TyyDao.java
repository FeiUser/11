package com.lyf.es.config.mysql;

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
        entityManagerFactoryRef="entityManagerFactoryTyy",
        transactionManagerRef="transactionManagerTyy",//事务工厂
        basePackages= {"com.lyf.es.model.po.tyy"}) //设置Repository所在位置
public class TyyDao {
    @Autowired
    @Qualifier("tyyDataSource")
    private DataSource tyySource;

    @Primary
    @Bean(name = "entityManagerTyy")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryTyy(builder).getObject().createEntityManager();
    }

    @Autowired
    private JpaProperties jpaProperties;

    @Bean(name = "entityManagerFactoryTyy")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTyy (EntityManagerFactoryBuilder builder) {
        return builder.
                dataSource(tyySource)
//                .properties(getVendorProperties())
                .packages("com.lyf.es.model.po.tyy") //设置实体类所在位置
                .persistenceUnit("TyyPersistenceUnit")
                .build();
    }

    @Bean(name = "transactionManagerTyy")
    @Primary
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryTyy(builder).getObject());
    }
}