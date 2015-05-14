/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import com.netcracker.dao.AddressDAO;
import com.netcracker.dao.CarDao;
import com.netcracker.dao.ContactsDAO;
import com.netcracker.dao.DriverCarDAO;
import com.netcracker.dao.RouteDAO;
import com.netcracker.dao.TaxiOrderDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.dao.exceptions.NoSuchEntity;
import com.netcracker.entity.Address;
import com.netcracker.entity.Car;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.Driver;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.helper.DriverCar;
import com.netcracker.entity.helper.Status;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.TaxiOrderHistory;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.persistence.NoResultException;

import org.json.JSONException;

/**
 *
 * @author Виктор
 */
public class TaxiOrderBean implements SessionBean {

    public void addTaxiOrder(User user, Route route, Address addFrom,
            Address addTo, TaxiOrder taxiOrder) {
        if (user == null) {
            throw new IllegalArgumentException("User can't be null");
        }
        if (route == null) {
            throw new IllegalArgumentException("Route can't be null");
        }
        if (addFrom == null) {
            throw new IllegalArgumentException("addFrom can't be null");
        }
        if (addTo == null) {
            throw new IllegalArgumentException("addTo can't be null");
        }
        if (taxiOrder == null) {
            throw new IllegalArgumentException("TaxiOrder can't be null");
        }
        AddressDAO addressDAO = null;
        RouteDAO routeDAO = null;
        TaxiOrderDAO taxiOrderDAO = null;
        try {
            addressDAO = new AddressDAO();
            addressDAO.persist(addFrom);
            addressDAO.persist(addTo);
            routeDAO = new RouteDAO();
            route.setFromAddrId(addFrom);
            route.setToAddrId(addTo);
            routeDAO.persist(route);
            taxiOrder.setRouteId(route);
            taxiOrder.setContactsId(createContacts(user));
            taxiOrder.setStatus(Status.QUEUED);
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
            if (taxiOrderDAO != null) {
                taxiOrderDAO.close();
            }
        }

    }

    public boolean checkDriverEligibility(TaxiOrder order, Driver driver, Integer carId) {

    	Car car = null;
		try {
			car = new CarDao().get(carId);
		} catch (NoSuchEntity e) {
			return false;
		}
    	
    	if (!driver.isAvailable() || !car.getAvailable()) {
    		return false;
    	}
    	
    	if (order.getAnimalTransport() && (!car.getAnimalable())) {
    		return false;
    	}
    	
    	if (order.getConditioner()&& (!car.getConditioner())) {
    		return false;
    	}
    	
    	if (order.getWifi() && (!car.getWifi())) {
    		return false;
    	}
    	
    	if (order.getCarCategory() != car.getCategory()) {
    		return false;
    	}
    	
    	if ((order.getMale() != null) && (order.getMale() != driver.isMale())) {
    		return false;
    	}
    	
    	if (!order.getSmoke() && driver.isSmokes()) {
    		return false;
    	}
    	
    	return true;
    }
    
    public boolean assignTaxiOrder(int taxiOrderId, Driver driver) {

    	DriverCarDAO drCarDao = new DriverCarDAO();
    	DriverCar driverCar = drCarDao.getCurrentCar(driver.getId());
    	if (driverCar == null) {
    		return false;
    	}
    	
    	TaxiOrder taxiOrder = getOrderById(taxiOrderId);
   
    	if (!checkDriverEligibility(taxiOrder, driver, driverCar.getCarId())) {
    		return false;
    	}
    	
        taxiOrder.setStatus(Status.ASSIGNED);
        taxiOrder.setDriverCarId(driverCar);
        TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            dao.update(taxiOrder);
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    	
    	return true; 
    }
    
    public boolean setInProgressTaxiOrder(int taxiOrderId, Driver driver) {

    	TaxiOrder taxiOrder = getOrderById(taxiOrderId);
    	
    	if (taxiOrder.getStatus() != Status.ASSIGNED.getId()) {
    		return false;
    	}
    	
    	DriverCar driverCar = taxiOrder.getDriverCarId();
    	
    	if (driverCar == null) {
    		return false;
    	}
    	
    	if (driverCar.getDriverId() != driver.getId()) {
    		return false;
    	}
   
        taxiOrder.setStatus(Status.IN_PROGRESS);
        TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            dao.update(taxiOrder);
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    	
    	return true; 
    }
    
    public boolean setCompletedTaxiOrder(int taxiOrderId, Driver driver) {

    	TaxiOrder taxiOrder = getOrderById(taxiOrderId);
    	
    	if (taxiOrder.getStatus() != Status.IN_PROGRESS.getId()) {
    		return false;
    	}
    	
    	DriverCar driverCar = taxiOrder.getDriverCarId();
    	
    	if (driverCar == null) {
    		return false;
    	}
    	
    	if (driverCar.getDriverId() != driver.getId()) {
    		return false;
    	}
   
        taxiOrder.setStatus(Status.COMPLETED);
        TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            dao.update(taxiOrder);
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    	
    	return true; 
    }
    
    public Contacts createContacts(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can't be null");
        }
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

    public TaxiOrder getOrderById(int id) {
        TaxiOrderDAO dao = null;
        TaxiOrder taxiOrder = null;
        try {
            dao = new TaxiOrderDAO();
            taxiOrder = dao.get(id);
        } catch (NoSuchEntity e) {
            e.printStackTrace();
        } finally {
            if (dao != null) {
                dao.close();
            }
        }

        return taxiOrder;
    }

