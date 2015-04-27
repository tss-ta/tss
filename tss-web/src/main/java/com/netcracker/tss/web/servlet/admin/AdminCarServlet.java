package com.netcracker.tss.web.servlet.admin;

import com.netcracker.dao.CarDao;
import com.netcracker.entity.Car;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kyrylo Berehovyi on 25/04/2015.
 */

@WebServlet(urlPatterns = "/admin/car")
public class AdminCarServlet extends HttpServlet {

    public static final Page template = Page.ADMIN_TEMPLATE;
    public static final Page defaultPageContent = Page.ADMIN_CARS_CONTENT;
    public static final Page addCarPageContent = Page.ADMIN_ADD_CAR_CONTENT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), defaultPageContent.getType());
        req.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "Test error Message");
        req.setAttribute(RequestAttribute.SUCCESS_MESSAGE.getName(), "Test success Message");

        String action = req.getParameter("action");
        if ("add-car".equals(action)) {
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), addCarPageContent.getAbsolutePath());
            req.getRequestDispatcher(template.getAbsolutePath()).forward(req, resp);
            return;
        }
        redirectToCars(2, 10, req, resp);
    }
    private void redirectToCars(int pageNumber, int pageSize, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDao carDao = new CarDao();
        List<Car> cars = carDao.getPage(pageNumber, pageSize);
        carDao.close();

        req.setAttribute(RequestAttribute.CAR_LIST.getName(), cars);
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), defaultPageContent.getAbsolutePath());
        req.getRequestDispatcher(template.getAbsolutePath()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add-car".equals(action)) {
            CarDao carDao = new CarDao();
            carDao.persist(new Car(req.getParameter("plate"), isOn(req.getParameter("avaliable")),
                    Integer.parseInt(req.getParameter("category")), isOn(req.getParameter("animalable")),
                    isOn(req.getParameter("wi-fi")), isOn(req.getParameter("conditioner"))));
            carDao.close();
//            List<Car> cars = carDao.getPage(1, 10);
//            carDao.close();
//
//            if(cars != null) {
//                req.setAttribute(ATTRIBUTE_CARS, cars);
//            }

            redirectToCars(1, 10, req, resp);
        }
    }

    private boolean isOn (String checkBoxText){
        return "on".equals(checkBoxText);
    }

}
