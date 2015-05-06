package com.netcracker.dao;

import com.netcracker.entity.Tariff;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author maks
 */
public class TariffDAO extends GenericDAO<Tariff> {

    public List<Tariff> searchByName(String partOfName, int pageNumber, int paginationStep) {
        TypedQuery<Tariff> query = em.createNamedQuery("Tariff.searchByNameOrdered", Tariff.class);
        query.setParameter("tariffName", "%" + partOfName + "%");
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    public List<Tariff> findPageOrderedByName(int pageNumber, int paginationStep) {
        TypedQuery<Tariff> query = em.createNamedQuery("Tariff.findAllOrderedByName", Tariff.class);
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    public Tariff findByTariffName(String tariffName) {
        TypedQuery<Tariff> query = em.createNamedQuery("Tariff.findByTariffName", Tariff.class);
        query.setParameter("tariffName", tariffName);
        return query.getSingleResult();
    }
}
