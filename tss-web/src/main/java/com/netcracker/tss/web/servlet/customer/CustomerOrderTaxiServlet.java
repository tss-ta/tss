package com.netcracker.tss.web.servlet.customer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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

import com.netcracker.util.BeansLocator;
import org.json.JSONException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.netcracker.dao.UserDAO;
import com.netcracker.ejb.MapBeanLocal;
import com.netcracker.ejb.MapBeanLocalHome;
import com.netcracker.ejb.PriceBean;
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

import java.util.Map;

/**
 * Created by Stanislav Zabielin
 * @author maks
 */
@WebServlet(urlPatterns = "/customer/order")
public class CustomerOrderTaxiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
        if (req.getParameter("addFrom") != null) {
            addAddressFrom(req);
            resp.sendRedirect("/customer/orderpage");
        } else if (req.getParameter("addTo") != null) {
            addAddressTo(req);
            resp.sendRedirect("/customer/orderpage");
        } else if (req.getParameter("deleteTo") != null
                || req.getParameter("deleteFrom") != null) {
            deleteAddress(req);
            resp.sendRedirect("/customer/orderpage");
        } else {
            Date bookingTime = new Date();
            Date orderTime = DateParser.parseDate(req);
            if (orderTime.before(bookingTime)) {
                req.setAttribute("errorMessage", "It is impossible to order taxi at the past! Please input the correct order time.");
                redirectToTaxiOrder(req,resp);
//                req.getRequestDispatcher("/customer/orderpage").forward(req, resp);
//                resp.sendRedirect("/customer/orderpage?err=It is impossible to order taxi at the past! Please input the correct order time.");
            } else {
                User user = findCurrentUser();
                TaxiOrderBeanLocal taxiOrderBeanLocal = getTaxiOrderBean(req);
                PriceBeanLocal priceBean = getPriceBean(req);
                float distance = 0;
                double price = 0;

                Route route = new Route(findCurrentUser().getUsername() + " Route");
                route.setDistance(distance);
                Address addFrom = toAddress(req.getParameter("fromAddr"), req);
                Address addTo = toAddress(req.getParameter("toAddr"), req);
                TaxiOrder taxiOrder = new TaxiOrder(AdditionalParameters.taxiOrderAddParameters(req));
                taxiOrder.setBookingTime(bookingTime);

                MapBeanLocal mapBean = getMapBean(req);
                distance = mapBean.calculateDistance(req.getParameter("fromAddr"),
                        req.getParameter("toAddr"));

                price = priceBean.calculatePrice(distance,
                        orderTime, taxiOrder, UserUtils.findCurrentUser());
                taxiOrder.setOrderTime(orderTime);
                taxiOrder.setPrice(price);
                taxiOrderBeanLocal.addTaxiOrder(user, route, addFrom, addTo,
                        taxiOrder);
                int latestTOId = taxiOrderBeanLocal.getTaxiOrderHistory(1, 1, user)
                        .get(0).getId();
                req.setAttribute("taxiOrderId", latestTOId);
                req.setAttribute("pageContent", "content/confirmation.jsp");
                req.getRequestDispatcher(
                        "/WEB-INF/views/customer/customer-template.jsp").forward(
                        req, resp);
            }
        }
        }
//        catch (JSONException | IOException | ServletException e) {
        catch (Exception e) {
            // TODO catch block
            e.printStackTrace();
            req.setAttribute("errorMessage", "Sorry, we can not make this order! Please, check all input parameters ad try again.");
            redirectToTaxiOrder(req, resp);
//            resp.sendRedirect("/customer/orderpage?err=Sorry, we can not make this order! Please, check all input parameters ad try again.");
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
            UserBeanLocal userBeanLocal = getUserBean();
            userBeanLocal.removeFromPersonalList(UserUtils.findCurrentUser(),
                    addr);
        }
    }

    private void addAddressFrom(HttpServletRequest req) {
        String addr = req.getParameter("fromAddr");
        if (addr != null) {
            UserBeanLocal userBeanLocal = getUserBean();
            userBeanLocal.addToPersonalList(UserUtils.findCurrentUser(), addr);
        }
    }

    private void addAddressTo(HttpServletRequest req) {
        String addr = req.getParameter("toAddr");
        if (addr != null) {
            UserBeanLocal userBeanLocal = getUserBean();
            userBeanLocal.addToPersonalList(UserUtils.findCurrentUser(), addr);
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

    private void redirectToTaxiOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("taxiOrder");
        UserBeanLocal userBeanLocal = getUserBean();
        req.setAttribute("personal_addr", userBeanLocal.toPersonalAddress(UserUtils.findCurrentUser()));
        req.setAttribute("pageContent", "content/customer-order.jsp");
        req.setAttribute("pageType", "orderpage");
        req.getRequestDispatcher("/WEB-INF/views/customer/customer-template.jsp")
                .forward(req, resp);
    }



    private UserBeanLocal getUserBean() {
        return BeansLocator.getInstance().getBean(UserBeanLocal.class);
    }

    private PriceBeanLocal getPriceBean(HttpServletRequest req) {
        return BeansLocator.getInstance().getBean(PriceBeanLocal.class);
    }

    private TaxiOrderBeanLocal getTaxiOrderBean(HttpServletRequest req) {
        return BeansLocator.getInstance().getBean(TaxiOrderBeanLocal.class);
    }

    private MapBeanLocal getMapBean(HttpServletRequest req) {
        return BeansLocator.getInstance().getBean(MapBeanLocal.class);
    }
}
