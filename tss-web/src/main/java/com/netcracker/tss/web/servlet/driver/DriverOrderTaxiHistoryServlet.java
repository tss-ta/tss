/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.servlet.driver;

import com.netcracker.dao.exceptions.DriverAssignCarException;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.entity.helper.Status;
import com.netcracker.entity.helper.StatusDriver;
import com.netcracker.entity.helper.TaxiOrderHistory;
import com.netcracker.tss.web.util.UserUtils;
import com.netcracker.util.BeansLocator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Виктор
 */
@WebServlet(urlPatterns = "/driver/history")
public class DriverOrderTaxiHistoryServlet extends HttpServlet {

    private static final int pageSize = 10;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer pageNumber = updatePageNumber(request);
        getServletContext().setAttribute("pageNumber", pageNumber);
        List<TaxiOrderHistory> list = null;
        try {
            list = getHistory(pageNumber, request, Status.getStatusByName("COMPLETED"));
            request.setAttribute("history", list);
            request.setAttribute("pageType", "history");
            request.setAttribute("pageContent", "content/history.jsp");
            request.getRequestDispatcher("/WEB-INF/views/driver/driver-template.jsp")
                    .forward(request, response);
        } catch (DriverAssignCarException ex) {
            request.setAttribute("pageType", "dashboard");
            request.setAttribute("pageContent", "content/noassignedcar.jsp");
            request.getRequestDispatcher("/WEB-INF/views/driver/driver-template.jsp")
                    .forward(request, response);
        }

    }

    private List<TaxiOrderHistory> getHistory(Integer pageNumber,
            HttpServletRequest req, Status status) throws DriverAssignCarException {
        TaxiOrderBeanLocal taxiOrderBeanLocal = BeansLocator.getInstance().getBean(TaxiOrderBeanLocal.class);;
        List<TaxiOrderHistory> list = taxiOrderBeanLocal.getTaxiOrderDriver(
                pageNumber, pageSize, UserUtils.findCurrentUser(), status);

        if (list.size() == 0 && pageNumber > 1) {
            pageNumber--;
            getServletContext().setAttribute("pageNumber", pageNumber);
            list = taxiOrderBeanLocal.getTaxiOrderDriver(
                    pageNumber, pageSize, UserUtils.findCurrentUser(), status);
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
