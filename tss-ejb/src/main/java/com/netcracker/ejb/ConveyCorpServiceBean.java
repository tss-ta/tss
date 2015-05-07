/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.dao.AddressDAO;
import com.netcracker.dao.ContactsDAO;
import com.netcracker.dao.RouteDAO;
import com.netcracker.dao.ServiceDAO;
import com.netcracker.dao.TaxiOrderDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.entity.Address;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.Route;
import com.netcracker.entity.Service;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.persistence.NoResultException;

/**
 *
 * @author Lis
 */
public class ConveyCorpServiceBean implements SessionBean{

    public Integer addCorpService(User user, List<Address> addFrom,
			Address addTo, TaxiOrder taxiOrder){
        TaxiOrderDAO taxiOrderDAO = null;
        ServiceDAO serviceDAO=null;
        RouteDAO routeDAO = null;
        AddressDAO addressDAO = null;
        try{
       taxiOrder.setContactsId(createContacts(user));
       taxiOrderDAO = new TaxiOrderDAO();
       taxiOrder.setStatus(TaxiOrder.Status.QUEUED);
       taxiOrderDAO.persist(taxiOrder);
       serviceDAO=new ServiceDAO();
       Service s=new Service();
       s.setServiceName("conveycorp");
       s.setOrderId(taxiOrder);
       List<Route> rl=new ArrayList<Route>();
       routeDAO = new RouteDAO();
       addressDAO = new AddressDAO();
       addressDAO.persist(addTo);
       for(Address a:addFrom){
           addressDAO.persist(a);
           Route r= new Route(user.getUsername() + " Route");
           r.setFromAddrId(a);
	   r.setToAddrId(addTo);
           routeDAO.persist(r);
           rl.add(r);
       }
       s.setRoutes(rl);
       serviceDAO.persist(s);
        }finally{
           if (addressDAO != null) {
				addressDAO.close();
			}
			if (routeDAO != null) {
				routeDAO.close();
			}
			if (taxiOrderDAO != null) {
				taxiOrderDAO.close();
			}
                        if (serviceDAO != null) {
				serviceDAO.close();
			}
        }
       return taxiOrder.getId();
    }
private Contacts createContacts(User user) {
		ContactsDAO contactsDAO = null;
		UserDAO userDAO = null;
		Contacts contacts = null;
		try {
			userDAO = new UserDAO();
			User userFromDB = null;
			try {
				userFromDB = userDAO.getByEmail(user.getEmail());
			} catch (NoResultException nre) {
			}
			contactsDAO = new ContactsDAO();
			if (userFromDB != null) {
				contactsDAO.persist(new Contacts(userFromDB));
			} else {
				contactsDAO.persist(new Contacts(user.getUsername(), user
						.getEmail()));
			}
			contacts = contactsDAO.getByEmail(user.getEmail());
		} finally {
			if (userDAO != null) {
				userDAO.close();
			}
			if (contactsDAO != null) {
				contactsDAO.close();
			}
		}
		return contacts;
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

