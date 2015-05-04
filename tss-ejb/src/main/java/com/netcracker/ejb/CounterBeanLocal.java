package com.netcracker.ejb;

import javax.ejb.EJBLocalObject;

/**
 * @author Kyrylo Berehovyi
 */

public interface CounterBeanLocal extends EJBLocalObject {

    Integer countAllCars();

    Long countAllCustomers();

    Long countAllDrivers();

    Integer countAllOrders();

    Integer countAllGroups();

    Integer countAllTariffs();
}
