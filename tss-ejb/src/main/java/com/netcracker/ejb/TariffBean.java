package com.netcracker.ejb;


import com.netcracker.dao.TariffDAO;
import com.netcracker.entity.Tariff;
import com.netcracker.entity.helper.Pager;
import com.netcracker.util.BeansLocator;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.persistence.NoResultException;

/**
 * @author maks
 */
public class TariffBean implements SessionBean {



    public void editTariff(int tariffId, float additiveCoef, float multCoef) { //float or double
        if (tariffId < 0) {
            throw new IllegalArgumentException("Id can't be less than zero");
        } 
        TariffDAO tariffDAO = null;
        try {
            tariffDAO = new TariffDAO();
            Tariff tariff = tariffDAO.get(tariffId);           
            tariff.setPlusCoef(additiveCoef);
            tariff.setMultipleCoef(multCoef);
            tariffDAO.update(tariff);
        } finally {
            if (tariffDAO != null) {
                tariffDAO.close();
            }
        }
    }

    public List<Tariff> getTariffPage(int pageNumber, int paginationStep) {
        TariffDAO dao = null;
        try {
            dao = new TariffDAO();
            return dao.findPageOrderedByName(pageNumber, paginationStep); //maybe should clone or convert to DTO?
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    public List<Tariff> searchTariffByName(String namePart, int pageNumber, int paginationStep) {
        TariffDAO dao = null;
        try {
            dao = new TariffDAO();
            return dao.searchByName(namePart, pageNumber, paginationStep);
        } catch (NoResultException nre) {
            return new ArrayList<>();
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    public Pager getPager(Integer pageNumber, Integer pageSize) {
        PageCalculatorBeanLocal pageCalculator = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        return pageCalculator.createPager(Tariff.class, pageNumber, pageSize);
    }
    public Pager getPager(Integer pageNumber, Integer pageSize, String namePart) {
        PageCalculatorBeanLocal pageCalculator = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        TariffDAO tariffDAO = null;
        Pager pager = null;
        try {
            tariffDAO = new TariffDAO();
            int amount = tariffDAO.countByNamePart(namePart);
            pager = pageCalculator.calculatePages(pageNumber, pageSize, amount);
        } finally {
            if (tariffDAO != null) {
                tariffDAO.close();
            }
        }
        return pager;
    }

//    public List<Roles> toEnumRolesList(List<Role> roleList) {
//        List<Roles> rolesList = new ArrayList<Roles>(); //enum
//        Iterator<Role> roleIterator = roleList.iterator();
//        while (roleIterator.hasNext()) {
//            String roleName = roleIterator.next().getRolename();
//            rolesList.add(Roles.valueOf(roleName));
//        }
//        return rolesList;
//    }

    @Override
    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

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
