package com.netcracker.dao;

import com.netcracker.entity.ReportInfo;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Kyrylo Berehovyi
 */

public class ReportInfoDAO extends GenericDAO<ReportInfo>{
    @Override
    public List<ReportInfo> getPage(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT ri FROM ReportInfo ri ORDER BY ri.id");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return (List<ReportInfo>) query.getResultList();
    }
}
