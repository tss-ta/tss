package com.netcracker.tss.web.servlet.customer;

import com.netcracker.dao.UserDAO;
import com.netcracker.ejb.SoberServiceBeanLocalHome;
import com.netcracker.ejb.SoberServiceBeanLocal;
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
import com.netcracker.tss.web.util.AdditionalParametersForSoberService;
import com.netcracker.tss.web.util.DateParser;
import com.netcracker.tss.web.util.UserUtils;

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

import com.netcracker.util.BeansLocator;
import org.json.JSONException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Lis
 * @author maks
 */
@WebServlet(urlPatterns = "/customer/soberService")
public class CustomerSoberServiceServlet extends HttpServlet {

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
            resp.sendRedirect("/customer/soberServicePage");
        } else if (req.getParameter("deleteTo") != null
                || req.getParameter("deleteFrom") != null) {
            deleteAddress(req);
            resp.sendRedirect("/customer/soberServicePage");
        } else {
            try {
                Date bookingTime = new Date();
                Date orderTime = DateParser.parseDate(req);
                if (orderTime.before(bookingTime)) {
                    req.setAttribute("errorMessage", "It is impossible to order taxi at the past! Please input the correct order time.");
                    redirectToOrder(req, resp);
//                req.getRequestDispatcher("/customer/orderpage").forward(req, resp);
//                resp.sendRedirect("/customer/orderpage?err=It is impossible to order taxi at the past! Please input the correct order time.");
                } else {

                User user = findCurrentUser();
                PriceBeanLocal priceBean = getPriceBean();
                SoberServiceBeanLocal taxiSoberServiceBeanLocal = BeansLocator.getInstance().getBean(SoberServiceBeanLocal.class);
                float distance = 0;
                double price = 0;
                String fromAddr = req.getParameter("fromAddr");
                String toAddr = req.getParameter("toAddr");
                MapBeanLocal mapBean = getMapBean();

                Address addTo = toAddress(toAddr, req);
                Address addFrom = toAddress(fromAddr, req);
                Route r = new Route(user.getUsername() + " Route");
                distance = mapBean.calculateDistance(fromAddr, toAddr);
                r.setDistance(distance);
                TaxiOrder taxiOrder = new TaxiOrder(AdditionalParametersForSoberService.taxiOrderAddParameters(req));
                taxiOrder.setBookingTime(bookingTime);
                taxiOrder.setOrderTime(orderTime);
                price = priceBean.calculatePriceForSoberService(distance,
                        bookingTime, taxiOrder, UserUtils.findCurrentUser());
                taxiOrder.setPrice(price);
                Integer latestTOId = taxiSoberServiceBeanLocal.addSoberService(user, r, addFrom, addTo, taxiOrder);
                req.setAttribute("taxiOrderId", latestTOId);
                req.setAttribute("pageContent", "content/confirmation.jsp");
                req.getRequestDispatcher(
                        "/WEB-INF/views/customer/customer-template.jsp").forward(
                        req, resp);
            }
            } catch (Exception e) {
                Logger.getLogger(CustomerSoberServiceServlet.class.getName())
                        .log(Level.SEVERE, e.getMessage(), e);
                req.setAttribute("errorMessage", "Sorry, we can not make this order! Please, check all input parameters ad try again.");
                redirectToOrder(req, resp);
//                resp.sendRedirect(
//                        "/customer/soberServicePage?err=Sorry, we can not make this order! Please, check all input parameters ad try again.");
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
            UserBeanLocal userBeanLocal = getUserBean();
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
            UserBeanLocal userBeanLocal = getUserBean();
            userBeanLocal.addToPersonalList(UserUtils.findCurrentUser(), addr);
        }
    }

    private Address toAddress(String addr, HttpServletRequest req) {
        MapBeanLocal mapBeanLocal = getMapBean();
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


    private void redirectToOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().removeAttribute("taxiOrder");
        UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
        req.setAttribute("personal_addr", userBeanLocal.toPersonalAddress(UserUtils.findCurrentUser()));
        req.setAttribute("pageContent", "content/customer-soberService.jsp");
        req.setAttribute("pageType", "soberService");
        req.getRequestDispatcher("/WEB-INF/views/customer/customer-template.jsp")
                .forward(req, resp);
    }

    private MapBeanLocal getMapBean() {
        return BeansLocator.getInstance().getBean(MapBeanLocal.class);
    }

    private UserBeanLocal getUserBean() {
        return BeansLocator.getInstance().getBean(UserBeanLocal.class);
    }

    private PriceBeanLocal getPriceBean() {
        return BeansLocator.getInstance().getBean(PriceBeanLocal.class);
    }
}
