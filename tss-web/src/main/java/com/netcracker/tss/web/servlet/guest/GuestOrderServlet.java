package com.netcracker.tss.web.servlet.guest;

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

import com.netcracker.ejb.GroupBeanLocal;
import com.netcracker.ejb.GroupBeanLocalHome;
import com.netcracker.ejb.RegistrationBeanLocal;
import com.netcracker.ejb.RegistrationBeanLocalHome;
import com.netcracker.ejb.TaxiOrderBean;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocalHome;
import com.netcracker.entity.Address;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.DateParser;

@WebServlet(urlPatterns = "/guest/order")
public class GuestOrderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		User user = new User(name, email);
		RegistrationBeanLocal rb = getRegistrationBean(req);
		if (!rb.isUserExist(user)) {
			rb.registrate(user);
		}
		else{
			req.setAttribute("error", "email");
			req.getRequestDispatcher("/WEB-INF/views/customer/guest.jsp").forward(
					req, resp);
		}
		TaxiOrderBeanLocal taxiOrderBeanLocal = getTaxiOrderBean(req);
		Route route = new Route("Guest Route");
		float altitude = 0;// ToDo
		float longtitude = 0;// ToDo
		Address addFrom = new Address(altitude, longtitude);
		Address addTo = new Address(altitude, longtitude);
		TaxiOrder taxiOrder = new TaxiOrder();
		taxiOrder.setBookingTime(new Date());
		Date orderTime = DateParser.parseDate(req);
		orderTime.setYear(new Date().getYear());
		taxiOrder.setOrderTime(orderTime);
		taxiOrderBeanLocal.addTaxiOrder(user, route, addFrom, addTo, taxiOrder);
		req.setAttribute("added", "success");
		req.getRequestDispatcher("/WEB-INF/views/customer/guest.jsp").forward(
				req, resp);
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

	private RegistrationBeanLocal getRegistrationBean(HttpServletRequest req) {
		Context context;
		try {
			context = new InitialContext();
			RegistrationBeanLocalHome regBeanLocalHome = (RegistrationBeanLocalHome) context
					.lookup("java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome");
			return regBeanLocalHome.create();
		} catch (NamingException ex) {
			Logger.getLogger(AdminGroupServlet.class.getName())
					.log(Level.SEVERE,
							"Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome",
							ex);
			throw new RuntimeException(
					"Internal server error!"
							+ "Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome");// maybe
																																						// have
																																						// to
																																						// create
																																						// custom
																																						// exception?
		} catch (ClassCastException ex) {
			Logger.getLogger(AdminGroupServlet.class.getName())
					.log(Level.SEVERE,
							"Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome",
							ex);
			throw new RuntimeException(
					"Internal server error!"
							+ "Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome");
		}
	}
}