    public void refuseTaxiOrder(int orderId) {
        TaxiOrder taxiOrder = getOrderById(orderId);
        taxiOrder.setStatus(Status.REFUSED);
        TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            dao.update(taxiOrder);
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    public List<TaxiOrderHistory> getActiveTaxiOrders(int pageNumber, int pageSize, Driver driver) {
        TaxiOrderDAO dao = null;
        List<TaxiOrder> orders = null;
        try {
            dao = new TaxiOrderDAO();
            orders = dao.getActiveTaxiOrders(pageNumber, pageSize, driver.getId());
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
        List<TaxiOrderHistory> taxiOrderHistory = createTOHistory(orders);
        return taxiOrderHistory;
    }

    public List<TaxiOrderHistory> getCompletedTaxiOrders(int pageNumber, int pageSize, Driver driver) {
    	TaxiOrderDAO dao = null;
    	List<TaxiOrder> orders = null;
    	try {
    		dao = new TaxiOrderDAO();
    		orders = dao.getCompletedTaxiOrders(pageNumber, pageSize, driver.getId());
    	} finally {
    		if (dao != null) {
    			dao.close();
    		}
    	}
    	List<TaxiOrderHistory> taxiOrderHistory = createTOHistory(orders);
    	return taxiOrderHistory;
    }
    
    public List<TaxiOrderHistory> getTaxiOrderHistory(Integer pageNumber,
            int pageSize, User user, Status status) {
        TaxiOrderDAO dao = null;
        ContactsDAO daoC = null;
        List<TaxiOrder> orders = null;
        try {
            dao = new TaxiOrderDAO();
            daoC = new ContactsDAO();
            orders = dao.getTaxiOrderHistory(pageNumber, pageSize,
                    daoC.getByEmail(user.getEmail()), status);
        } finally {
            if (dao != null) {
                dao.close();
            }
            if (daoC != null) {
                daoC.close();
            }
        }
        List<TaxiOrderHistory> taxiOrderHistory = createTOHistory(orders);
        return taxiOrderHistory;
    }

    public List<TaxiOrderHistory> getTaxiOrderHistory(int pageNumber,
            int pageSize, User user) {
        return getTaxiOrderHistory(pageNumber, pageSize, user, null);
    }

    public void updateTaxiOrder(TaxiOrder taxiOrder) {
        TaxiOrderDAO taxiOrderDAO = null;
        try {
            taxiOrderDAO = new TaxiOrderDAO();
            taxiOrderDAO.update(taxiOrder);
        } finally {
            if (taxiOrderDAO != null) {
                taxiOrderDAO.close();
            }
        }
    }

    public void editTaxiOrderCustomer(int orderId, Address addFrom,
            Address addTo, Date orderTime, float distance, double price) {
        TaxiOrderDAO taxiOrderDAO = null;
        TaxiOrder taxiOrder = null;
        AddressDAO addressDAO = null;
        RouteDAO routeDAO = null;
        Route route = null;
        try {
            taxiOrderDAO = new TaxiOrderDAO();
            taxiOrder = taxiOrderDAO.get(orderId);
            addressDAO = new AddressDAO();
            route = taxiOrder.getRouteId();
            route.setDistance(distance);
            routeDAO = new RouteDAO();
            routeDAO.update(route);
            Address addressFrom = taxiOrder.getRouteId().getFromAddrId();
            Address addressTo = taxiOrder.getRouteId().getToAddrId();
            addressFrom.setAltitude(addFrom.getAltitude());
            addressFrom.setLongtitude(addFrom.getLongtitude());
            addressTo.setAltitude(addTo.getAltitude());
            addressTo.setLongtitude(addTo.getLongtitude());
            addressDAO.update(addressFrom);
            addressDAO.update(addressTo);
            taxiOrder.setOrderTime(orderTime);
            taxiOrder.setPrice(price);
            taxiOrderDAO.update(taxiOrder);
        } catch (NoSuchEntity e) {
            e.printStackTrace();
        } finally {
            if (routeDAO != null) {
                routeDAO.close();
            }
            if (taxiOrderDAO != null) {
                taxiOrderDAO.close();
            }
            if (addressDAO != null) {
                addressDAO.close();
            }
        }

    }

    public TaxiOrderHistory getOrderForEdit(TaxiOrder order) {
        TaxiOrderHistory toh = new TaxiOrderHistory(order);
        toh.setToAddr(getToAddr(toh));
        toh.setFromAddr(getFromAddr(toh));
        return toh;
    }

    private List<TaxiOrderHistory> createTOHistory(List<TaxiOrder> orders) {
        List<TaxiOrderHistory> listTOH = new ArrayList<>();
        for (TaxiOrder to : orders) {
            TaxiOrderHistory toh = new TaxiOrderHistory(to);
            toh.setToAddr(getToAddr(toh));
            toh.setFromAddr(getFromAddr(toh));
            listTOH.add(toh);
        }
        return listTOH;
    }

    private String getFromAddr(TaxiOrderHistory toh) {
        if (toh.getRouteId() != null) {
            Address a = toh.getRouteId().getFromAddrId();
            return toAddress(a.getAltitude(), a.getLongtitude());
        } else {
            return "";
        }
    }

    private String getToAddr(TaxiOrderHistory toh) {
        if (toh.getRouteId() != null) {
            Address a = toh.getRouteId().getToAddrId();
            return toAddress(a.getAltitude(), a.getLongtitude());
        } else {
            return "";
        }
    }

    private String toAddress(float lng, float alt) {
        MapBean mapBean = new MapBean();
        String to = "";
        try {
            to = mapBean.geodecodeAddress(lng, alt);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return to;
    }

    @Override
    public void setSessionContext(SessionContext ctx) throws EJBException,
            RemoteException {

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
