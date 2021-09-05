package com.api.pipeline.main.config.db;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "testEntityManagerFactory",
//        transactionManagerRef = "testTransactionManager",
//        basePackages = { "com.api.pipeline.blackup" }
//)
public class TestDataSourceConfig {
//
//    @Primary
//    @Bean
//    @ConfigurationProperties("test.datasource")
//    public DataSourceProperties testDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Primary
//    @Bean
//    @ConfigurationProperties("test.datasource.configuration")
//    public DataSource testDataSource(@Qualifier("testDataSourceProperties") DataSourceProperties dataSourceProperties) {
//        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//
//    @Primary
//    @Bean(name="entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean testEntityManagerFactory(EntityManagerFactoryBuilder builder,
//                                                                              @Qualifier("testDataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.api.pipeline.blackup")
//                .persistenceUnit("primaryEntityManager")
//                .build();
//    }
//
//    @Primary
//    @Bean
//    public PlatformTransactionManager testTransactionManager(@Qualifier("testEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
}