package com.netcracker.tss.web.servlet.customer;

import java.io.IOException;
import java.util.Date;
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

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.netcracker.dao.UserDAO;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocalHome;
import com.netcracker.entity.Address;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;

@WebServlet(urlPatterns = "/customer/order")
public class CustomerOrderTaxiServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = findCurrentUser();
		TaxiOrderBeanLocal taxiOrderBeanLocal = getTaxiOrderBean(req);
		Route route = new Route("Guest Route");
		float altitude = 0;// ToDo
		float longtitude = 0;// ToDo
		Address addFrom = new Address(altitude, longtitude);
		Address addTo = new Address(altitude, longtitude);
		TaxiOrder taxiOrder = new TaxiOrder();
		taxiOrder.setBookingTime(new Date());
		taxiOrderBeanLocal.addTaxiOrder(user, route, addFrom, addTo, taxiOrder);
		req.setAttribute("added", "success");
		req.getRequestDispatcher("/WEB-INF/views/customer/ordertaxi.jsp")
				.forward(req, resp);
	}

	private User findCurrentUser() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDAO userDao = new UserDAO();
		User user = userDao.getByEmail(userDetails.getUsername());
		userDao.close();
		return user;
	}

	private TaxiOrderBeanLocal getTaxiOrderBean(HttpServletRequest req) {
		Context context;
		try {
			context = new InitialContext();
			TaxiOrderBeanLocalHome taxiOrderBeanLocalHome = (TaxiOrderBeanLocalHome) context
					.lookup("java:app/tss-ejb/TaxiOrderBean!com.netcracker.ejb.TaxiOrderBeanLocalHome");
			return taxiOrderBeanLocalHome.create();
		} catch (NamingException ex) {
			Logger.getLogger(AdminGroupServlet.class.getName())
					.log(Level.SEVERE,
							"Can't find taxiOrderBean with name java:app/tss-ejb/TaxiOrderBean!com.netcracker.ejb.TaxiOrderBeanLocalHome ",
							ex);
			throw new RuntimeException("Internal server error!");// maybe have
																	// to create
																	// custom
																	// exception?
		}
	}

}
