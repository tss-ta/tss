package com.netcracker.tss.web.servlet;

import com.netcracker.dao.CarDao;
import com.netcracker.dao.DriverDAO;
import com.netcracker.entity.Car;
import com.netcracker.entity.Driver;

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

    public static final String ATTRIBUTE_CARS = "cars_page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Admin Page");
        String menu = req.getParameter("menu");
        String action = req.getParameter("action");
//        switch (menu){
//            case "car": car
//        }

        if ("addcar".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/addcar.jsp").forward(req, resp);
        }

        if ("cars".equals(menu)) {
            CarDao carDao = new CarDao();
            List<Car> cars = carDao.getPage(1, 10);
            carDao.close();

            req.setAttribute(ATTRIBUTE_CARS, cars);
            req.getRequestDispatcher("/WEB-INF/views/admin/cars.jsp").forward(req, resp);
        } else if ("reports".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/reports.jsp").forward(req, resp);
        } else if ("customers".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/customers.jsp").forward(req, resp);
        } else if ("drivers".equals(menu)) {
            DriverDAO driverDAO = new DriverDAO();
            List<Driver> drivers = driverDAO.getPage(1, 10);
            driverDAO.close();

            req.setAttribute("drivers_page", drivers);
            req.getRequestDispatcher("/WEB-INF/views/admin/drivers.jsp").forward(req, resp);
        } else if ("tariffs".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/tariffs.jsp").forward(req, resp);
        } else if ("groups".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/groups.jsp").forward(req, resp);
        } else {

            req.getRequestDispatcher("/WEB-INF/views/admin/cars.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        List<Car> cars = null;

        if ("newcar".equals(action)) {
            CarDao carDao = new CarDao();
            carDao.persist(new Car(req.getParameter("plate"), isOn(req.getParameter("avaliable")), 
                    Integer.parseInt(req.getParameter("category")), isOn(req.getParameter("animalable")),
                    isOn(req.getParameter("wi-fi")), isOn(req.getParameter("conditioner"))));
            cars = carDao.getPage(1, 10);
            carDao.close();
        }

        if(cars != null) {
            req.setAttribute(ATTRIBUTE_CARS, cars);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin/cars.jsp").forward(req, resp);
//        PrintWriter out = resp.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet controller</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>!!! " + 11111111 + "</h1>");
//            out.println("<h1>!!! " + action + "</h1>");
//            out.println("<h1>pl " + req.getParameter("plate") + "</h1>");
//            out.println("<h1>wifi " + req.getParameter("wi-fi") + "</h1>");
//            out.println("<h1>cond " + req.getParameter("conditioner") + "</h1>");
//            out.println("<h1>aval " + req.getParameter("avaliable") + "</h1>");
//                        out.println("<h1>anim " + req.getParameter("animalable") + "</h1>");
//            out.println("<h1>cat " + req.getParameter("category") + "</h1>");
//
//            out.println("<h3>!!! " + req.getParameterMap().toString() + "</h3>");
//            out.println("<h3>!!! " + req.getParameterNames().toString() + "</h3>");
//
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }
    }
    
    private boolean isOn (String checkBoxText){
        return "on".equals(checkBoxText);
    }
//    private int toInt (String number) {
//        return Integer.parseInt(number);
//    }

}
