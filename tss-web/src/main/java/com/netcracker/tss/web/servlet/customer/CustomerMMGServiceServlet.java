/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.servlet.customer;

import com.netcracker.dao.UserDAO;
import com.netcracker.ejb.MapBeanLocal;
import com.netcracker.ejb.MapBeanLocalHome;
import com.netcracker.ejb.MeetMyGuestBeanLocal;
import com.netcracker.ejb.MeetMyGuestBeanLocalHome;
import com.netcracker.ejb.PriceBeanLocal;
import com.netcracker.ejb.PriceBeanLocalHome;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocalHome;
import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.ejb.UserBeanLocalHome;
import com.netcracker.entity.Address;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.AdditionalParameters;
import com.netcracker.tss.web.util.DateParser;
import com.netcracker.tss.web.util.UserUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
 */
@WebServlet(urlPatterns = "/customer/meetMyGuest")
public class CustomerMMGServiceServlet extends HttpServlet {

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
        if (request.getParameter("addFrom") != null) {
            addAddressFrom(request);
            response.sendRedirect("/customer/orderpage");
        } else if (request.getParameter("addTo") != null) {
            addAddressTo(request);
            response.sendRedirect("/customer/orderpage");
        } else if (request.getParameter("deleteTo") != null
                || request.getParameter("deleteFrom") != null) {
            deleteAddress(request);
            response.sendRedirect("/customer/orderpage");
        } else {
            User user = findCurrentUser();
            MeetMyGuestBeanLocal myGuestBeanLocal = getMeetMyGuestBean(request);
            TaxiOrderBeanLocal taxiOrderBeanLocal = getTaxiOrderBean(request);
            PriceBeanLocal priceBean = getPriceBean(request);
            float distance = 0;
            double price = 0;

            Route route = new Route(findCurrentUser().getUsername() + " Route");
            route.setDistance(distance);
            Address addFrom = toAddress(request.getParameter("fromAddr"), request);
            Address addTo = toAddress(request.getParameter("toAddr"), request);
            TaxiOrder taxiOrder = new TaxiOrder(AdditionalParameters.taxiOrderAddParameters(request));
            try {
                MapBeanLocal mapBean = getMapBean(request);
                distance = mapBean.calculateDistance(request.getParameter("fromAddr"),
                        request.getParameter("toAddr"));
            } catch (JSONException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if ("".equals(request.getParameter("price"))) {
                price = priceBean.calculatePrice(distance,
                        DateParser.parseDate(request),taxiOrder);
            } else {
                price = Double.parseDouble(request.getParameter("price"));
            }
            taxiOrder.setBookingTime(new Date());
            Date orderTime = DateParser.parseDate(request);
            taxiOrder.setOrderTime(orderTime);
            taxiOrder.setPrice(price);
            String guestName = request.getParameter("guestName");
            myGuestBeanLocal.addMeetMyGuestService(user, route, addFrom, addTo, taxiOrder, guestName);
            int latestTOId = taxiOrderBeanLocal.getTaxiOrderHistory(1, 1, user)
                    .get(0).getId();
            request.setAttribute("taxiOrderId", latestTOId);
            request.setAttribute("pageContent", "content/confirmation.jsp");
            request.getRequestDispatcher(
                    "/WEB-INF/views/customer/customer-template.jsp").forward(
                            request, response);
        }
    }

    private void deleteAddress(HttpServletRequest req) {
        String addr = null;
        if (req.getParameter("deleteFrom") != null) {
            addr = req.getParameter("pers_addr");
        } else if (req.getParameter("deleteTo") != null) {
            addr = req.getParameter("pers_addr_to");
        }
        if (addr != null) {
            UserBeanLocal userBeanLocal = getUserBean(req);
            userBeanLocal.removeFromPersonalList(UserUtils.findCurrentUser(),
                    addr);
        }
    }

    private void addAddressFrom(HttpServletRequest req) {
        String addr = req.getParameter("fromAddr");
        if (addr != null) {
            UserBeanLocal userBeanLocal = getUserBean(req);
            userBeanLocal.addToPersonalList(UserUtils.findCurrentUser(), addr);
        }
    }

    private void addAddressTo(HttpServletRequest req) {
        String addr = req.getParameter("toAddr");
        if (addr != null) {
            UserBeanLocal userBeanLocal = getUserBean(req);
            userBeanLocal.addToPersonalList(UserUtils.findCurrentUser(), addr);
        }
    }

    private MeetMyGuestBeanLocal getMeetMyGuestBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            MeetMyGuestBeanLocalHome meetMyGuestBeanLocalHome = (MeetMyGuestBeanLocalHome) context
                    .lookup("java:app/tss-ejb/MeetMyGuestBean!com.netcracker.ejb.MeetMyGuestBeanLocalHome");
            return meetMyGuestBeanLocalHome.create();
        } catch (NamingException ex) {
            Logger.getLogger(AdminGroupServlet.class.getName())
                    .log(Level.SEVERE,
                            "Can't find userBean with name java:app/tss-ejb/UserBean!com.netcracker.ejb.MeetMyGuestBeanLocal ",
                            ex);
            throw new RuntimeException("Internal server error!");// maybe have
            // to create
            // custom
            // exception?
        }
    }

    private Address toAddress(String addr, HttpServletRequest req) {
        MapBeanLocal mapBeanLocal = getMapBean(req);
        double[] to = {0, 0};
        try {
            to = mapBeanLocal.geocodeAddress(addr);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return new Address((float) to[1], (float) to[0]);
    }

    private User findCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        UserDAO userDao = new UserDAO();
        User user = userDao.getByEmail(userDetails.getUsername());
        userDao.close();
        return user;
    }

    private MapBeanLocal getMapBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            MapBeanLocalHome mapBeanLocalHome = (MapBeanLocalHome) context
                    .lookup("java:app/tss-ejb/MapBean!com.netcracker.ejb.MapBeanLocalHome");
            return mapBeanLocalHome.create();
        } catch (NamingException ex) {
            Logger.getLogger(AdminGroupServlet.class.getName())
                    .log(Level.SEVERE,
                            "Can't find taxiOrderBean with name java:app/tss-ejb/MapBean!com.netcracker.ejb.MapBeanLocalHome ",
                            ex);
            throw new RuntimeException("Internal server error!");// maybe have
            // to create
            // custom
            // exception?
        }
    }

    private PriceBeanLocal getPriceBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            PriceBeanLocalHome priceBeanLocalHome = (PriceBeanLocalHome) context
                    .lookup("java:app/tss-ejb/PriceBean!com.netcracker.ejb.PriceBeanLocalHome");
            return priceBeanLocalHome.create();
        } catch (NamingException ex) {
            throw new RuntimeException("Internal server error!");// maybe have
            // to create
            // custom
            // exception?
        }
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

    private UserBeanLocal getUserBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            UserBeanLocalHome userBeanLocalHome = (UserBeanLocalHome) context
                    .lookup("java:app/tss-ejb/UserBean!com.netcracker.ejb.UserBeanLocalHome");
            return userBeanLocalHome.create();
        } catch (NamingException ex) {
            Logger.getLogger(AdminGroupServlet.class.getName())
                    .log(Level.SEVERE,
                            "Can't find userBean with name java:app/tss-ejb/UserBean!com.netcracker.ejb.UserBeanLocalHome ",
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
