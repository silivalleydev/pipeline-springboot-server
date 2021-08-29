package com.api.pipeline.main.config.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "erpEntityManagerFactory",
        transactionManagerRef = "erpTransactionManager",
        basePackages = { "com.api.pipeline.erp" }
)
public class ErpDataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("erp.datasource")
    public DataSourceProperties erpDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("erp.datasource.configuration")
    public DataSource erpDataSource(@Qualifier("erpDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean erpEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                              @Qualifier("erpDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.api.pipeline.erp")
                .persistenceUnit("erpEntityManager")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager erpTransactionManager(@Qualifier("erpEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}