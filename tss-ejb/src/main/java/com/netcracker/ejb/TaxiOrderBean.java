/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.dao.AddressDAO;
import com.netcracker.dao.RouteDAO;
import com.netcracker.dao.TaxiOrderDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.entity.Address;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;


/**
 *
 * @author Виктор
 */
public class TaxiOrderBean implements SessionBean {

    public void addTaxiOrder(User user, Route route, Address addFrom, Address addTo, TaxiOrder taxiOrder) {
        AddressDAO addressDAO = null;
        RouteDAO routeDAO = null;
        TaxiOrderDAO taxiOrderDAO = null;
        UserDAO userDAO = null;
        try {
            addressDAO = new AddressDAO();
            addressDAO.persist(addFrom);
            addressDAO.persist(addTo);
            routeDAO = new RouteDAO();
            route.setFromAddrId(addFrom);
            route.setToAddrId(addTo);
            routeDAO.persist(route);
            taxiOrder.setRouteId(route);
            userDAO = new UserDAO();
            User userFromDB = userDAO.getByEmail(user.getEmail());
            if (userFromDB != null) {
                taxiOrder.setUserId(userFromDB);
            } else {
                userDAO.persist(user);
                taxiOrder.setUserId(user);
            }
            taxiOrder.setRouteId(route);
            taxiOrderDAO = new TaxiOrderDAO();
            taxiOrderDAO.persist(taxiOrder);
        } finally {
            if (addressDAO != null) {
                addressDAO.close();
            }
            if (routeDAO != null) {
                routeDAO.close();
            }
            if (userDAO != null) {
                userDAO.close();
            }
            if (taxiOrderDAO != null) {
                taxiOrderDAO.close();
            }
        }

    }

    @Override
    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
