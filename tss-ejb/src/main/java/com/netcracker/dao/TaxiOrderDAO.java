/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.dao;

import com.netcracker.entity.Contacts;
import com.netcracker.entity.TaxiOrder;
import java.util.Date;
import com.netcracker.entity.helper.Status;
import com.netcracker.entity.helper.CarCategory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Виктор
 * @author maks
 * @author Vitalii Chekaliuk
 */
public class TaxiOrderDAO extends GenericDAO<TaxiOrder> {

    public TaxiOrderDAO() {
    }

    public List<TaxiOrder> getActiveTaxiOrders(int pageNumber, int pageSize, int driverId) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Argument 'pageNumber' <= 0");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Argument 'pageSize' <= 0");
        }
        TypedQuery<TaxiOrder> tq = em.createQuery(
               // "SELECT t FROM TaxiOrder t WHERE (t.status = 0) OR (t.status = 1) OR (((t.status = 2) OR (t.status = 4)) AND (t.driverCarId.driverId = :driverId)) ORDER BY t.status DESC, t.bookingTime ASC", TaxiOrder.class);
        		  "SELECT t FROM TaxiOrder t WHERE (t.status = 0) OR (t.status = 1) OR (t.status = 2) OR (t.status = 4) ORDER BY t.status DESC, t.bookingTime ASC", TaxiOrder.class);
       // tq.setParameter("driverId", driverId);
        tq.setFirstResult((pageNumber - 1) * pageSize);
        tq.setMaxResults(pageSize);
        List<TaxiOrder> taxiOrders = tq.getResultList();
        return taxiOrders;
    }
    
    public List<TaxiOrder> getCompletedTaxiOrders(int pageNumber, int pageSize, int driverId) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Argument 'pageNumber' <= 0");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Argument 'pageSize' <= 0");
        }
        TypedQuery<TaxiOrder> tq = em.createQuery(
                "SELECT t FROM TaxiOrder t WHERE (t.status = 5) AND (t.driverCarId.driverId = :driverId) ORDER BY t.bookingTime DESC", TaxiOrder.class);
        tq.setParameter("driverId", driverId);
        tq.setFirstResult((pageNumber - 1) * pageSize);
        tq.setMaxResults(pageSize);
        List<TaxiOrder> taxiOrders = tq.getResultList();
        return taxiOrders;
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

    public List<TaxiOrder> getTaxiOrderHistory(Integer pageNumber,
            int pageSize, Contacts contacts, Status status) {
        if (status != null) {
            return filterByStatus(
                    getTaxiOrderHistory(pageNumber, pageSize, contacts), status);
        } else {
            return getTaxiOrderHistory(pageNumber, pageSize, contacts);
        }
    }

    private List<TaxiOrder> filterByStatus(List<TaxiOrder> list, Status status) {
        List<TaxiOrder> tmp = new ArrayList<>();
        for (TaxiOrder to : list) {
            if (to.getEnumStatus().equals(status)) { //!!!!!!!!!
                tmp.add(to);
            }
        }
        return tmp;
    }

    public List<TaxiOrder> findBookedOrdersByPeriod(Date begin, Date end, int pageNumber, int pageSize) {
        TypedQuery<TaxiOrder> query = em.createQuery("SELECT t FROM TaxiOrder t WHERE t.bookingTime BETWEEN :begin AND :end ", TaxiOrder.class);
        query.setParameter("begin", begin);
        query.setParameter("end", end);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<TaxiOrder> findBookedOrdersByPeriod(Date begin, Date end) {
        TypedQuery<TaxiOrder> query = em.createQuery("SELECT t FROM TaxiOrder t WHERE t.bookingTime BETWEEN :begin AND :end ", TaxiOrder.class);
        query.setParameter("begin", begin);
        query.setParameter("end", end);
        return query.getResultList();
    }

    public int countOrdersWithCarCategory(CarCategory category) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE (t.carCategory = :category)");
        query.setParameter("category", category.getId());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

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

    public int countUserOrders(Contacts userContacts) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE (t.contactsId = :contacts)");
        query.setParameter("contacts", userContacts);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countOrdersWithWifi(Contacts userContacts) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE (t.contactsId = :contacts) AND (t.wifi = true)");
        query.setParameter("contacts", userContacts);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countOrdersWithConditioner(Contacts userContacts) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE (t.contactsId = :contacts) AND (t.conditioner = true)");
        query.setParameter("contacts", userContacts);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countOrdersWithAnimalable(Contacts userContacts) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE (t.contactsId = :contacts) AND (t.animalTransport = true)");
        query.setParameter("contacts", userContacts);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countBookedOrdersByPeriod(Date begin, Date end) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE t.bookingTime BETWEEN :begin AND :end ");
        query.setParameter("begin", begin);
        query.setParameter("end", end);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }
}
