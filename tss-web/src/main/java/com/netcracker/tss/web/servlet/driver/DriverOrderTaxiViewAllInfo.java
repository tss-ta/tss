/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.servlet.driver;

import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.helper.TaxiOrderHistory;
import static com.netcracker.tss.web.servlet.customer.CustomerOrderTaxiEditDeleteServlet.TAXI_ORDER_ID;
import com.netcracker.util.BeansLocator;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Виктор
 */
@WebServlet(urlPatterns = "/driver/dashboard/allinfo")
public class DriverOrderTaxiViewAllInfo extends HttpServlet {

    public static final String VIEW_ALL = "viewAll";
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
        if (VIEW_ALL.equals(action)) {
            int taxiOrderId = Integer.parseInt(request.getParameter(ORDER_ID));
            TaxiOrderBeanLocal taxiOrderBeanLocal = BeansLocator.getInstance().getBean(TaxiOrderBeanLocal.class);
            TaxiOrder taxiOrder = taxiOrderBeanLocal.getOrderById(taxiOrderId);
            TaxiOrderHistory list = taxiOrderBeanLocal.getOrderForEdit(taxiOrder);
            DateFormat format = new SimpleDateFormat("HH:mm, dd MM yyyy",
                    Locale.ENGLISH);
            request.setAttribute("orderTime", format.format(list.getOrderTime()));
            request.setAttribute("list", list);
            request.setAttribute("pageContent", "content/viewAllInfoDriver.jsp");
            request.setAttribute("pageType", "dashboard");
            request.getRequestDispatcher("/WEB-INF/views/driver/driver-template.jsp")
                    .forward(request, response);
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
