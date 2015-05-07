package com.netcracker.ejb;

import com.netcracker.entity.Car;

import javax.ejb.EJBLocalObject;

/**
 * @author Kyrylo Berehovyi
 */
public interface ValidatorBeanLocal extends EJBLocalObject {

    String validateCar(Car car);
}

