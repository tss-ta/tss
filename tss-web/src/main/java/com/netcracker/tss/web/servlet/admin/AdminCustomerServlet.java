package com.netcracker.tss.web.servlet.admin;

import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.entity.helper.Roles;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.BeansLocator;

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

/**
 * Created by Kyrylo Berehovyi on 25/04/2015.
 *
 * @author maks
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
        } else if ("search-users".equals(action)) {
            try {
                UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
                req.setAttribute("customers", userBeanLocal.searchCustomersByEmail(req.getParameter("email"), 1, 10));
                req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CUSTOMERS_CONTENT.getType());
                req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CUSTOMERS_CONTENT.getAbsolutePath());
                req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
            } catch (RuntimeException e) {
                Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                        "Can't show customers", e);
                req.getRequestDispatcher("/500.jsp").forward(req, resp);
            }
        } else {
            redirectToCustomers(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add-roles".equals(action)) {
            try {
                UserBeanLocal customerBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
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
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
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
        if (isOn(req.getParameter("banned"))) {
            roles.add(Roles.BANNED);
        }
        return roles;
    }
}
