package com.netcracker.ejb;

import com.netcracker.dao.ContactsDAO;
import com.netcracker.dao.RoleDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.Role;
import com.netcracker.entity.User;
import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.persistence.NoResultException;

/**
 *
 * @author Виктор
 * @author maks
 */
public class RegistrationBean implements SessionBean {

    public void registrate(User user) {
        UserDAO userDAO = null;
        ContactsDAO contactsDAO = null;
        try {
            Role role = new RoleDAO().findByRolename("CUSTOMER");//by name or ID?
            user.addRole(role);
            userDAO = new UserDAO();
            userDAO.persist(user);
            contactsDAO = new ContactsDAO();
            contactsDAO.persist(new Contacts(userDAO.getByEmail(user.getEmail())));
        } finally {
            if (userDAO != null) {
                userDAO.close();
            }
            if (contactsDAO != null) {
                contactsDAO.close();
            }
        }
    }

    public boolean isUserExist(User user) {
        UserDAO dao = null;
        try {
            dao = new UserDAO();
            User userFromDB = dao.getByEmail(user.getEmail());
            if (userFromDB != null) {
                return true;
            }
            return false;
        } catch (NoResultException e) {
            return false;
        } finally {
            if (dao != null) {
                dao.close();
            }
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
