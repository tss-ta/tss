package com.netcracker.ejb;

import javax.ejb.EJBLocalObject;

/**
 * @author Kyrylo Berehovyi
 */

public interface CounterBeanLocal extends EJBLocalObject {

    Integer countAllCars();

    int countAllCustomers();

    int countAllDrivers();

    Integer countAllOrders();

    Integer countAllGroups();

    Integer countAllTariffs();
}
