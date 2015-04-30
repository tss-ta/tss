package com.netcracker.ejb;

import com.netcracker.entity.Driver;

import javax.ejb.EJBLocalObject;
import java.util.List;

/**
 * @author Illia Rudenko
 */

public interface DriverLocal extends EJBLocalObject {

    public Driver getDriver(Integer id);

    public void addDriver(Driver driver);

    public void editDriver(Driver driver);

    public void deleteDriver(Integer driverId);

    public List<Driver> getDriverPage(int pageNumber, int pageSize);

    public void assignCar(Integer driverId, Integer carId);

    public void unassignCar(Integer driverId, Integer carId);
}
