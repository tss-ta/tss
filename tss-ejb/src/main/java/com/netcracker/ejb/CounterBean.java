package com.netcracker.ejb;

import com.netcracker.dao.*;
import com.netcracker.entity.Role;
import com.netcracker.entity.helper.Roles;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;

/**
 * @author Kyrylo Berehovyi
 */
public class CounterBean implements SessionBean {

    public Integer countAllCars() {
        CarDao carDao = null;
        Integer count = null;
        try {
            carDao = new CarDao();
            count = carDao.count();
        } catch (Exception e) {
            if (carDao != null) {
                carDao.close();
            }
        }
        return count;
    }

    public Integer countAllGroups() {
        GroupDAO groupDao = null;
        Integer count = null;
        try {
            groupDao = new GroupDAO();
            count = groupDao.count();
        } catch (Exception e) {
            if (groupDao != null) {
                groupDao.close();
            }
        }
        return count;
    }

    public Integer countAllTariffs() {
        TariffDAO tariffDao = null;
        Integer count = null;
        try {
            tariffDao = new TariffDAO();
            count = tariffDao.count();
        } catch (Exception e) {
            if (tariffDao != null) {
                tariffDao.close();
            }
        }
        return count;
    }

    public Integer countAllOrders() {
        TaxiOrderDAO taxiOrderDAO = null;
        Integer count = null;
        try {
            taxiOrderDAO = new TaxiOrderDAO();
            count = taxiOrderDAO.count();
        } catch (Exception e) {
            if (taxiOrderDAO != null) {
                taxiOrderDAO.close();
            }
        }
        return count;
    }

    public Long countAllCustomers() {
        return countAllUsersByRolename(Roles.CUSTOMER);
    }

    public Long countAllDrivers() {
        return countAllUsersByRolename(Roles.DRIVER);
    }

    private Long countAllUsersByRolename(Roles roles) {
        UserDAO userDao = null;
        Long count = null;
        try {
            userDao = new UserDAO();
            count = userDao.countByRolename(roles.getRolename());
        } catch (Exception e) {
            if (userDao != null) {
                userDao.close();
            }
        }
        return count;
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
