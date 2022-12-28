package com.b2c.Local.B2C.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.session.jdbc.config.annotation.SpringSessionDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = {"com.b2c.Local.B2C.securities.model","com.b2c.Local.B2C.securities.dao","com.b2c.Local.B2C.securities.service","com.b2c.Local.B2C.auths.service"}, entityManagerFactoryRef = "mySqlEntityManager"
,transactionManagerRef = "sqlTransactionManager")
public class MySqlDbConfig {

    @Value("${spring.datasource.second.hibernate.dialect}")
    private String dialect;

    @Bean
    @SpringSessionDataSource
    @ConfigurationProperties(prefix="spring.datasource.second")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(value = "mySqlEntityManager")
    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", dialect);
        return entityManagerFactoryBuilder.dataSource(secondaryDataSource()).packages("com.b2c.Local.B2C.securities.model","com.b2c.Local.B2C.securities.dao","com.b2c.Local.B2C.securities.service","com.b2c.Local.B2C.auths.service").properties(properties).build();
    }

    @Bean(name="sqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("mySqlEntityManager") EntityManagerFactory enManFact) {
        return new JpaTransactionManager(enManFact);
    }
}
