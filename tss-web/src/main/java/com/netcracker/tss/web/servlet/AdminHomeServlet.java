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
        req.getRequestDispatcher("/WEB-INF/views/admin/home-admin.jsp").forward(req, resp);
    }
}
