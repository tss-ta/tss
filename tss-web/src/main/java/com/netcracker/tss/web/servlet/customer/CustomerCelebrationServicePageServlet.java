package com.netcracker.tss.web.servlet.customer;

import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.tss.web.util.UserUtils;
import com.netcracker.util.BeansLocator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Illia Rudenko
 */
@WebServlet(urlPatterns = "/customer/celebrServicePage")
public class CustomerCelebrationServicePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("taxiOrder");
        UserBeanLocal userBeanLocal = BeansLocator.getInstance().getUserBean();
        req.setAttribute("personal_addr", userBeanLocal.toPersonalAddress(UserUtils.findCurrentUser()));
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.CUSTOMER_CELEBRATION_SERVICE_CONTENT.getAbsolutePath());
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.CUSTOMER_CELEBRATION_SERVICE_CONTENT.getType());
        req.getRequestDispatcher("/WEB-INF/views/customer/customer-template.jsp")
                .forward(req, resp);
    }
}
