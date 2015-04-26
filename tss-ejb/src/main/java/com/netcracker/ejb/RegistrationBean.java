
package com.netcracker.ejb;

import com.netcracker.dao.RoleDAO;
import com.netcracker.dao.UserDAO;
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
 */
public class RegistrationBean implements SessionBean {

    public void registrate(User user) {
        UserDAO userDao = null;
        try {
            Role role = new RoleDAO().get(3);//maybe by name?
            user.addRole(role);
            userDao = new UserDAO();
            userDao.persist(user);
        } finally {
            if (userDao != null) {
                userDao.close();
            }
        }
    }

//    public boolean checkUser(User user) {
//	UserDAO dao = new UserDAO();
//        User userFromDB = dao.getByEmail(user.getEmail());
//        dao.close();
//        if (userFromDB == null) {
//            return true;
//        }
//        return false;
//    }
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
