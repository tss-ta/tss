package com.netcracker.ejb;

import com.netcracker.entity.Car;

import javax.ejb.EJBLocalObject;
import java.util.List;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
 */
public interface CarBeanLocal extends EJBLocalObject {

    List<Car> getPageOfCars(int pageNumber, int pageSize);

    public boolean insertCar(Car car);

    public List<Car> searchByLicPlate(String licPlate);

    List<Car> getPageOfCarsSearchedByLicPlate(int pageNumber, int pageSize, String licPlate);
}
