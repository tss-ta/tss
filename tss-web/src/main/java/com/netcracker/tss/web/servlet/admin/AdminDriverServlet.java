package com.netcracker.tss.web.servlet.admin;

import com.netcracker.dao.DriverDAO;
import com.netcracker.ejb.*;
import com.netcracker.entity.Driver;
import com.netcracker.entity.driverUtil.Category;
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
 * Updated by Illia Rudenko on 26/04/2015.
 */

@WebServlet(urlPatterns = "/admin/driver")
public class AdminDriverServlet extends HttpServlet {


    public static final String ACTION_ADD_DRIVER = "adddriver";
    public static final String ACTION_EDIT_DRIVER = "editdriver";

    public static final String PAGE_ADD_DRIVER = "/WEB-INF/views/admin/add-driver.jsp";

    public static final String ATTRIBUTE_DRIVER = "driver";

    public static final String PARAMETER_DRIVER_ID = "driverid";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_CONFIRM_PASSWORD = "confirmPassword";
    public static final String PARAMETER_DRIVER_NAME = "drivername";
    public static final String PARAMETER_DRIVER_EMAIL = "email";
    public static final String PARAMETER_CATEGORY = "category";
    public static final String PARAMETER_AVAILABLE = "available";
    public static final String PARAMETER_IS_MALE = "ismale";
    public static final String PARAMETER_SMOKES = "smokes";
    public static final String PARAMETER_CAR_ID = "carid";



    private DriverLocal drvLocal;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(ACTION_ADD_DRIVER.equals(action)) {
            req.getRequestDispatcher(PAGE_ADD_DRIVER).forward(req, resp);

        } else if(ACTION_EDIT_DRIVER.equals(action)) {

            Driver driver = getDriverBean().getDriver(Integer.valueOf(req.getParameter(PARAMETER_DRIVER_ID)));
            req.setAttribute(ATTRIBUTE_DRIVER, driver);

            req.getRequestDispatcher(PAGE_ADD_DRIVER).forward(req, resp);
        } else {
            redirectToDrivers(1, 10, req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("newdriver".equals(action)) {
            Driver newDriver = createDriverFromRequest(req);

            if(newDriver != null) {

                getDriverBean().addDriver(newDriver);

                redirectToDrivers(1, 10, req, resp);
            } else {
                resp.sendRedirect(PAGE_ADD_DRIVER);
            }

        } if(ACTION_EDIT_DRIVER.equals(action)) {

        }
    }

    private Driver createDriverFromRequest(HttpServletRequest req) {
        String passStr = req.getParameter(PARAMETER_PASSWORD);
        String confirmPassStr = req.getParameter(PARAMETER_CONFIRM_PASSWORD);

        Driver newDriver;
        if(passStr.equals(confirmPassStr)) {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(passStr);


            newDriver = new Driver(req.getParameter(PARAMETER_DRIVER_NAME),
                    req.getParameter(PARAMETER_DRIVER_EMAIL),
                    password,
                    Category.valueOf(req.getParameter(PARAMETER_CATEGORY)),
                    isOn(req.getParameter(PARAMETER_AVAILABLE)),
                    isOn(req.getParameter(PARAMETER_IS_MALE)),
                    isOn(req.getParameter(PARAMETER_SMOKES)));

            return newDriver;
        }
        return null;
    }


    private void redirectToDrivers(int pageNumber, int pageSize, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Driver> drivers = getDriverBean().getDriverPage(pageNumber, pageSize);

        req.setAttribute("drivers_page", drivers);
        req.getRequestDispatcher("/WEB-INF/views/admin/drivers.jsp").forward(req, resp);
    }

    private boolean isOn (String checkBoxText){
        return "on".equals(checkBoxText);
    }

    private DriverLocal getDriverBean() {

        if(drvLocal == null) {
            try {
                Context context = new InitialContext();
                DriverLocalHome regBeanLocalHome = (DriverLocalHome) context.lookup("java:app/tss-ejb/DriverBean!com.netcracker.ejb.DriverLocalHome");
                drvLocal = regBeanLocalHome.create();
                return drvLocal;
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
        } else {
            return drvLocal;
        }
    }
}
