package com.netcracker.tss.web.servlet.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocalHome;
import com.netcracker.entity.helper.TaxiOrderHistory;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.UserUtils;

/**
 * Created by Stanislav Zabielin
 */
@WebServlet(urlPatterns = "/customer/dashboard")
public class CustomerOrderTaxiDashboardServlet extends HttpServlet {

    private static final int pageSize = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().removeAttribute("taxiOrder");
        Integer pageNumber = updatePageNumber(req);
        req.getSession(true).setAttribute("pageNumber", pageNumber);
        List<TaxiOrderHistory> list = getHistory(pageNumber, req);
        req.setAttribute("history", list);
        req.setAttribute("pageType", "dashboard");
        req.setAttribute("pageContent", "content/dashboard.jsp");
        req.getRequestDispatcher("/WEB-INF/views/customer/customer-template.jsp")
                .forward(req, resp);
    }

    private List<TaxiOrderHistory> getHistory(Integer pageNumber,
            HttpServletRequest req) {
        TaxiOrderBeanLocal taxiOrderBeanLocal = getTaxiOrderBean(req);
        List<TaxiOrderHistory> list = taxiOrderBeanLocal.getTaxiOrderHistory(
                pageNumber, pageSize, UserUtils.findCurrentUser());
        if (list.size() == 0 && pageNumber > 1) {
            pageNumber--;
            req.getSession(true).setAttribute("pageNumber", pageNumber);
            list = taxiOrderBeanLocal
                    .getTaxiOrderHistory(pageNumber, pageSize,
                            UserUtils.findCurrentUser());
        }
        return list;
    }

    private Integer updatePageNumber(HttpServletRequest req) {
        Integer pageNumber = (Integer) req.getSession(true).getAttribute(
                "pageNumber");
        if (pageNumber == null) {
            pageNumber = 1;
        }
        if (req.getParameter("previous") != null && pageNumber > 1) {
            pageNumber--;
        } else if (req.getParameter("next") != null) {
            pageNumber++;
        }
        return pageNumber;
    }

    private TaxiOrderBeanLocal getTaxiOrderBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            TaxiOrderBeanLocalHome taxiOrderBeanLocalHome = (TaxiOrderBeanLocalHome) context
                    .lookup("java:app/tss-ejb/TaxiOrderBean!com.netcracker.ejb.TaxiOrderBeanLocalHome");
            return taxiOrderBeanLocalHome.create();
        } catch (NamingException ex) {
            Logger.getLogger(AdminGroupServlet.class.getName())
                    .log(Level.SEVERE,
                            "Can't find taxiOrderBean with name java:app/tss-ejb/TaxiOrderBean!com.netcracker.ejb.TaxiOrderBeanLocalHome ",
                            ex);
            throw new RuntimeException("Internal server error!");// maybe have
            // to create
            // custom
            // exception?
        }
    }

}
