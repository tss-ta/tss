package com.netcracker.tss.web.servlet.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Stanislav Zabielin
 */
@WebServlet(urlPatterns = "/customer/orderpage")
public class CustomerOrderTaxiPageServlet extends HttpServlet {
	
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setAttribute("pageContent", "content/customer-order.jsp");
		 req.setAttribute("pageType", "orderpage");
			req.getRequestDispatcher("/WEB-INF/views/customer/customer-template.jsp")
					.forward(req, resp);
	    }

}
