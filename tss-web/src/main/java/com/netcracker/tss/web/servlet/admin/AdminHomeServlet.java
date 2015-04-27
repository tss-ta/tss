package com.netcracker.tss.web.servlet.admin;

import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;

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

    private static final Page page = Page.ADMIN_DASHBOARD_CONTENT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_DASHBOARD_CONTENT.getType());
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_DASHBOARD_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
    }
}
