/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.dao.RoleDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.entity.Role;
import com.netcracker.entity.User;
import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 *
 * @author Виктор
 */
public class RegistrationBean implements SessionBean {

    public void registrate(User user) {
        Role role = new RoleDAO().get(3);
        user.addRole(role);
        UserDAO userDao =  new UserDAO();
        userDao.persist(user);
        userDao.close();
    }

    public boolean checkUser(User user) {
        User userFromDB = new UserDAO().getByEmail(user.getEmail());
        if (userFromDB == null) {
            return true;
        }
        return false;
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
