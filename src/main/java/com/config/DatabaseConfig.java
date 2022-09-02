package com.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@EnableTransactionManagement
@Configuration
public class DatabaseConfig {

    @Autowired
    private ApplicationContext applicationContext;

    private String driverClassName = "com.mysql.cj.jdbc.Driver";
    @Value("${DATABASE_URL}")
    private String url;
    @Value("${DATABASE_USERNAME}")
    private String username;
    @Value("${DATABASE_PASSWORD}")
    private String password;

    @Bean
    public DataSource dataSource() {
        log.info("DataSource Initializing");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        log.info("DataSource Initialized");
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        log.info("SqlSessionFactory Initializing");
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/sqls/*.xml"));
        factoryBean.setTypeAliasesPackage("com/model");
        log.info("SqlSessionFactory Initialized");
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        log.info("SqlSession Initializing");
        log.info("SqlSession Initialized");
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public PlatformTransactionManager transactionManger() {
        log.info("TransactionManger Initializing");
        log.info("TransactionManger Initialized");
        return new DataSourceTransactionManager(dataSource());
    }
}