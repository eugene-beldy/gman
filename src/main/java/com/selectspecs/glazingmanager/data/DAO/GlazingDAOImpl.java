/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selectspecs.glazingmanager.data.DAO;

import com.selectspecs.glazingmanager.data.service.GlazingJobDataServiceImpl;
import com.selectspecs.sslogic.Database.DAO.GlazingQueries;
import com.selectspecs.sslogic.Database.ORM.Custom.ItemDataForGlazing;
import com.selectspecs.sslogic.Database.ORM.Custom.LabJobAndTrace;
import com.selectspecs.sslogic.Database.ORM.LabGlazingJob;
import com.selectspecs.sslogic.Database.ORM.MailMessage;
import com.selectspecs.sslogic.Database.ORM.OrderedItem;
import com.selectspecs.sslogic.Database.ORM.WsSize;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eugene
 */

@Repository
public class GlazingDAOImpl implements GlazingDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     *
     * @param orderNo
     * @param itemNo
     * @return
     */
    @Override
    @Transactional
    public ItemDataForGlazing getItemGlazingData(int orderNo, int itemNo) {

        List<ItemDataForGlazing> glazingDataList = null;

        try {
            glazingDataList = getCurrentSession().createSQLQuery(
                    GlazingQueries.ITEM_DATA_FOR_GLAZING_QUERY).
                    addEntity(ItemDataForGlazing.class).
                    setMaxResults(1).
                    setInteger("orderNo", orderNo).
                    setInteger("itemNo", itemNo)
                    .list();
        } catch (Exception ex) {
            Logger.getLogger(GlazingJobDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (glazingDataList == null || glazingDataList.isEmpty()) {
            return null;
        } else {
            return glazingDataList.get(0);
        }
    }

    /**
     *
     * @param email
     */
    @Override
    public void sendGlazingLabEmail(MailMessage email) {
        getCurrentSession().saveOrUpdate(email);
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<LabJobAndTrace> getNewLabJobs() {
        return getCurrentSession().createSQLQuery(
                GlazingQueries.LAB_JOB_AND_TRACE_QUERY).
                addEntity(LabJobAndTrace.class).list();
    }

    /**
     *
     * @param labJob
     */
    @Override
    public void updateLabJob(LabGlazingJob labJob) {
        getCurrentSession().saveOrUpdate(labJob);
    }

    /**
     *
     * @param pageItem
     * @param size
     * @return
     */
    @Override
    public WsSize getWebstockSize(BigDecimal pageItem, int size) {
        List<WsSize> wsSizeList = getCurrentSession().createSQLQuery(
                GlazingQueries.WSSIZE_QUERY).
                addEntity(WsSize.class)
                .setBigDecimal("pageItem", pageItem).
                setInteger("size", size).
                list();
        if (wsSizeList == null || wsSizeList.isEmpty()) {
            return null;
        } else {
            return wsSizeList.get(0);
        }
    }

    /**
     *
     * @param wsSize
     */
    @Override
    public void updateWsSize(WsSize wsSize) {
        getCurrentSession().saveOrUpdate(wsSize);
    }

    /**
     *
     * @param orderNo
     * @param itemNo
     * @return
     */
    @Override
    public OrderedItem getOrderedItemByOrderNoItemNo(int orderNo, int itemNo) {
        List<OrderedItem> orderedItemsList = getCurrentSession().createSQLQuery(
                GlazingQueries.ORDERED_ITEM_QUERY).
                addEntity(OrderedItem.class)
                .setInteger("orderNo", orderNo).
                setInteger("itemNo", itemNo).
                list();
        if (orderedItemsList == null || orderedItemsList.isEmpty()) {
            return null;
        } else {
            return orderedItemsList.get(0);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public LabGlazingJob getLabJob(int id) {
        List<LabGlazingJob> labJobs = getCurrentSession().createSQLQuery(
                GlazingQueries.LAB_GLAZING_JOB_QUERY).
                addEntity(LabGlazingJob.class).
                setInteger("id", id).
                list();
        if (labJobs == null || labJobs.isEmpty()) {
            return null;
        } else {
            return labJobs.get(0);
        }
    }

}
