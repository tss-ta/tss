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
 * Created by Kyrylo Berehovyi on 25/04/2015.
 */

@WebServlet(urlPatterns = "/admin/report")
public class AdminReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_REPORTS_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
    }
}
