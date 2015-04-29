package com.netcracker.tss.web.servlet.customer;

import java.io.IOException;
import java.util.ArrayList;
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


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.netcracker.dao.UserDAO;
import com.netcracker.ejb.MapBeanLocal;
import com.netcracker.ejb.MapBeanLocalHome;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocalHome;
import com.netcracker.entity.Address;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.TaxiOrderHistory;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;

/**
 * Created by Stanislav Zabielin
 */

@WebServlet(urlPatterns = "/customer/history")
public class CustomerOrderTaxiHistoryServlet extends HttpServlet {

	private static final int pageSize = 10;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer pageNumber = updatePageNumber(req);
		getServletContext().setAttribute("pageNumber", pageNumber);
		List<TaxiOrderHistory> list = getHistory(pageNumber, req);
		req.setAttribute("history", list);
		req.getRequestDispatcher("/WEB-INF/views/customer/home-customer.jsp")
				.forward(req, resp);
	}

	private List<TaxiOrderHistory> getHistory(Integer pageNumber,
			HttpServletRequest req) {
		TaxiOrderBeanLocal taxiOrderBeanLocal = getTaxiOrderBean(req);
		List<TaxiOrderHistory> list = taxiOrderBeanLocal.getTaxiOrderHistory(
				pageNumber, pageSize, findCurrentUser());
		if (list.size() == 0 && pageNumber>1) {
			pageNumber--;
			getServletContext().setAttribute("pageNumber", pageNumber);
			list = taxiOrderBeanLocal
					.getTaxiOrderHistory(pageNumber, pageSize,
							findCurrentUser());
		}
		return list;
	}

	private Integer updatePageNumber(HttpServletRequest req) {
		Integer pageNumber = (Integer) getServletContext().getAttribute(
				"pageNumber");
		if (pageNumber == null)
			pageNumber = 1;
		if (req.getParameter("previous") != null && pageNumber > 1)
			pageNumber--;
		else if (req.getParameter("next") != null) {
			pageNumber++;
		}
		return pageNumber;
	}

	private User findCurrentUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
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
