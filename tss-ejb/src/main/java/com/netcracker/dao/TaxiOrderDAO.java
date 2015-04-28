/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.dao;

import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Виктор
 */
public class TaxiOrderDAO extends GenericDAO<Object> {

    public TaxiOrderDAO() {
        super();
    }

    public List<TaxiOrder> getTaxiOrderHistory(User user) {
        TypedQuery<TaxiOrder> tq = em.createQuery(
                "SELECT t FROM TaxiOrder t WHERE t.userId = :userId",TaxiOrder.class);
        tq.setParameter("userId", user);
        
        List<TaxiOrder> taxiOrders = tq.getResultList();
        return taxiOrders;
    }
}
