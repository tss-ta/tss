package com.netcracker.tss.web.servlet.admin;

import com.netcracker.ejb.ReportsBean;
import com.netcracker.ejb.ReportsBeanLocal;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.BeansLocator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kyrylo Berehovyi on 25/04/2015.
 * @author maks
 */
@WebServlet(urlPatterns = "/admin/report")
public class AdminReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if ("overall-popular-car-options".equals(action)) {
            ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
            req.setAttribute("report", reportsBean.getCarOptionsReport());
            req.setAttribute("allTO", reportsBean.countAllOrders());
            req.setAttribute("user", "All");
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CAR_OPTIONS_REPORTS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } else {
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_REPORTS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        }
    }

    private void redirectToReports(HttpServletRequest req, HttpServletResponse resp) {

    }
}
