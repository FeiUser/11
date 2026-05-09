package com.jta.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfigration {

    @Primary
    @Bean(name = "test1Properties")
    @Qualifier("test1Properties")
    @ConfigurationProperties(prefix="datasource.test1")
    public DataSourceProperties test1Properties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "test1DataSource")
    @Qualifier("test1DataSource")
    public DataSource test1DataSource(@Qualifier("test1Properties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    /***************************************************
     ****             test2 库数据源配置              ****
     ***************************************************/
    @Bean(name = "test2Properties")
    @Qualifier("test2Properties")
    @ConfigurationProperties(prefix="datasource.test2")
    public DataSourceProperties test2Properties() {
        return new DataSourceProperties();
    }
    @Bean(name = "test2DataSource")
    @Qualifier("test2DataSource")
    public DataSource test2DataSource(@Qualifier("test2Properties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

}
