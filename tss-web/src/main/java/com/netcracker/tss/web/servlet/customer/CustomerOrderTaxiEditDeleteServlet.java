/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.servlet.customer;

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
import com.netcracker.entity.helper.Roles;
import com.netcracker.entity.helper.Status;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 */
@WebServlet(urlPatterns = "/customer/edit")
public class CustomerOrderTaxiEditDeleteServlet extends HttpServlet {

    public static final String ACTION_EDIT_TAXI_ORDER = "editTaxiOrder";
    public static final String ACTION_DELETE_TAXI_ORDER = "deleteTaxiOrder";
    public static final String TAXI_ORDER_ID = "taxiOrderId";
    private int taxiOrderId;

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
        String action = request.getParameter("action");
        if (ACTION_EDIT_TAXI_ORDER.equals(action)) {
            taxiOrderId = Integer.parseInt(request.getParameter(TAXI_ORDER_ID));
            TaxiOrder taxiOrder = null;
            try {
                taxiOrder = new TaxiOrderDAO().get(taxiOrderId);
            } catch (NoSuchEntity e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("taxiOrder", taxiOrder);
            redirectToEdit(request, response);
            return;
        }
       
        if (ACTION_DELETE_TAXI_ORDER.equals(action)) {
            TaxiOrderBeanLocal taxiOrderBeanLocal = BeansLocator.getInstance().getBean(TaxiOrderBeanLocal.class);
            taxiOrderId = Integer.parseInt(request.getParameter(TAXI_ORDER_ID));
            taxiOrderBeanLocal.refuseTaxiOrder(taxiOrderId);
            User user = UserUtils.findCurrentUser();
            if (taxiOrderBeanLocal.countOrdersByStatus(user, Status.REFUSED) >= 3) {
                UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
                List<Roles> rs = new ArrayList<>();
                rs.add(Roles.BANNED);
                userBeanLocal.editRoles(user.getId(), rs);
                request.setAttribute("pageContent", "content/banned.jsp");
                request.getRequestDispatcher(
                        "/WEB-INF/views/customer/customer-template.jsp").
                        forward(request, response);
                
            } else {
                request.setAttribute("taxiOrderId", taxiOrderId);
                request.setAttribute("pageContent", "content/refuse.jsp");
                request.getRequestDispatcher(
                        "/WEB-INF/views/customer/customer-template.jsp").
                        forward(request, response);
            }
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
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("addTo") != null) {
            addAddressTo(request);
            redirectToEdit(request, response);
        } else if (request.getParameter("addFrom") != null) {
            addAddressFrom(request);
            redirectToEdit(request, response);
        } else if (request.getParameter("deleteTo") != null
                || request.getParameter("deleteFrom") != null) {
            deleteAddress(request);
            redirectToEdit(request, response);
        } else {
            TaxiOrderBeanLocal taxiOrderBeanLocal = getTaxiOrderBean(request);
            Address addFrom = toAddress(request.getParameter("fromAddr"), request);
            Address addTo = toAddress(request.getParameter("toAddr"), request);
            PriceBeanLocal priceBean = getPriceBean(request);
            float distance = 0;
            double price = 0;
            try {
                MapBeanLocal mapBean = getMapBean(request);
                distance = mapBean.calculateDistance(request.getParameter("fromAddr"),
                        request.getParameter("toAddr"));
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            if ("".equals(request.getParameter("price"))) {
                price = priceBean.calculatePrice(distance,
                        DateParser.parseDate(request), (TaxiOrder) request.getSession().getAttribute("taxiOrder"));
            } else {
                price = Double.parseDouble(request.getParameter("price"));
            }
            request.getSession().removeAttribute("taxiOrder");
            Date orderTime = DateParser.parseDate(request);
            orderTime.setYear(new Date().getYear());
            taxiOrderBeanLocal.editTaxiOrderCustomer(taxiOrderId,
                    addFrom, addTo, orderTime, distance, price);
            request.setAttribute("taxiOrderId", taxiOrderId);
            request.setAttribute("pageContent", "content/confirmation-updated.jsp");
            request.getRequestDispatcher(
                    "/WEB-INF/views/customer/customer-template.jsp").forward(
                            request, response);

        }
    }

    private void redirectToEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        DateFormat format = new SimpleDateFormat("HH:mm, dd MM yyyy",
                Locale.ENGLISH);
        UserBeanLocal userBeanLocal = getUserBean(req);
        req.setAttribute("personal_addr", userBeanLocal.toPersonalAddress(UserUtils.findCurrentUser()));
        TaxiOrder taxiOrder = getTaxiOrderBean(req).getOrderById(taxiOrderId);
        TaxiOrderHistory toh = getTaxiOrderBean(req).getOrderForEdit(taxiOrder);
        req.setAttribute("orderTime", format.format(toh.getOrderTime()));
        req.setAttribute("toh", toh);
        req.setAttribute("pageContent", "content/editTaxiOrder.jsp");
        req.setAttribute("pageType", "editpage");
        req.getRequestDispatcher("/WEB-INF/views/customer/customer-template.jsp")
                .forward(req, resp);
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
            userBeanLocal.removeFromPersonalList(UserUtils.findCurrentUser(), addr);
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
}
