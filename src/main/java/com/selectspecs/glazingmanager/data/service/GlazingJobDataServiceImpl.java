/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selectspecs.glazingmanager.data.service;

import com.selectspecs.glazingmanager.data.DAO.GlazingDAO;
import com.selectspecs.sslogic.Database.DAO.GlazingJobDataService;
import com.selectspecs.sslogic.Database.ORM.Custom.ItemDataForGlazing;
import com.selectspecs.sslogic.Database.ORM.LabGlazingJob;
import com.selectspecs.sslogic.Database.ORM.Custom.LabJobAndTrace;
import com.selectspecs.sslogic.Database.ORM.MailMessage;
import com.selectspecs.sslogic.Database.ORM.OrderedItem;
import com.selectspecs.sslogic.Database.ORM.WsSize;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eugene
 */
@Service
@Transactional
public class GlazingJobDataServiceImpl implements GlazingJobDataService {
    
    @Autowired
    private GlazingDAO glazingDAO;

    /**
     *
     * @param orderNo
     * @param itemNo
     * @return
     */
    @Override
    public ItemDataForGlazing getItemGlazingData(int orderNo, int itemNo) {
        return glazingDAO.getItemGlazingData(orderNo, itemNo);
    }

    /**
     *
     * @param email
     */
    @Override
    public void sendGlazingLabEmail(MailMessage email) {
        glazingDAO.sendGlazingLabEmail(email);
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<LabJobAndTrace> getNewLabJobs() {
        return glazingDAO.getNewLabJobs();
    }

    /**
     *
     * @param labJob
     */
    @Override
    public void updateLabJob(LabGlazingJob labJob) {
        
        glazingDAO.updateLabJob(labJob);
    }

    /**
     *
     * @param pageItem
     * @param size
     * @return
     */
    @Override
    public WsSize getWebstockSize(BigDecimal pageItem, int size) {
        return glazingDAO.getWebstockSize(pageItem, size);
    }

    /**
     *
     * @param wsSize
     */
    @Override
    public void updateWsSize(WsSize wsSize) {
        
        glazingDAO.updateWsSize(wsSize);
    }

    /**
     *
     * @param orderNo
     * @param itemNo
     * @return
     */
    @Override
    public OrderedItem getOrderedItemByOrderNoItemNo(int orderNo, int itemNo) {
        return glazingDAO.getOrderedItemByOrderNoItemNo(orderNo, itemNo);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public LabGlazingJob getLabJob(int id) {
        return glazingDAO.getLabJob(id);
    }
    
}
