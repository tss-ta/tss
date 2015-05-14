package com.netcracker.dao;

import com.netcracker.entity.Driver;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.Category;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author Illia Rudenko
 */

public class DriverDAO extends GenericDAO<Driver> {

    public DriverDAO() {
        super();
    }

    public List<Driver> searchByName(String name, int pageNumber, int paginationStep) {
        Query query = em.createNamedQuery("Driver.searchDriverByName");
        query.setParameter("drivername", "%" + name + "%");
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    public Long countSearchedByNameResults(String name) {
        Query query = em.createQuery("SELECT COUNT(d.id) FROM Driver d WHERE d.username like :username");
        query.setParameter("username", "%" + name + "%");
        return (Long) query.getSingleResult();
    }

    public Driver getDriverByToken(Integer token) {
        try {
            Query query = em.createNamedQuery("Driver.searchDriverByToken");
            query.setParameter("token", token);

            return (Driver) query.getSingleResult();
        } catch (NoResultException ex) {

        }
        return null;
    }
}
