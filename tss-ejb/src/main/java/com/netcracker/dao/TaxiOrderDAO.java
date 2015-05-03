/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.dao;

import com.netcracker.entity.Contacts;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;

import java.util.List;
import javax.persistence.Query;

import javax.persistence.TypedQuery;

/**
 *
 * @author Виктор
 * @author maks
 */
public class TaxiOrderDAO extends GenericDAO<TaxiOrder> {

    public TaxiOrderDAO() {
        super();
    }

    public List<TaxiOrder> getTaxiOrderHistory(int pageNumber, int pageSize, Contacts contacts) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Argument 'pageNumber' <= 0");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Argument 'pageSize' <= 0");
        }
        TypedQuery<TaxiOrder> tq = em.createQuery(
                "SELECT t FROM TaxiOrder t WHERE t.contactsId = :contactsId ORDER BY t.bookingTime DESC", TaxiOrder.class);
        tq.setParameter("contactsId", contacts);
        tq.setFirstResult((pageNumber - 1) * pageSize);
        tq.setMaxResults(pageSize);
        List<TaxiOrder> taxiOrders = tq.getResultList();
        return taxiOrders;
    }

//    public int countOrdersWithCarCategory () {};
    public int countOrdersWithWifi() {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE t.wifi = true");
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countOrdersWithConditioner() {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE t.conditioner = true");
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countOrdersWithAnimalable() {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE t.animalTransport = true");
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }
        
    public int countOrdersWithWifi(User user) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE (t.wifi = true)");
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countOrdersWithConditioner(User user) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE t.conditioner = true");
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countOrdersWithAnimalable(User user) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE t.animalTransport = true");
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }
}
