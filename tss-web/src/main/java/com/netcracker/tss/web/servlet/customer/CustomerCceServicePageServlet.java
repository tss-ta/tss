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

import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.ejb.UserBeanLocalHome;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.UserUtils;

/**
 *
 * @author Lis
 */
@WebServlet(urlPatterns = "/customer/cceServicePage")
public class CustomerCceServicePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().removeAttribute("taxiOrder");
        UserBeanLocal userBeanLocal = getUserBean(req);
        req.setAttribute("personal_addr", userBeanLocal.toPersonalAddress(UserUtils.findCurrentUser()));
        req.setAttribute("pageContent", "content/customer-cceService.jsp");
        req.setAttribute("pageType", "cceService");
        req.getRequestDispatcher("/WEB-INF/views/customer/customer-template.jsp")
                .forward(req, resp);
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
}
