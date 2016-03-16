/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selectspecs.glazingmanager.config;

import com.selectspecs.sslogic.Database.Config.DBSettings;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Eugene
 */
@Configuration
@EnableTransactionManagement
public class DBConfig {

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    @Resource
    private Environment env;

    /**
     *
     * @return
     */
    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DBSettings.getDATABASE_DRIVER());
        dataSource.setUrl(DBSettings.getDATABASE_URL());
        dataSource.setUsername(DBSettings.getDATABASE_USERNAME());
        dataSource.setPassword(DBSettings.getDATABASE_PASSWORD());

        return dataSource;
    }

    /**
     *
     * @return
     */
    @Bean(name = "dataSourceSupp")
    public DriverManagerDataSource dataSourceSupp() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DBSettings.getDATABASE_DRIVER());
        dataSource.setUrl(DBSettings.getDATABASE_URL_SUPP());
        dataSource.setUsername(DBSettings.getDATABASE_USERNAME());
        dataSource.setPassword(DBSettings.getDATABASE_PASSWORD());

        return dataSource;
    }

    /**
     *
     * @return
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(DBSettings.getENTITYMANAGER_PACKAGES_TO_SCAN());
        sessionFactoryBean.setHibernateProperties(hibProperties());
        return sessionFactoryBean;
    }

    /**
     *
     * @return
     */
    @Bean
    public LocalSessionFactoryBean sessionFactorySupp() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSourceSupp());
        sessionFactoryBean.setPackagesToScan(DBSettings.getENTITYMANAGER_PACKAGES_TO_SCAN());
        sessionFactoryBean.setHibernateProperties(hibProperties());
        return sessionFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, DBSettings.getDATABASE_HIBERNATE_DIALECT());
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, DBSettings.isDATABASE_SHOW_SQL());
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

//    /**
//     *
//     * @return
//     */
//    @Bean
//    public HibernateTransactionManager transactionManagerSupp() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactorySupp().getObject());
//        return transactionManager;
//    }
}
