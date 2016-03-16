/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selectspecs.glazingmanager.controllers;

import com.selectspecs.sslogic.Database.DAO.GlazingJobDataService;
import com.selectspecs.sslogic.GlazingLab.LabMessage;
import com.selectspecs.sslogic.GlazingLab.LabProcessors.LensOrdersProcessor;
import com.selectspecs.sslogic.GlazingLab.LabProcessors.TracingFilesProcessor;
import com.selectspecs.sslogic.GlazingLab.LabProcessors.WebstockUpdater;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Eugene
 */
@Controller
public class AppController {

    @Autowired
    private GlazingJobDataService glazingJobDataProvider;

    @Autowired
    private TracingFilesProcessor tracingFilesProcessor;

    @Autowired
    private LensOrdersProcessor lensOrdersProcessor;

    @Autowired
    private WebstockUpdater webStockUpdater;

    /**
     *
     * @param map
     * @return
     */
    @RequestMapping(value = {"/", "/index**"}, method = {RequestMethod.GET})
    public String welcomePage(Map<String, Object> map) {

        map.put("tfpWorking", tracingFilesProcessor.isWorking());
        map.put("lopWorking", lensOrdersProcessor.isWorking());
        map.put("wsuWorking", webStockUpdater.isWorking());

        return "index";
    }

    /**
     *
     * @param fromDateMilisec
     * @return
     */
    @RequestMapping(value = {"/tracing"}, method = {RequestMethod.GET})
    public @ResponseBody
    List<LabMessage> tracingMessages(
            @RequestParam(value = "fromDate") long fromDateMilisec
    ) {

        List<LabMessage> messages = tracingFilesProcessor.getMessages();
        List<LabMessage> requestedGMs;

        Collections.sort(messages, (m1, m2) -> {
            return m1.getDate().compareTo(m2.getDate());
        });

        if (fromDateMilisec != 0) {
            Date fromDate = new Date(fromDateMilisec);
            requestedGMs = new ArrayList<>(messages);
            requestedGMs.removeIf((LabMessage t) -> {
                return t.getDate().compareTo(fromDate) <= 0;
            });
        } else {
            requestedGMs = messages;
        }

        return requestedGMs;
    }

    /**
     *
     * @param map
     * @return
     */
    @RequestMapping(value = {"/tfprestart"}, method = {RequestMethod.GET})
    public String startTfp(Map<String, Object> map) {
        if (!tracingFilesProcessor.isWorking()) {
            tracingFilesProcessor.start();
        }
        return "redirect:/index";
    }

    /**
     *
     * @param fromDateMilisec
     * @return
     */
    @RequestMapping(value = {"/lensorders"}, method = {RequestMethod.GET})
    public @ResponseBody
    List<LabMessage> lensOrderMessages(
            @RequestParam(value = "fromDate") long fromDateMilisec
    ) {

        List<LabMessage> messages = lensOrdersProcessor.getMessages();
        List<LabMessage> requestedGMs;

        Collections.sort(messages, (m1, m2) -> {
            return m1.getDate().compareTo(m2.getDate());
        });

        if (fromDateMilisec != 0) {
            Date fromDate = new Date(fromDateMilisec);
            requestedGMs = new ArrayList<>(messages);
            requestedGMs.removeIf((LabMessage t) -> {
                return t.getDate().compareTo(fromDate) <= 0;
            });
        } else {
            requestedGMs = messages;
        }

        return requestedGMs;
    }

    /**
     *
     * @param map
     * @return
     */
    @RequestMapping(value = {"/lorestart"}, method = {RequestMethod.GET})
    public String startLo(Map<String, Object> map) {
        if (!lensOrdersProcessor.isWorking()) {
            lensOrdersProcessor.start();
        }
        return "redirect:/index";
    }

    /**
     *
     * @param fromDateMilisec
     * @return
     */
    @RequestMapping(value = {"/wsupdate"}, method = {RequestMethod.GET})
    public @ResponseBody
    List<LabMessage> wsMessages(
            @RequestParam(value = "fromDate") long fromDateMilisec
    ) {

        List<LabMessage> messages = webStockUpdater.getMessages();
        List<LabMessage> requestedGMs;

        Collections.sort(messages, (m1, m2) -> {
            return m1.getDate().compareTo(m2.getDate());
        });

        if (fromDateMilisec != 0) {
            Date fromDate = new Date(fromDateMilisec);
            requestedGMs = new ArrayList<>(messages);
            requestedGMs.removeIf((LabMessage t) -> {
                return t.getDate().compareTo(fromDate) <= 0;
            });
        } else {
            requestedGMs = messages;
        }

        return requestedGMs;
    }

    /**
     *
     * @param map
     * @return
     */
    @RequestMapping(value = {"/wsurestart"}, method = {RequestMethod.GET})
    public String startWs(Map<String, Object> map) {
        if (!webStockUpdater.isWorking()) {
            webStockUpdater.start();
        }
        return "redirect:/index";
    }
}
