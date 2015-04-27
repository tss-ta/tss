package com.netcracker.ejb;

import com.netcracker.entity.Car;
import com.netcracker.entity.Driver;

import javax.ejb.EJBLocalObject;
import java.util.List;

/**
 * Created by Illya on 26.04.2015.
 */
public interface DriverLocal extends EJBLocalObject {

    public Driver getDriver(Integer id);

    public void addDriver(Driver driver);

    public void editDriver(Driver driver);

    public void deleteDriver(Integer driverId);

    public List<Driver> getDriverPage(int pageNumber, int pageSize);

    public void assignCar(Driver driver, Car car);

    public void unassignCar(Driver driver, Car car);
}
