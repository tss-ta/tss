package com.netcracker.tss.web.servlet;

import com.netcracker.dao.CarDao;
import com.netcracker.entity.Car;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Kyrylo Berehovyi on 20/04/2015.
 */
@WebServlet(urlPatterns = "/admin")
public class AdminHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Admin Page");
        String menu = req.getParameter("menu");
        String action = req.getParameter("action");
//        switch (menu){
//            case "car": car
//        }
        
        
        if ("addcar".equals(action)){
            req.getRequestDispatcher("/WEB-INF/views/admin/addcar.jsp").forward(req, resp);
        }
        

        if ("cars".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/cars.jsp").forward(req, resp);
        } else if ("reports".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/reports.jsp").forward(req, resp);
        } else if ("customers".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/customers.jsp").forward(req, resp);
        }else if ("drivers".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/drivers.jsp").forward(req, resp);
        }else if ("tariffs".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/tariffs.jsp").forward(req, resp);
        }else if ("groups".equals(menu)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/groups.jsp").forward(req, resp);
        } else {

            req.getRequestDispatcher("/WEB-INF/views/admin/cars.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String action = req.getParameter("action");
        if ("newcar".equals(action)){
            CarDao carDao = new CarDao();
            carDao.persist(new Car(1, true, 1, true, true, true));
        }
                           PrintWriter out = resp.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>!!! " + 11111111 + "</h1>");
            out.println("<h1>!!! " + action + "</h1>");
                        out.println("<h3>!!! " + req.getParameterMap()+ "</h3>");
                        out.println("<h3>!!! " + req.getParameterNames()+ "</h3>");
                        
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    
}
