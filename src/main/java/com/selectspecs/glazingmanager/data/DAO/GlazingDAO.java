/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selectspecs.glazingmanager.data.DAO;

import com.selectspecs.sslogic.Database.ORM.Custom.ItemDataForGlazing;
import com.selectspecs.sslogic.Database.ORM.Custom.LabJobAndTrace;
import com.selectspecs.sslogic.Database.ORM.LabGlazingJob;
import com.selectspecs.sslogic.Database.ORM.MailMessage;
import com.selectspecs.sslogic.Database.ORM.OrderedItem;
import com.selectspecs.sslogic.Database.ORM.WsSize;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eugene
 */
public interface GlazingDAO {

    /**
     *
     * @param orderNo
     * @param itemNo
     * @return
     */
    @Transactional
    ItemDataForGlazing getItemGlazingData(int orderNo, int itemNo);

    /**
     *
     * @param id
     * @return
     */
    LabGlazingJob getLabJob(int id);

    /**
     *
     * @return
     */
    Collection<LabJobAndTrace> getNewLabJobs();

    /**
     *
     * @param orderNo
     * @param itemNo
     * @return
     */
    OrderedItem getOrderedItemByOrderNoItemNo(int orderNo, int itemNo);

    /**
     *
     * @param pageItem
     * @param size
     * @return
     */
    WsSize getWebstockSize(BigDecimal pageItem, int size);

    /**
     *
     * @param email
     */
    void sendGlazingLabEmail(MailMessage email);

    /**
     *
     * @param labJob
     */
    void updateLabJob(LabGlazingJob labJob);

    /**
     *
     * @param wsSize
     */
    void updateWsSize(WsSize wsSize);
    
}
