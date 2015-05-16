package com.netcracker.tss.web.servlet.driver;

import com.netcracker.dao.exceptions.DriverAssignCarException;
import com.netcracker.dao.exceptions.DriverOrderCountException;
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
import com.netcracker.entity.helper.Status;

import com.netcracker.entity.helper.StatusDriver;
import com.netcracker.entity.helper.TaxiOrderHistory;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.UserUtils;
import com.netcracker.util.BeansLocator;
import javax.persistence.NoResultException;

@WebServlet(urlPatterns = "/driver/dashboard")
public class DriverOrderTaxiDashboardServlet extends HttpServlet {

    public static final String CHANGE_STATUS = "changeStatus";
    public static final String ORDER_ID = "order_id";
    private static final int pageSize = 10;
    public static String statusSt = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        Integer pageNumber = updatePageNumber(req);
        getServletContext().setAttribute("pageNumber", pageNumber);
        List<TaxiOrderHistory> list;
        try {
            if (statusSt != null) {
                if (CHANGE_STATUS.equals(action)) {
                    int taxiOrderId = Integer.parseInt(req.getParameter(ORDER_ID));
                    TaxiOrderBeanLocal taxiOrderBeanLocal = BeansLocator.getInstance().getBean(TaxiOrderBeanLocal.class);
                    try {
                        taxiOrderBeanLocal.setNextStatus(taxiOrderId, UserUtils.findCurrentUser());
                    } catch (DriverOrderCountException e) {
                        req.setAttribute("pageType", "dashboard");
                        req.setAttribute("pageContent", "content/orderInProgressMessage.jsp");
                        req.getRequestDispatcher("/WEB-INF/views/driver/driver-template.jsp")
                                .forward(req, resp);
                        return;
                    }
                    redirect(pageNumber, req, resp, statusSt);
                    return;
                }
            }
            if (!"filterByStatus".equals(req.getParameter("action"))) {
                redirect(pageNumber, req, resp, Status.IN_PROGRESS.toString());
            } else {
                statusSt = req.getParameter("status");
                redirect(pageNumber, req, resp, statusSt);
            }
           
        } catch (DriverAssignCarException e) {
            req.setAttribute("pageType", "dashboard");
            req.setAttribute("pageContent", "content/noassignedcar.jsp");
            req.getRequestDispatcher("/WEB-INF/views/driver/driver-template.jsp")
                    .forward(req, resp);
        }
    }

    private void redirect(int pageNumber, HttpServletRequest request, HttpServletResponse response, String status) throws DriverAssignCarException {
        List<TaxiOrderHistory> list = getHistory(pageNumber, request, Status.getStatusByName(status));
        request.setAttribute("status_enum", StatusDriver.getDriverStatus());
        request.setAttribute("param", status);
        request.setAttribute("history", list);
        request.setAttribute("pageType", "dashboard");
        request.setAttribute("pageContent", "content/dashboard.jsp");
        try {
            request.getRequestDispatcher("/WEB-INF/views/driver/driver-template.jsp")
                    .forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(DriverOrderTaxiDashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DriverOrderTaxiDashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<TaxiOrderHistory> getHistory(Integer pageNumber,
            HttpServletRequest req, Status status) throws DriverAssignCarException {
        TaxiOrderBeanLocal taxiOrderBeanLocal = getTaxiOrderBean(req);
        List<TaxiOrderHistory> list = taxiOrderBeanLocal.getTaxiOrderDriver(
                pageNumber, pageSize, UserUtils.findCurrentUser(), status);

        if (list.size() == 0 && pageNumber > 1) {
            pageNumber--;
            getServletContext().setAttribute("pageNumber", pageNumber);
            list = taxiOrderBeanLocal
                    .getTaxiOrderHistory(pageNumber, pageSize,
                            UserUtils.findCurrentUser());
        }
        return list;
    }

    private Integer updatePageNumber(HttpServletRequest req) {
        Integer pageNumber = (Integer) getServletContext().getAttribute(
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
