package com.netcracker.dao;

import com.netcracker.entity.Criterion;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Kyrylo Berehovyi
 */
public class CriterionDao extends GenericDAO<Criterion> {

    public List<Criterion> findCriterionByReportInfoId(Integer id) {
        Query query = em.createQuery("SELECT c FROM Criterion c WHERE c.reportInfo.id = :id");
        query.setParameter("id", id);
        return (List<Criterion>) query.getResultList();
    }
}
