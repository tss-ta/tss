package com.netcracker.tss.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kyrylo Berehovyi on 20/04/2015.
 */
@WebServlet(urlPatterns = "/admin")
public class AdminHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Admin Page");
        req.setAttribute("title", "Admin Page");
        String menu = req.getParameter("menu");
//        switch (menu){
//            case "car": car
//        }

        if ("cars".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/cars.jsp").forward(req, resp);
        } else if ("reports".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/reports.jsp").forward(req, resp);
        } else if ("customers".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/customers.jsp").forward(req, resp);
        } else {

            req.getRequestDispatcher("/WEB-INF/views/admin/cars.jsp").forward(req, resp);
        }
    }
}
