/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.servlet.customer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.netcracker.dao.UserDAO;
import com.netcracker.ejb.ConveyCorpServiceBeanLocal;
import com.netcracker.ejb.ConveyCorpServiceBeanLocalHome;
import com.netcracker.ejb.MapBeanLocal;
import com.netcracker.ejb.MapBeanLocalHome;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocalHome;
import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.ejb.UserBeanLocalHome;
import com.netcracker.entity.Address;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.AdditionalParameters;
import com.netcracker.tss.web.util.DateParser;
import com.netcracker.tss.web.util.UserUtils;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;

/**
 *
 * @author Lis
 */
@WebServlet(urlPatterns = "/customer/cceService")
public class CustomerCceServiceServlet extends HttpServlet {

  @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		if (req.getParameter("addTo") != null
				|| req.getParameter("addFrom") != null) {
			addAddress(req);
			resp.sendRedirect("/customer/cceServicePage");
		} else if (req.getParameter("deleteTo") != null
				|| req.getParameter("deleteFrom") != null) {
			deleteAddress(req);
			resp.sendRedirect("/customer/cceServicePage");
		} else {
			User user = findCurrentUser();
			ConveyCorpServiceBeanLocal taxiConveyCorpServiceBeanLocal = getConveyCorpServiceBean(req);
                        String[] fromListAddr= req.getParameterValues("fromList");
                        if(fromListAddr!=null&&fromListAddr.length>0){
                        List<Address> addFrom=new ArrayList<Address>();
                        for(String fa:fromListAddr){
                           addFrom.add(toAddress(req.getParameter("fromAddr"), req));
                        }
                         String toAddr=req.getParameter("toAddr");
			Address addTo = toAddress( toAddr, req);
			TaxiOrder taxiOrder = new TaxiOrder(AdditionalParameters.taxiOrderAddParameters(req));
			taxiOrder.setBookingTime(new Date());
			Date orderTime = DateParser.parseDate(req);
			orderTime.setYear(new Date().getYear());
			taxiOrder.setOrderTime(orderTime);
			Integer latestTOId =taxiConveyCorpServiceBeanLocal.addCorpService(user, addFrom, addTo,taxiOrder); 
		        req.setAttribute("taxiOrderId", latestTOId);
		        req.setAttribute("pageContent", "content/confirmation.jsp");
			req.getRequestDispatcher(
					"/WEB-INF/views/customer/customer-template.jsp").forward(
					req, resp);
                        }else{
                          resp.sendRedirect("/customer/cceServicePage");  
                        }
		}
	}

	private void deleteAddress(HttpServletRequest req) {
		String addr = null;
		if (req.getParameter("deleteFrom") != null)
		{
			addr = req.getParameter("pers_addr");
		}
		else if (req.getParameter("deleteTo") != null)
			addr = req.getParameter("pers_addr_to");
		if (addr != null) {
			UserBeanLocal userBeanLocal = getUserBean(req);
			userBeanLocal.removeFromPersonalList(UserUtils.findCurrentUser(), addr);
		}
	}

	private void addAddress(HttpServletRequest req) {
		String addr = null;
		if (req.getParameter("fromAddr") != null)
			addr = req.getParameter("fromAddr");
		else if (req.getParameter("toAddr") != null)
			addr = req.getParameter("toAddr");
		if (addr != null) {
			UserBeanLocal userBeanLocal = getUserBean(req);
			userBeanLocal.addToPersonalList(UserUtils.findCurrentUser(), addr);
		}
	}

	private Address toAddress(String addr, HttpServletRequest req) {
		MapBeanLocal mapBeanLocal = getMapBean(req);
		double[] to = { 0, 0 };
		try {
			to = mapBeanLocal.geocodeAddress(addr);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return new Address((float) to[1], (float) to[0]);
	}

	private User findCurrentUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		UserDAO userDao = new UserDAO();
		User user = userDao.getByEmail(userDetails.getUsername());
		userDao.close();
		return user;
	}

	private ConveyCorpServiceBeanLocal getConveyCorpServiceBean(HttpServletRequest req) {
		Context context;
		try {
			context = new InitialContext();
			ConveyCorpServiceBeanLocalHome taxiConveyCorpServiceBeanLocalHome = (ConveyCorpServiceBeanLocalHome) context
					.lookup("java:app/tss-ejb/ConveyCorpServiceBean!com.netcracker.ejb.ConveyCorpServiceBeanLocalHome");
			
                        return  taxiConveyCorpServiceBeanLocalHome.create();
		} catch (NamingException ex) {
			Logger.getLogger(AdminGroupServlet.class.getName())
					.log(Level.SEVERE,
							"Can't find ConveyCorpServiceBean with name java:app/tss-ejb/TaxiOrderBean!com.netcracker.ejb.ConveyCorpServiceBeanLocalHome ",
							ex);
			throw new RuntimeException("Internal server error!");// maybe have
			// to create
			// custom
			// exception?
		}
	}

	private MapBeanLocal getMapBean(HttpServletRequest req) {
		Context context;
		try {
			context = new InitialContext();
			MapBeanLocalHome mapBeanLocalHome = (MapBeanLocalHome) context
					.lookup("java:app/tss-ejb/MapBean!com.netcracker.ejb.MapBeanLocalHome");
			return mapBeanLocalHome.create();
		} catch (NamingException ex) {
			Logger.getLogger(AdminGroupServlet.class.getName())
					.log(Level.SEVERE,
							"Can't find taxiOrderBean with name java:app/tss-ejb/MapBean!com.netcracker.ejb.MapBeanLocalHome ",
							ex);
			throw new RuntimeException("Internal server error!");// maybe have
			// to create
			// custom
			// exception?
		}
	}

	private UserBeanLocal getUserBean(HttpServletRequest req) {
		Context context;
		try {
			context = new InitialContext();
			UserBeanLocalHome userBeanLocalHome = (UserBeanLocalHome) context
					.lookup("java:app/tss-ejb/UserBean!com.netcracker.ejb.UserBeanLocalHome");
			return userBeanLocalHome.create();
		} catch (NamingException ex) {
			Logger.getLogger(AdminGroupServlet.class.getName())
					.log(Level.SEVERE,
							"Can't find userBean with name java:app/tss-ejb/UserBean!com.netcracker.ejb.UserBeanLocalHome ",
							ex);
			throw new RuntimeException("Internal server error!");// maybe have
																	// to create
																	// custom
																	// exception?
		}
	}

}
