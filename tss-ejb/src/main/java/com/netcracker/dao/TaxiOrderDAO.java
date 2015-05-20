/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.dao;

import com.netcracker.entity.Car;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.Driver;
import com.netcracker.entity.TaxiOrder;
import java.util.Date;
import com.netcracker.entity.helper.Status;
import com.netcracker.entity.helper.CarCategory;
import com.netcracker.entity.helper.DriverCar;
import java.util.ArrayList;
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
    }

    public List<TaxiOrder> getTaxiOrderHistoryDriver(int pageNumber, int pageSize,
            DriverCar driverCar, Status status, Driver driver, Car car) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Argument 'pageNumber' <= 0");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Argument 'pageSize' <= 0");
        }
        TypedQuery<TaxiOrder> tq;
        if (status != Status.QUEUED) {
            tq = em.createQuery(
                    "SELECT t FROM TaxiOrder t WHERE t.driverCarId = :driverCarId AND t.status = :status ORDER BY t.bookingTime DESC", TaxiOrder.class);
            tq.setParameter("driverCarId", driverCar);
            tq.setParameter("status", status.getId());
            tq.setFirstResult((pageNumber - 1) * pageSize);
            tq.setMaxResults(pageSize);
            List<TaxiOrder> taxiOrders = tq.getResultList();
            return taxiOrders;
        } else {
            tq = em.createQuery(
                    "SELECT t FROM TaxiOrder t WHERE t.status = :status AND "
                    + "t.male = :male AND t.carCategory = :carCategory"
                    + " ORDER BY t.bookingTime DESC", TaxiOrder.class);
            tq.setParameter("status", status.getId());
            tq.setParameter("male", driver.isMale());
            tq.setParameter("carCategory", car.getCategory());
            tq.setFirstResult((pageNumber - 1) * pageSize);
            tq.setMaxResults(pageSize);
            List<TaxiOrder> taxiOrders = tq.getResultList();
            return taxiOrders;
        }
    }

    public int countOrdersWithDriverStatus(DriverCar driverCar, Integer status) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE (t.driverCarId = :driverCarId) AND (t.status = :status) ");
        query.setParameter("driverCarId", driverCar);
        query.setParameter("status", status);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
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

    public int countOrdersWithStatus(Contacts userContacts, Integer status) {
        Query query = em.createQuery("SELECT COUNT(t) FROM TaxiOrder t WHERE (t.contactsId = :contacts) AND (t.status = :status) ");
        query.setParameter("contacts", userContacts);
        query.setParameter("status", status);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }
}
