package com.netcracker.dao;

import java.util.List;

import com.netcracker.entity.Driver;
import com.netcracker.entity.helper.DriverCar;

import javax.persistence.TypedQuery;

/**
 * @author Illia Rudenko
 */

public class DriverCarDAO extends GenericDAO<DriverCar> {

    public DriverCarDAO() {
        super();
    }

    public DriverCar getByDriverId(Integer driverId) {
        TypedQuery<DriverCar> drvCars = em.createNamedQuery("DriverCar.findByDriverId", DriverCar.class);
        drvCars.setParameter("driver_id", driverId);
        return drvCars.getSingleResult();
    }

    public DriverCar getCurrentCar(Integer driverId) {

    	TypedQuery<DriverCar> query = em.createNamedQuery("DriverCar.findCurrentCar", DriverCar.class);
    	query.setParameter("driver_id", driverId);
    	List<DriverCar> results = query.getResultList();
    	DriverCar driverCar = null;
    	if (!results.isEmpty()) {
    		driverCar = results.get(0);
    	}

    	return driverCar;
    }
}
