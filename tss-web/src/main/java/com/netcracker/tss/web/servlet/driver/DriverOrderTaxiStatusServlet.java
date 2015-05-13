/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.servlet.driver;

import com.netcracker.dao.TaxiOrderDAO;
import com.netcracker.dao.exceptions.NoSuchEntity;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.entity.TaxiOrder;
import static com.netcracker.tss.web.servlet.customer.CustomerOrderTaxiEditDeleteServlet.ACTION_EDIT_TAXI_ORDER;
import com.netcracker.tss.web.util.UserUtils;
import com.netcracker.util.BeansLocator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Виктор
 */
@WebServlet(urlPatterns = "/driver/status")
public class DriverOrderTaxiStatusServlet extends HttpServlet {

    public static final String CHANGE_STATUS = "changeStatus";
    public static final String ORDER_ID = "order_id";

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
        String action = request.getParameter("action");
        if (CHANGE_STATUS.equals(action)) {
            int taxiOrderId = Integer.parseInt(request.getParameter(ORDER_ID));
            TaxiOrderBeanLocal taxiOrderBeanLocal = BeansLocator.getInstance().getBean(TaxiOrderBeanLocal.class);
            taxiOrderBeanLocal.setNextStatus(taxiOrderId, UserUtils.findCurrentUser());
            response.sendRedirect("/driver/dashboard");
        }
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
