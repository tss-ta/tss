package com.netcracker.dao;

import com.netcracker.entity.Criterion;
import com.netcracker.entity.ReportInfo;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Kyrylo Berehovyi
 */
public class CriterionDAO extends GenericDAO<Criterion> {

    public List<Criterion> findCriterionByReportInfoId(Integer id) {
        Query query = em.createQuery("SELECT c FROM Criterion c WHERE c.reportInfo.id = :id");
        query.setParameter("id", id);
        return (List<Criterion>) query.getResultList();
    }

    @Override
    public void delete(Criterion entity) {
        Query query = em.createQuery("DELETE FROM Criterion c WHERE c.id = :id");
        query.setParameter("id", entity.getId());
        query.executeUpdate();
    }
}
