package com.netcracker.ejb;


import com.netcracker.dao.TariffDAO;
import com.netcracker.dao.exceptions.NoSuchEntity;
import com.netcracker.entity.Tariff;
import com.netcracker.entity.helper.Pager;
import com.netcracker.exceptions.InvalidEntityException;
import com.netcracker.util.BeansLocator;

import java.rmi.RemoteException;
import java.util.ArrayList;
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
            validate(tariff);
            tariffDAO.update(tariff);
        } catch (NoSuchEntity e) {
            throw new IllegalArgumentException("Can't edit this tariff! \n Tariff with id = " + tariffId + " doesn't exist");
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
            return dao.findPageOrderedByName(pageNumber, paginationStep); //maybe should clone or convert to dto?
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

    private void validate (Tariff group){
        ValidatorBeanLocal validatorBean = BeansLocator.getInstance().getBean(ValidatorBeanLocal.class);
        String message = validatorBean.validate(group);
        if (message != null){
            throw new InvalidEntityException(message);
        }
    }

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
