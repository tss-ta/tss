package com.netcracker.dao;

import com.netcracker.entity.helper.DriverCar;
import javax.persistence.NoResultException;

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


}
