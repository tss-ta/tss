/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.servlet.customer;

import com.netcracker.dao.UserDAO;

import com.netcracker.ejb.SoberServiceBeanLocalHome;
import com.netcracker.ejb.SoberServiceBeanLocal;
import com.netcracker.ejb.ConveyCorpServiceBeanLocal;
import com.netcracker.ejb.ConveyCorpServiceBeanLocalHome;
import com.netcracker.ejb.MapBeanLocal;
import com.netcracker.ejb.MapBeanLocalHome;
import com.netcracker.ejb.PriceBeanLocal;
import com.netcracker.ejb.PriceBeanLocalHome;
import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.ejb.UserBeanLocalHome;
import com.netcracker.entity.Address;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.AdditionalParametersForSoberService;
import com.netcracker.tss.web.util.DateParser;
import com.netcracker.tss.web.util.UserUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

/**
 *
 * @author Lis
 */
@WebServlet(urlPatterns = "/customer/soberService")
public class CustomerSoberServiceServlet extends HttpServlet {

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
                        PriceBeanLocal priceBean = getPriceBean(req);
			SoberServiceBeanLocal taxiSoberServiceBeanLocal = getSoberServiceBean(req);
                             float distance = 0;
                             double price = 0;
                        String fromAddr=req.getParameter("fromAddr");
                        String toAddr=req.getParameter("toAddr");
		        Address addTo = toAddress( toAddr, req);
                        Address addFrom= toAddress( fromAddr, req);
                        Route r=new Route(user.getUsername() + " Route");
                        MapBeanLocal mapBean = getMapBean(req);
                           try{
                           distance = mapBean.calculateDistance(fromAddr,toAddr);
                           } catch (JSONException | IOException e) {
                            // TODO Auto-generated catch block
                           e.printStackTrace();
                           }
                           r.setDistance(distance);
                        TaxiOrder taxiOrder = new TaxiOrder(AdditionalParametersForSoberService.taxiOrderAddParameters(req));
			taxiOrder.setBookingTime(new Date());
			Date orderTime = DateParser.parseDate(req);
			orderTime.setYear(new Date().getYear());
			taxiOrder.setOrderTime(orderTime);
                        
                        if ("".equals(req.getParameter("price"))) {
                           price = priceBean.calculatePrice(distance,
                        DateParser.parseDate(req),taxiOrder, UserUtils.findCurrentUser());
                       } else {
                         price = Double.parseDouble(req.getParameter("price"));
                        }
			taxiOrder.setPrice(price);
                        
			Integer latestTOId =taxiSoberServiceBeanLocal.addSoberService(user, r, addFrom, addTo,taxiOrder); 
		        req.setAttribute("taxiOrderId", latestTOId);
		        req.setAttribute("pageContent", "content/confirmation.jsp");
			req.getRequestDispatcher(
					"/WEB-INF/views/customer/customer-template.jsp").forward(
					req, resp);
                        
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

	private SoberServiceBeanLocal getSoberServiceBean(HttpServletRequest req) {
		Context context;
		try {
			context = new InitialContext();
			SoberServiceBeanLocalHome taxiSoberServiceBeanLocalHome = (SoberServiceBeanLocalHome) context
					.lookup("java:app/tss-ejb/SoberServiceBean!com.netcracker.ejb.SoberServiceBeanLocalHome");
			
                        return  taxiSoberServiceBeanLocalHome.create();
		} catch (NamingException ex) {
			Logger.getLogger(AdminGroupServlet.class.getName())
					.log(Level.SEVERE,
							"Can't find SoberServiceBean with name java:app/tss-ejb/SoberServiceBean!com.netcracker.ejb.SoberServiceBeanLocalHome ",
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
        private PriceBeanLocal getPriceBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            PriceBeanLocalHome priceBeanLocalHome = (PriceBeanLocalHome) context
                    .lookup("java:app/tss-ejb/PriceBean!com.netcracker.ejb.PriceBeanLocalHome");
            return priceBeanLocalHome.create();
        } catch (NamingException ex) {
            throw new RuntimeException("Internal server error!");// maybe have
            // to create
            // custom
            // exception?
        }
    }
}
