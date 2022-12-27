package com.b2c.Local.B2C.securities.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.b2c.Local.B2C.websocket.buffermessage","com.b2c.Local.B2C.auths.model","com.b2c.Local.B2C.common.dao","com.b2c.Local.B2C.store.dao","com.b2c.Local.B2C.products.electronic.controller","com.b2c.Local.B2C.products.electronic.service","com.b2c.Local.B2C.products.electronic.model","com.b2c.Local.B2C.products.electronic.dao","com.b2c.Local.B2C.store.model","com.b2c.Local.B2C.auths.dao","com.b2c.Local.B2C.common.model","com.b2c.Local.B2C.notification.dao","com.b2c.Local.B2C.notification.model"}, entityManagerFactoryRef = "postgresEntityManager",transactionManagerRef = "postTransactionManager")
public class PostgresDbConfig {

    @Value("${spring.datasource.first.hibernate.dialect}")
    private String dialect;

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.first")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(value = "postgresEntityManager")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", dialect);
        return entityManagerFactoryBuilder.dataSource(primaryDataSource()).packages("com.b2c.Local.B2C.websocket.buffermessage","com.b2c.Local.B2C.notification.dao","com.b2c.Local.B2C.notification.model","com.b2c.Local.B2C.auths.model","com.b2c.Local.B2C.common.dao","com.b2c.Local.B2C.store.dao","com.b2c.Local.B2C.store.model","com.b2c.Local.B2C.auths.dao","com.b2c.Local.B2C.products.electronic.controller","com.b2c.Local.B2C.products.electronic.service","com.b2c.Local.B2C.products.electronic.model","com.b2c.Local.B2C.products.electronic.dao","com.b2c.Local.B2C.common.model").properties(properties).build();
    }

    @Primary
    @Bean(name="postTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("postgresEntityManager") EntityManagerFactory enManFact) {
        return new JpaTransactionManager(enManFact);
    }


}
