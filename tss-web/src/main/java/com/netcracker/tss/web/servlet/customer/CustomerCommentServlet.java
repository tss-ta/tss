package com.netcracker.tss.web.servlet.customer;

import java.io.IOException;
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

import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocalHome;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;

/**
 * Author Stanislav Zabielin
 */
@WebServlet("/customer/comment")
public class CustomerCommentServlet extends HttpServlet {

	public static final String TAXI_ORDER_ID = "taxiOrderId";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int taxiOrderId = 0;
		if (request.getParameter("addComment") != null) {
			comment(request);
			taxiOrderId = ((TaxiOrder)request.getSession()
					.getAttribute("commentTaxiOrder")).getId();
		} else
			taxiOrderId = Integer.parseInt(request.getParameter(TAXI_ORDER_ID));
		TaxiOrderBeanLocal taxiOrderBean = getTaxiOrderBean(request);
		TaxiOrder taxiOrder = taxiOrderBean.getOrderById(taxiOrderId);
		request.getSession().setAttribute("commentTaxiOrder", taxiOrder);
		request.setAttribute("taxiOrder", taxiOrder);
		request.setAttribute("pageContent", "content/comments.jsp");
		request.getRequestDispatcher(
				"/WEB-INF/views/customer/customer-template.jsp").forward(
				request, response);
	}

	private void comment(HttpServletRequest request) {
		int taxiOrderId = ((TaxiOrder)request.getSession()
				.getAttribute("commentTaxiOrder")).getId();
		TaxiOrderBeanLocal taxiOrderBean = getTaxiOrderBean(request);
		TaxiOrder taxiOrder = taxiOrderBean.getOrderById(taxiOrderId);
		taxiOrder.setComment(request.getParameter("userComment"));
		taxiOrderBean.updateTaxiOrder(taxiOrder);
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
