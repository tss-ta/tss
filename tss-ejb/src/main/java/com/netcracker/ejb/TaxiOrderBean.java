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
import com.netcracker.entity.helper.TaxiOrderHistory;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

/**
 *
 * @author Виктор
 */
public class TaxiOrderBean implements SessionBean {

	public void addTaxiOrder(User user, Route route, Address addFrom,
			Address addTo, TaxiOrder taxiOrder) {
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
			taxiOrder.setUserId(userFromDB);
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

	public List<TaxiOrderHistory> getTaxiOrderHistory(int pageNumber,
			int pageSize, User user) {
		TaxiOrderDAO dao = null;
		List<TaxiOrder> orders = null;
		try {
			dao = new TaxiOrderDAO();
			orders = dao.getTaxiOrderHistory(pageNumber, pageSize, user);
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
		List<TaxiOrderHistory> taxiOrderHistory = createTOHistory(orders);
		return taxiOrderHistory;
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
		Address a = toh.getRouteId().getFromAddrId();
		return toAddress(a.getAltitude(),a.getLongtitude());
	}

	private String getToAddr(TaxiOrderHistory toh) {
		Address a = toh.getRouteId().getToAddrId();
		return toAddress(a.getAltitude(),a.getLongtitude());
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
