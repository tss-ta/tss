
package com.netcracker.ejb;

import com.netcracker.dao.AddressDAO;
import com.netcracker.dao.MeetMyGuestDAO;
import com.netcracker.dao.RouteDAO;
import com.netcracker.dao.ServiceDAO;
import com.netcracker.dao.TaxiOrderDAO;
import com.netcracker.entity.Address;
import com.netcracker.entity.MeetMyGuest;
import com.netcracker.entity.Route;
import com.netcracker.entity.Service;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.Status;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 *
 * @author Виктор
 */
public class MeetMyGuestBean implements SessionBean {

    public void addMeetMyGuestService(User user, Route route, Address addFrom,
            Address addTo, TaxiOrder taxiOrder, String guestName) {
        AddressDAO addressDAO = null;
        RouteDAO routeDAO = null;
        TaxiOrderDAO taxiOrderDAO = null;
        ServiceDAO serviceDAO = null;
        MeetMyGuestDAO meetMyGuestDAO = null;
        try {
            addressDAO = new AddressDAO();
            addressDAO.persist(addFrom);
            addressDAO.persist(addTo);
            routeDAO = new RouteDAO();
            route.setFromAddrId(addFrom);
            route.setToAddrId(addTo);
            routeDAO.persist(route);
            taxiOrder.setRouteId(route);
            taxiOrder.setContactsId(new TaxiOrderBean().createContacts(user));
            taxiOrder.setStatus(Status.QUEUED);
            taxiOrder.setRouteId(route);
            taxiOrderDAO = new TaxiOrderDAO();
            taxiOrderDAO.persist(taxiOrder);
            serviceDAO = new ServiceDAO();
            Service service = new Service();
            service.setServiceName("meetMyGuest");
            service.setOrderId(taxiOrder);
            serviceDAO.persist(service);
            MeetMyGuest meetMyGuest = new MeetMyGuest();
            meetMyGuest.setServiceId(service.getServiceId());
            meetMyGuest.setService(service);
            meetMyGuest.setGuestName(guestName);
            meetMyGuestDAO = new MeetMyGuestDAO();
            meetMyGuestDAO.persist(meetMyGuest);
        } finally {
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
            if (meetMyGuestDAO != null) {
                meetMyGuestDAO.close();
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
