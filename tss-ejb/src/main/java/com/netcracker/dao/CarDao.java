/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.dao;

import com.netcracker.entity.Car;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author maks
 */
public class CarDao extends GenericDAO<Car>{

    public Car findByLicPlate(String licPlate) {
        TypedQuery<Car> query = em.createNamedQuery("Car.findByLicPlate", Car.class);
        query.setParameter("licPlate", licPlate);
        return query.getSingleResult();
    }

    public List<Car> searchByLicPlate(String licPlate) {
        TypedQuery<Car> query = em.createNamedQuery("Car.searchByLicPlate", Car.class);
        query.setParameter("licPlate", "%" + licPlate + "%");
        return query.getResultList();
    }
}
