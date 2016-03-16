/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selectspecs.glazingmanager.config;

import com.selectspecs.sslogic.Database.DAO.GlazingJobDataService;
import com.selectspecs.sslogic.GlazingLab.LabProcessors.LensOrdersProcessor;
import com.selectspecs.sslogic.GlazingLab.LabProcessors.TracingFilesProcessor;
import com.selectspecs.sslogic.GlazingLab.LabProcessors.WebstockUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 *
 * @author Eugene
 */
@Configuration
public class GlazingLabBeans {

    @Autowired
    private GlazingJobDataService glazingJobDataProvider;

    /**
     *
     * @return
     */
    @Bean(name = "tracingFilesProcessor")
    @DependsOn("glazingJobDataProvider")
    public TracingFilesProcessor TracingFilesProcessor() {

        TracingFilesProcessor tfp = TracingFilesProcessor.getInstance(glazingJobDataProvider);
        if (!tfp.isWorking()) {
            tfp.start();
        }
        return tfp;
    }

    /**
     *
     * @return
     */
    @Bean(name = "lensOrdersProcessor")
    @DependsOn("glazingJobDataProvider")
    public LensOrdersProcessor LensOrdersProcessor() {

        LensOrdersProcessor lop = LensOrdersProcessor.getInstance(glazingJobDataProvider);
        if (!lop.isWorking()) {
            lop.start();
        }
        return lop;
    }

    /**
     *
     * @return
     */
    @Bean(name = "webStockUpdater")
    @DependsOn("glazingJobDataProvider")
    public WebstockUpdater WebStockUpdater() {

        WebstockUpdater wsu = WebstockUpdater.getInstance(glazingJobDataProvider);
        if (!wsu.isWorking()) {
            wsu.start();
        }
        return wsu;
    }
}
