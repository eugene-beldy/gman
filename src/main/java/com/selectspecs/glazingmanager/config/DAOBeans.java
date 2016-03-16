/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selectspecs.glazingmanager.config;

import com.selectspecs.glazingmanager.data.DAO.GlazingDAOImpl;
import com.selectspecs.glazingmanager.data.service.GlazingJobDataServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 *
 * @author Eugene
 */
@Configuration
public class DAOBeans {

    /**
     *
     * @return
     */
    @Bean(name = "glazingDAO")
    @DependsOn("sessionFactory")
    public GlazingDAOImpl glazingDAO() {
        return new GlazingDAOImpl();
    }

    @Bean(name = "glazingJobDataProvider")
    @DependsOn("glazingDAO")
    public GlazingJobDataServiceImpl GlazingService() {
        return new GlazingJobDataServiceImpl();
    }

}
