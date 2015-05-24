/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.servlet;

import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.helper.TaxiOrderHistory;
import com.netcracker.util.BeansLocator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Виктор
 */
@WebServlet(urlPatterns = "/track/order")
public class TrackOrderServlet extends HttpServlet {

    public static final String TRACK_NUMBER = "trackNumber";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        try {
            int orderId = Integer.parseInt(request.getParameter(TRACK_NUMBER));
            TaxiOrderBeanLocal taxiOrderBeanLocal = BeansLocator.getInstance().getBean(TaxiOrderBeanLocal.class);
            TaxiOrder taxiOrder = taxiOrderBeanLocal.getOrderById(orderId);
            if (taxiOrder == null) {
                request.setAttribute("error", true);
                new TrackOrderPageServlet().doGet(request, response);
            } else {
                TaxiOrderHistory toh = taxiOrderBeanLocal.getOrderForEdit(taxiOrder);
                request.setAttribute("list", toh);
                request.setAttribute("trackId", orderId);
                new TrackOrderPageServlet().doGet(request, response);
            }
        } catch (Exception e) { //TODO
            request.setAttribute("error", true);
            new TrackOrderPageServlet().doGet(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
