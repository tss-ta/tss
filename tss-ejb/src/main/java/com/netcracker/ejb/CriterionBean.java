package com.netcracker.ejb;

import com.netcracker.dao.CriterionDAO;
import com.netcracker.entity.Criterion;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Kyrylo Berehovyi
 */
public class CriterionBean implements SessionBean {

    public void removeCriterionList(List<Criterion> criterionList) {
        CriterionDAO criterionDAO = null;
        try {
            criterionDAO = new CriterionDAO();
            for (Criterion criterion : criterionList) {
                criterionDAO.delete(criterion);
            }
        } finally {
            if (criterionDAO != null) {
                criterionDAO.close();
            }
        }
    }

    public void updateCriterionList(List<Criterion> criterionList) {
        CriterionDAO criterionDAO = null;
        try {
            criterionDAO = new CriterionDAO();
            for (Criterion criterion : criterionList) {
                criterionDAO.update(criterion);
            }
        } finally {
            if (criterionDAO != null) {
                criterionDAO.close();
            }
        }
    }

    public void insertCriterionList(List<Criterion> criterionList) {
        CriterionDAO criterionDAO = null;
        try {
            criterionDAO = new CriterionDAO();
            for (Criterion criterion : criterionList) {
                criterionDAO.persist(criterion);
            }
        } finally {
            if (criterionDAO != null) {
                criterionDAO.close();
            }
        }
    }

    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {

    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {

    }
}
