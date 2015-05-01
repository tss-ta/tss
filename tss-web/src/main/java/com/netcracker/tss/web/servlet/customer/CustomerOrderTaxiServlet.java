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

import org.json.JSONException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.netcracker.dao.UserDAO;
import com.netcracker.ejb.MapBeanLocal;
import com.netcracker.ejb.MapBeanLocalHome;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocalHome;
import com.netcracker.entity.Address;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.DateParser;
import java.util.Map;

/**
 * Created by Stanislav Zabielin
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
        User user = findCurrentUser();
        TaxiOrderBeanLocal taxiOrderBeanLocal = getTaxiOrderBean(req);
        Route route = new Route(findCurrentUser().getUsername() + " Route");
        Address addFrom = toAddress(req.getParameter("fromAddr"), req);
        Address addTo = toAddress(req.getParameter("toAddr"), req);
        TaxiOrder taxiOrder = new TaxiOrder(taxiOrderAddParameters(req));
        taxiOrder.setBookingTime(new Date());
        Date orderTime = DateParser.parseDate(req);
        orderTime.setYear(new Date().getYear());
        taxiOrder.setOrderTime(orderTime);
        taxiOrderBeanLocal.addTaxiOrder(user, route, addFrom, addTo, taxiOrder);
        req.setAttribute("added", "success");
        req.setAttribute("pageContent", "content/customer-order.jsp");
		req.getRequestDispatcher("/WEB-INF/views/customer/customer-template.jsp")
				.forward(req, resp);
    }
    private TaxiOrder taxiOrderAddParameters (HttpServletRequest req) {
        Integer carType = checkString(req.getParameter("carType"));
        Integer wayOfPayment = checkString(req.getParameter("paymentType"));
        Boolean driversGender = checkDriversGender(req.getParameter("driverGender"));
        Integer musicType = checkString(req.getParameter("musicType"));
        String[] addParameters = req.getParameterValues("addOptions");
        Boolean wifi = null;
        Boolean animal = null;
        Boolean noSmokeDriver = null;
        Boolean conditioner = null;
        if (addParameters != null) {
            for (String st : addParameters) {
                if ("wifi".equals(st)) {
                    wifi = Boolean.TRUE;
                }
                if ("animal".equals(st)) {
                    animal = Boolean.TRUE;
                }
                if ("nosmoke".equals(st)) {
                    noSmokeDriver = Boolean.TRUE;
                }
                if ("conditioner".equals(st)) {
                    conditioner = Boolean.TRUE;
                }
            }
        }
    return new TaxiOrder(wayOfPayment, musicType, driversGender, noSmokeDriver, carType, animal, wifi, conditioner);
    }
    private Boolean checkDriversGender(String s) {
        if (!"".equals(s)) {
            if ("male".equals(s)) {
                return true;
            } else {
                return false;
            }
        }
        return null;
    }

    private Integer checkString(String s) {
        if (!"".equals(s)) {
            return Integer.parseInt(s);
        }
        return null;
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

}
