package com.netcracker.tss.web.servlet.admin;

import com.netcracker.ejb.CustomerBeanLocal;
import com.netcracker.ejb.CustomerBeanLocalHome;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by Kyrylo Berehovyi on 25/04/2015.
 */
@WebServlet(urlPatterns = "/admin/customer")
public class AdminCustomerServlet extends HttpServlet {

//        public static final Page template = Page.ADMIN_TEMPLATE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectToGroups(req, resp);
    }

    private void redirectToGroups(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CustomerBeanLocal groupBeanLocal = getCustomerBean(req);
            req.setAttribute("customers", groupBeanLocal.getCustomers(1, 10));
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CUSTOMERS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CUSTOMERS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } catch (RuntimeException e) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't show customers", e);
            req.getRequestDispatcher("/500.jsp").forward(req, resp);
        }
    }

    private CustomerBeanLocal getCustomerBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            CustomerBeanLocalHome customerBeanLocalHome = (CustomerBeanLocalHome) context.lookup("java:app/tss-ejb/CustomerBean!com.netcracker.ejb.CustomerBeanLocalHome");
            return customerBeanLocalHome.create();
        } catch (NamingException ex) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/GroupBean!com.netcracker.ejb.GroupBeanLocal ", ex);
            throw new RuntimeException("Internal server error!");// maybe have to create custom exception?
        }
    }
}
