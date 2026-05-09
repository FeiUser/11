package org.test.mybatis.config.mysql;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = DataSourcesConfig2.BACK_PACKAGES2, sqlSessionFactoryRef = "sqlSessionFactory2")
public class DataSourcesConfig2 {
    final static String BACK_PACKAGES2 = "org.test.mybatis.mapper.ds2";

    @Bean(name = "ds2")
    @ConfigurationProperties(prefix="lee.datasource.ds2")
    public DataSource ds2DataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        return hikariDataSource;
    }

    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("ds2") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置mybatis的配置文件属性
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/ds2/*.xml"));

        // 设置mybatis.configuration属性
        // 目的是为了省略resultType里的代码量
        sqlSessionFactoryBean.setTypeAliasesPackage("org.test.mybatis.entity.ds2");
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactoryBean.getObject().getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name = "ds2TransactionManager")
    public DataSourceTransactionManager ds2TransactionManager(@Qualifier("ds2") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        dataSourceTransactionManager.setRollbackOnCommitFailure(true);
        dataSourceTransactionManager.setGlobalRollbackOnParticipationFailure(true);
        return dataSourceTransactionManager;
    }

    @Bean(name = "sqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
