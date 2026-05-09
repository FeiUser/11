package com.lyf.es.config.mysql;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfigration {

    /***************************************************
     ****             localhost 库数据源配置              ****
     ***************************************************/
    @Primary
    @Bean(name = "tyyProperties")
    @Qualifier("tyyProperties")
    @ConfigurationProperties(prefix="datasource.tyy")
    public DataSourceProperties tyyProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "tyyDataSource")
    @Qualifier("tyyDataSource")
    public DataSource tyyDataSource(@Qualifier("tyyProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
}