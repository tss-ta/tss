package com.netcracker.ejb;

import com.netcracker.dao.CriterionDAO;
import com.netcracker.entity.Criterion;

import javax.ejb.EJBLocalObject;
import java.util.List;

/**
 * @author Kyrylo Berehovyi
 */
public interface CriterionBeanLocal extends EJBLocalObject {

    void removeCriterionList(List<Criterion> criterionList);

    void updateCriterionList(List<Criterion> criterionList);

    void insertCriterionList(List<Criterion> criterionList);

}