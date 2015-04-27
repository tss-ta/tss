package com.netcracker.tss.web.servlet.admin;

import com.netcracker.dao.DriverDAO;
import com.netcracker.ejb.RegistrationBean;
import com.netcracker.ejb.RegistrationBeanLocal;
import com.netcracker.ejb.RegistrationBeanLocalHome;
import com.netcracker.entity.Driver;
import com.netcracker.entity.helper.Category;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by Kyrylo Berehovyi on 25/04/2015.
 */

@WebServlet(urlPatterns = "/admin/driver")
public class AdminDriverServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_DRIVERS_CONTENT.getType());

        String action = req.getParameter("action");
        if("adddriver".equals(action)) {
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_DRIVER_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
            return;
        } else {
            redirectToDrivers(1, 10, req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_DRIVERS_CONTENT.getType());
        String action = req.getParameter("action");
        if ("newdriver".equals(action)) {
            String passStr = req.getParameter("password");
            String confirmPassStr = req.getParameter("confirmPassword");

            if(passStr.equals(confirmPassStr)) {

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String password  = encoder.encode(passStr);
                RegistrationBean rb = new RegistrationBean();

                Driver newDriver = new Driver(req.getParameter("drivername"),
                        req.getParameter("email"),
                        password,
                        Category.valueOf(req.getParameter("category")),
                        isOn(req.getParameter("available")),
                        isOn(req.getParameter("isMale")),
                        isOn(req.getParameter("smokes")));

                if(!rb.isUserExist(newDriver)) {
                    rb.registrate(newDriver);
                } else {
                    req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_DRIVER_CONTENT.getAbsolutePath());
                    req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);

                    return;
                }

                redirectToDrivers(1, 10, req, resp);
            } else {
                req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_DRIVER_CONTENT.getAbsolutePath());
                req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);

            }

        }
    }

    private void redirectToDrivers(int pageNumber, int pageSize, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DriverDAO driverDAO = new DriverDAO();
        List<Driver> drivers = driverDAO.getPage(pageNumber, pageSize);
        driverDAO.close();

        req.setAttribute(RequestAttribute.DRIVER_LIST.getName(), drivers);
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_DRIVERS_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
    }

    private boolean isOn (String checkBoxText){
        return "on".equals(checkBoxText);
    }
        private RegistrationBeanLocal getRegistrationBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            RegistrationBeanLocalHome regBeanLocalHome = (RegistrationBeanLocalHome) context.lookup("java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome");
            return regBeanLocalHome.create();
        } catch (NamingException ex) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome", ex);
            throw new RuntimeException("Internal server error!" + 
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome");// maybe have to create custom exception?
        } catch (ClassCastException ex){
                        Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome", ex);
            throw new RuntimeException("Internal server error!" + 
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome");
        }
    }
}
