/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.servlet.driver;

import com.netcracker.dao.DriverDAO;
import com.netcracker.dao.TaxiOrderDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.dao.exceptions.NoSuchEntity;
import com.netcracker.ejb.MapBeanLocal;
import com.netcracker.ejb.MapBeanLocalHome;
import com.netcracker.ejb.PriceBeanLocal;
import com.netcracker.ejb.PriceBeanLocalHome;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocalHome;
import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.ejb.UserBeanLocalHome;
import com.netcracker.entity.Address;
import com.netcracker.entity.Driver;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.TaxiOrderHistory;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.DateParser;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.tss.web.util.UserUtils;
import com.netcracker.util.BeansLocator;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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

import org.json.JSONException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Виктор
 * @author Vitalii Chekaliuk
 */
@WebServlet(urlPatterns = "/driver/todetails")
public class DriverTaxiOrderDetailsServlet extends HttpServlet {

    public static final String TAXI_ORDER_ID = "taxiOrderId";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

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
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
        DateFormat format = new SimpleDateFormat("HH:mm, dd MM yyyy",
                Locale.ENGLISH);
        int taxiOrderId = Integer.parseInt(request.getParameter(TAXI_ORDER_ID));
        TaxiOrder taxiOrder = null;
        try {
            taxiOrder = new TaxiOrderDAO().get(taxiOrderId);
        } catch (NoSuchEntity e) {
            e.printStackTrace();
        }
        
        taxiOrder = getTaxiOrderBean(request).getOrderById(taxiOrderId);
        TaxiOrderHistory toh = getTaxiOrderBean(request).getOrderForEdit(taxiOrder);
        request.setAttribute("orderTime", format.format(toh.getOrderTime()));
        request.setAttribute("toh", toh);
        request.setAttribute("pageContent", "content/taxiOrderDetails.jsp");
        request.setAttribute("pageType", "editpage");
        request.getRequestDispatcher("/WEB-INF/views/driver/driver-template.jsp")
                .forward(request, response);
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
