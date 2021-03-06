/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.dao;

import com.netcracker.entity.Car;

import javax.persistence.Query;
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

    public List<Car> getPageOfCarsSearchedByLicPlate(int pageNumber, int pageSize, String licPlate) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Argument 'pageNumber' <= 0");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Argument 'pageSize' <= 0");
        }

        TypedQuery<Car> query = em.createNamedQuery("Car.searchByLicPlate", Car.class);
        query.setParameter("licPlate", "%" + licPlate + "%");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public Long countSearchedByLicPlateResults(String licPlate) {
        Query query = em.createQuery("SELECT COUNT(c.id) FROM Car c WHERE c.licPlate like :licPlate");
        query.setParameter("licPlate", "%" + licPlate + "%");
        return (Long) query.getSingleResult();
    }
}
