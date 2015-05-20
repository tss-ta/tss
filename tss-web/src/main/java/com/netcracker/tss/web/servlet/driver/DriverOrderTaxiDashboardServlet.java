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
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/driver/dashboard")
public class DriverOrderTaxiDashboardServlet extends HttpServlet {

    public static final String CHANGE_STATUS = "changeStatus";
    public static final String ORDER_ID = "order_id";
    private static final int pageSize = 10;
    public static String statusSt = null;
    private Integer pageNumber = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        pageNumber = updatePageNumber(req);
        req.getSession().setAttribute("pageNumber", pageNumber);
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
                    redirect( req, resp, statusSt);
                    return;
                }
            }
            if (!"filterByStatus".equals(req.getParameter("action"))) {
                redirect( req, resp, Status.IN_PROGRESS.toString());
            } else {
                if ((statusSt != null) && (!statusSt.equals(req.getParameter("status")))) {
                    pageNumber = 1;
                }
                statusSt = req.getParameter("status");
                redirect( req, resp, statusSt);
            }

        } catch (DriverAssignCarException e) {
            req.setAttribute("pageType", "dashboard");
            req.setAttribute("pageContent", "content/noassignedcar.jsp");
            req.getRequestDispatcher("/WEB-INF/views/driver/driver-template.jsp")
                    .forward(req, resp);
        }
    }

    private void redirect( HttpServletRequest request, HttpServletResponse response, String status) throws DriverAssignCarException {
        List<TaxiOrderHistory> list = getHistory(request, Status.getStatusByName(status));
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

    private List<TaxiOrderHistory> getHistory(HttpServletRequest req, Status status) throws DriverAssignCarException {
        TaxiOrderBeanLocal taxiOrderBeanLocal = BeansLocator.getInstance().getBean(TaxiOrderBeanLocal.class);
        List<TaxiOrderHistory> list = taxiOrderBeanLocal.getTaxiOrderDriver(
                pageNumber, pageSize, UserUtils.findCurrentUser(), status);

        if (list.size() == 0 && pageNumber > 1) {
            pageNumber--;
            list = taxiOrderBeanLocal.getTaxiOrderDriver(
                    pageNumber, pageSize, UserUtils.findCurrentUser(), status);
        }
        return list;
    }

    private Integer updatePageNumber(HttpServletRequest req) {
        if (req.getParameter("previous") != null && pageNumber > 1) {
            pageNumber--;
        } else if (req.getParameter("next") != null) {
            pageNumber++;
        }
        return pageNumber;
    }

}
