package com.netcracker.tss.web.servlet.admin;

import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Kyrylo Berehovyi on 20/04/2015.
 */

@WebServlet(urlPatterns = "/admin")
public class AdminHomeServlet extends HttpServlet {

    private static final Page page = Page.ADMIN_DASHBOARD_CONTENT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_DASHBOARD_CONTENT.getType());
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_DASHBOARD_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//
//        if ("newcar".equals(action)) {
//            CarDao carDao = new CarDao();
//            carDao.persist(new Car(req.getParameter("plate"), isOn(req.getParameter("avaliable")),
//                    Integer.parseInt(req.getParameter("category")), isOn(req.getParameter("animalable")),
//                    isOn(req.getParameter("wi-fi")), isOn(req.getParameter("conditioner"))));
////            List<Car> cars = carDao.getPage(1, 10);
////            carDao.close();
////
////            if(cars != null) {
////                req.setAttribute(ATTRIBUTE_CARS, cars);
////            }
//
//            redirectToCars(1, 10, req, resp);
//        } else if ("newdriver".equals(action)) {
//            String passStr = req.getParameter("password");
//            String confirmPassStr = req.getParameter("confirmPassword");
//
//            if(passStr.equals(confirmPassStr)) {
//
//                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//                String password  = encoder.encode(passStr);
//                RegistrationBean rb = new RegistrationBean();
//
//                Driver newDriver = new Driver(req.getParameter("drivername"),
//                        req.getParameter("email"),
//                        password,
//                        Category.valueOf(req.getParameter("category")),
//                        isOn(req.getParameter("available")),
//                        isOn(req.getParameter("isMale")),
//                        isOn(req.getParameter("smokes")));
//
//                if(rb.checkUser(newDriver)) {
//                    rb.registrate(newDriver);
//                } else {
//                    resp.sendRedirect("/add-driver.jsp");
//                    return;
//                }
//
//                redirectToDrivers(1, 10, req, resp);
//            } else {
//                resp.sendRedirect("/add-driver.jsp");
//            }
//
//        }
    }
    
//    private boolean isOn (String checkBoxText){
//        return "on".equals(checkBoxText);
//    }
//
//    private void redirectToCars(int pageNumber, int pageSize, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        CarDao carDao = new CarDao();
//        List<Car> cars = carDao.getPage(pageNumber, pageSize);
//        carDao.close();
//
//        req.setAttribute(ATTRIBUTE_CARS, cars);
//        req.getRequestDispatcher("/WEB-INF/views/admin/cars.jsp").forward(req, resp);
//    }
//
//    private void redirectToDrivers(int pageNumber, int pageSize, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        DriverDAO driverDAO = new DriverDAO();
//        List<Driver> drivers = driverDAO.getPage(pageNumber, pageSize);
//        driverDAO.close();
//
//        req.setAttribute("drivers_page", drivers);
//        req.getRequestDispatcher("/WEB-INF/views/admin/drivers.jsp").forward(req, resp);
//    }
}
