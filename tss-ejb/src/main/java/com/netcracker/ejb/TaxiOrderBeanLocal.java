/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.dao.exceptions.DriverOrderCountException;
import com.netcracker.entity.Address;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.Status;
import com.netcracker.entity.helper.TaxiOrderHistory;

import java.util.Date;
import java.util.List;

import javax.ejb.EJBLocalObject;

/**
 *
 * @author Виктор
 */
public interface TaxiOrderBeanLocal extends EJBLocalObject {

    public void addTaxiOrder(User user, Route route, Address addFrom, Address addTo, TaxiOrder taxiOrder);

    public List<TaxiOrderHistory> getTaxiOrderHistory(int pageNumber, int pageSize, User user);

    public TaxiOrder getOrderById(int id);

    public TaxiOrderHistory getOrderForEdit(TaxiOrder order);

    public void editTaxiOrderCustomer(int orderId, Address addFrom, Address addTo, Date orderTime, float distance, double price);

    public List<TaxiOrderHistory> getTaxiOrderHistory(Integer pageNumber,
            int pageSize, User user, Status status);

    public Contacts createContacts(User user);

    public void updateTaxiOrder(TaxiOrder taxiOrder);

    public void refuseTaxiOrder(int orderId);

    public int countOrdersByStatus(User user, Status status);

    public List<TaxiOrderHistory> getTaxiOrderDriver(Integer pageNumber,
            int pageSize, User user, Status status);

    public void setNextStatus(int taxiOrderId, User user) throws DriverOrderCountException;
}
