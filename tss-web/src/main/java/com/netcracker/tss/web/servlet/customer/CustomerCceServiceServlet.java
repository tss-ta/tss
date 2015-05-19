package com.netcracker.tss.web.servlet.customer;

import java.io.IOException;
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

import com.netcracker.dao.UserDAO;
import com.netcracker.ejb.ConveyCorpServiceBeanLocal;
import com.netcracker.ejb.ConveyCorpServiceBeanLocalHome;
import com.netcracker.ejb.MapBeanLocal;
import com.netcracker.ejb.MapBeanLocalHome;
import com.netcracker.ejb.PriceBeanLocal;
import com.netcracker.ejb.PriceBeanLocalHome;
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

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Lis
 * @author maks
 */
@WebServlet(urlPatterns = "/customer/cceService")
public class CustomerCceServiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("addTo") != null
                || req.getParameter("addFrom") != null) {
            addAddress(req);
            resp.sendRedirect("/customer/cceServicePage");
        } else if (req.getParameter("deleteTo") != null
                || req.getParameter("deleteFrom") != null) {
            deleteAddress(req);
            resp.sendRedirect("/customer/cceServicePage");
        } else {
            User user = findCurrentUser();
            PriceBeanLocal priceBean = getPriceBean(req);
            ConveyCorpServiceBeanLocal taxiConveyCorpServiceBeanLocal = getConveyCorpServiceBean(req);
            String[] fromListAddr = req.getParameterValues("fromList");
            if (fromListAddr != null && fromListAddr.length > 0) {
                float distance = 0;
                float currentdistance = 0;
                double price = 0;
                String toAddr = req.getParameter("toAddr");
                MapBeanLocal mapBean = getMapBean(req);
                try {
                    Address addTo = toAddress(toAddr, req);
                    List<Address> addFrom = new ArrayList<Address>();
                    List<Route> routes = new ArrayList<>();
                    for (String fa : fromListAddr) {
                        addFrom.add(toAddress(fa, req));
                        Route r = new Route(user.getUsername() + " Route");

                        currentdistance = mapBean.calculateDistance(fa, toAddr);
                        distance = distance + currentdistance;

                        r.setDistance(currentdistance);
                        routes.add(r);
                    }
                    TaxiOrder taxiOrder = new TaxiOrder(AdditionalParameters.taxiOrderAddParameters(req));
                    taxiOrder.setBookingTime(new Date());
                    Date orderTime = DateParser.parseDate(req);
                    //orderTime.setYear(new Date().getYear());
                    taxiOrder.setOrderTime(orderTime);
                    price = priceBean.calculatePriceForCceService(distance,
                            DateParser.parseDate(req), taxiOrder, UserUtils.findCurrentUser());
                    taxiOrder.setPrice(price);

                    Integer latestTOId = taxiConveyCorpServiceBeanLocal.addCorpService(user, routes, addFrom, addTo, taxiOrder);
                    req.setAttribute("taxiOrderId", latestTOId);
                    req.setAttribute("pageContent", "content/confirmation.jsp");
                    req.getRequestDispatcher(
                            "/WEB-INF/views/customer/customer-template.jsp").forward(
                            req, resp);
                } catch (Exception e) {
                    resp.sendRedirect("/customer/cceServicePage?err=Sorry, we can not make this order! Please, check all input parameters ad try again.");
                }
            } else {
                resp.sendRedirect("/customer/cceServicePage?err=Sorry, we can not make this order! Please, check all input parameters ad try again.");
            }
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
            userBeanLocal.removeFromPersonalList(UserUtils.findCurrentUser(), addr);
        }
    }

    private void addAddress(HttpServletRequest req) {
        String addr = null;
        if (req.getParameter("fromAddr") != null) {
            addr = req.getParameter("fromAddr");
        } else if (req.getParameter("toAddr") != null) {
            addr = req.getParameter("toAddr");
        }
        if (addr != null) {
            UserBeanLocal userBeanLocal = getUserBean(req);
            userBeanLocal.addToPersonalList(UserUtils.findCurrentUser(), addr);
        }
    }

    private Address toAddress(String addr, HttpServletRequest req) {
        MapBeanLocal mapBeanLocal = getMapBean(req);
        double[] to = {0, 0};
        try {
            to = mapBeanLocal.geocodeAddress(addr);
        } catch (JSONException | IOException e) {
            return null;
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

    private ConveyCorpServiceBeanLocal getConveyCorpServiceBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            ConveyCorpServiceBeanLocalHome taxiConveyCorpServiceBeanLocalHome = (ConveyCorpServiceBeanLocalHome) context
                    .lookup("java:app/tss-ejb/ConveyCorpServiceBean!com.netcracker.ejb.ConveyCorpServiceBeanLocalHome");

            return taxiConveyCorpServiceBeanLocalHome.create();
        } catch (NamingException ex) {
            Logger.getLogger(AdminGroupServlet.class.getName())
                    .log(Level.SEVERE,
                    "Can't find ConveyCorpServiceBean with name java:app/tss-ejb/ConveyCorpServiceBean!com.netcracker.ejb.ConveyCorpServiceBeanLocalHome ",
                    ex);
            throw new RuntimeException("Internal server error!");// maybe have
            // to create
            // custom
            // exception?
        }
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
                    "Can't find taxiMapBean with name java:app/tss-ejb/MapBean!com.netcracker.ejb.MapBeanLocalHome ",
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
