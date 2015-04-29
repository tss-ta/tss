package com.netcracker.tss.web.servlet.admin;

import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.ejb.UserBeanLocalHome;
import com.netcracker.entity.helper.Roles;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        String action = req.getParameter("action");
        if ("add-role".equals(action)) {
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_ROLES_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } else {
            redirectToCustomers(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add-roles".equals(action)) {
            try {
                UserBeanLocal customerBeanLocal = getUserBean(req);
                customerBeanLocal.editRoles(Integer.parseInt(req.getParameter("id")), getRoles(req));
//                redirectToCustomers(req, resp);
            } catch (RuntimeException e) {
                req.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "Can't edit roles");
//                redirectToCustomers(req, resp);
            }
        }
        redirectToCustomers(req, resp);
    }

    private void redirectToCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserBeanLocal userBeanLocal = getUserBean(req);
            req.setAttribute("customers", userBeanLocal.getCustomers(1, 10));
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CUSTOMERS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CUSTOMERS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } catch (RuntimeException e) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't show customers", e);
            req.getRequestDispatcher("/500.jsp").forward(req, resp);
        }
    }

    private boolean isOn(String checkBoxText) {
        return "on".equals(checkBoxText);
    }

    private List<Roles> getRoles(HttpServletRequest req) {
        List<Roles> roles = new ArrayList<>();
        if (isOn(req.getParameter("customer"))) {
            roles.add(Roles.CUSTOMER);
        } 
        if (isOn(req.getParameter("admin"))) {
            roles.add(Roles.ADMIN);
        } 
        if (isOn(req.getParameter("driver"))) {
            roles.add(Roles.DRIVER);
        }
        return roles;
    }

    private UserBeanLocal getUserBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            UserBeanLocalHome customerBeanLocalHome = (UserBeanLocalHome) context.lookup("java:app/tss-ejb/UserBean!com.netcracker.ejb.UserBeanLocalHome");
            return customerBeanLocalHome.create();
        } catch (NamingException ex) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/UserBean!com.netcracker.ejb.UserBeanLocalHome", ex);
            throw new RuntimeException("Internal server error!");// maybe have to create custom exception?
        }
    }
}
