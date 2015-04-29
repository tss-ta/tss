package com.netcracker.ejb;

import com.netcracker.entity.Car;

import javax.ejb.EJBLocalObject;
import java.util.List;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
 */
public interface CarBeanLocal extends EJBLocalObject {

    List<Car> getPageOfCars(int pageNumber);

    public void insertCar(Car car);
}
