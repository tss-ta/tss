package com.netcracker.tss.web.servlet.admin;

import com.netcracker.dao.CarDao;
import com.netcracker.dao.DriverDAO;
import com.netcracker.ejb.*;
import com.netcracker.entity.Car;
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
 * Updated by Illia Rudenko on 26/04/2015.
 */

@WebServlet(urlPatterns = "/admin/driver")
public class AdminDriverServlet extends HttpServlet {


    public static final String ACTION_ADD_DRIVER = "adddriver";
    public static final String ACTION_ASSIGN_CAR = "assign";
    public static final String ACTION_UNASSIGN_CAR = "unassign";
    public static final String ACTION_EDIT_DRIVER = "editdriver";
    public static final String ACTION_DELETE_DRIVER = "deletedriver";

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

            redirectToAddDriver(req, resp);
            return;
        } else if(ACTION_EDIT_DRIVER.equals(action)) {

            redirectToEditDriver(req, resp);
            return;
        } else if(ACTION_DELETE_DRIVER.equals(action)) {

            getDriverBean().deleteDriver(Integer.valueOf(req.getParameter(PARAMETER_DRIVER_ID)));

            redirectToDrivers(1, 10, req, resp);
            return;
        } else if("assigncar".equals(action)) {
            int driverId = Integer.valueOf(req.getParameter(PARAMETER_DRIVER_ID));
            int carId = Integer.valueOf(req.getParameter(PARAMETER_CAR_ID));
            getDriverBean().assignCar(driverId, carId);

            redirectToEditDriver(req, resp);
            return;
        }

        redirectToDrivers(1, 10, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("newdriver".equals(action)) {
            Driver newDriver = createDriverFromRequest(req, resp);

            if(newDriver != null) {

                getDriverBean().addDriver(newDriver);

                redirectToDrivers(1, 10, req, resp);
            } else {
                resp.sendRedirect(PAGE_ADD_DRIVER);
            }

        } else if(ACTION_EDIT_DRIVER.equals(action)) {
            Driver driver = getDriverBean().getDriver(Integer.valueOf(req.getParameter(PARAMETER_DRIVER_ID)));

            if(driver != null) {
                getDriverBean().editDriver(updateDriverFromRequest(driver, req, resp));
            }

            redirectToDrivers(1, 10, req, resp);
        } else if(ACTION_ASSIGN_CAR.equals(action)) {

            req.setAttribute(ACTION_ASSIGN_CAR, true);
            req.setAttribute(PARAMETER_DRIVER_ID, req.getParameter(PARAMETER_DRIVER_ID));

            redirectToCars(1, 10, req, resp);
        } else if(ACTION_UNASSIGN_CAR.equals(action)) {

            int driverId = Integer.valueOf(req.getParameter(PARAMETER_DRIVER_ID));
            int carId = Integer.valueOf(req.getParameter(PARAMETER_CAR_ID));

            getDriverBean().unassignCar(driverId, carId);
            redirectToEditDriver(req, resp);
        }
    }

    private Driver createDriverFromRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        } else {
            redirectToAddDriver(req, resp);
            return null;
        }
    }

    private Driver updateDriverFromRequest(Driver driver, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(driver != null) {

            driver.setUsername(req.getParameter(PARAMETER_DRIVER_NAME));
            driver.setCategory(Category.valueOf(req.getParameter(PARAMETER_CATEGORY)));
            driver.setAvailable(isOn(req.getParameter(PARAMETER_AVAILABLE)));
            driver.setMale(isOn(req.getParameter(PARAMETER_IS_MALE)));
            driver.setSmokes(isOn(req.getParameter(PARAMETER_SMOKES)));

            return driver;
        } else {
            redirectToAddDriver(req, resp);
        }
        return null;
    }


    private void redirectToDrivers(int pageNumber, int pageSize, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Driver> drivers = getDriverBean().getDriverPage(pageNumber, pageSize);

        req.setAttribute(RequestAttribute.DRIVER_LIST.getName(), drivers);

        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_DRIVERS_CONTENT.getType());
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_DRIVERS_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
    }

    private void redirectToCars(int pageNumber, int pageSize, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDao carDao = new CarDao();
        List<Car> cars = carDao.getPage(pageNumber, pageSize);
        carDao.close();

        req.setAttribute(RequestAttribute.CAR_LIST.getName(), cars);
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CARS_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
    }

    private void redirectToAddDriver(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_DRIVER_CONTENT.getType());
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_DRIVER_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
    }

    private void redirectToEditDriver(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Driver driver = getDriverBean().getDriver(Integer.valueOf(req.getParameter(PARAMETER_DRIVER_ID)));
        req.setAttribute(ATTRIBUTE_DRIVER, driver);

        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_DRIVER_CONTENT.getType());
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_DRIVER_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
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
                        "Can't find driverBeanLocalHome with name java:app/tss-ejb/DriverBean!com.netcracker.ejb.DriverLocalHome", ex);
                throw new RuntimeException("Internal server error!" +
                        "Can't find driverBeanLocalHome with name java:app/tss-ejb/DriverBean!com.netcracker.ejb.DriverLocalHome");// maybe have to create custom exception?
            } catch (ClassCastException ex){
                Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                        "Can't find driverBeanLocalHome with name java:app/tss-ejb/DriverBean!com.netcracker.ejb.DriverLocalHome", ex);
                throw new RuntimeException("Internal server error!" +
                        "Can't find driverBeanLocalHome with name java:app/tss-ejb/DriverBean!com.netcracker.ejb.DriverLocalHome");
            }
        } else {
            return drvLocal;
        }
    }
}
