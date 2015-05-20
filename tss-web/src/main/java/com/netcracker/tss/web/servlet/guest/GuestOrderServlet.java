package com.netcracker.tss.web.servlet.guest;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.util.BeansLocator;
import org.json.JSONException;

import com.netcracker.ejb.MapBeanLocal;
import com.netcracker.ejb.PriceBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.entity.Address;
import com.netcracker.entity.Route;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.tss.web.util.AdditionalParameters;
import com.netcracker.tss.web.util.DateParser;
import com.netcracker.tss.web.util.UserUtils;

/**
 * Servlet for quick taxi order on home page as guest
 * @author maks
 */
@WebServlet(urlPatterns = "/guest/order")
public class GuestOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(name, email);
        TaxiOrderBeanLocal taxiOrderBeanLocal = getTaxiOrderBean(req);
        PriceBeanLocal priceBean = getPriceBean(req);
        float distance = 0;
        double price;

        Route route = new Route("Guest Route");
        route.setDistance(distance);
        Address addFrom = toAddress(req.getParameter("fromAddr"), req);
        Address addTo = toAddress(req.getParameter("toAddr"), req);
        TaxiOrder taxiOrder = new TaxiOrder(AdditionalParameters.taxiOrderAddParameters(req));

            MapBeanLocal mapBean = getMapBean(req);
            distance = mapBean.calculateDistance(req.getParameter("fromAddr"),
                    req.getParameter("toAddr"));


        price = priceBean.calculatePrice(distance,
                DateParser.parseDate(req), taxiOrder, UserUtils.findCurrentUser());
        taxiOrder.setBookingTime(new Date());
        Date orderTime = DateParser.parseDate(req);
        taxiOrder.setOrderTime(orderTime);
        taxiOrder.setPrice(price);
        taxiOrderBeanLocal.addTaxiOrder(user, route, addFrom, addTo, taxiOrder);
        int latestTOId = taxiOrderBeanLocal.getTaxiOrderHistory(1, 1, user).get(0).getId();
        req.setAttribute("taxiOrderId", latestTOId);
        req.getRequestDispatcher("/WEB-INF/views/customer/guest-confirmation.jsp")
                .forward(req, resp);

        } catch (Exception e) {
            // TODO
            Logger.getLogger(GuestOrderServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            resp.sendRedirect("/incorrect-address.jsp");
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

    private TaxiOrderBeanLocal getTaxiOrderBean(HttpServletRequest req) {
        return BeansLocator.getInstance().getBean(TaxiOrderBeanLocal.class);
    }

    private MapBeanLocal getMapBean(HttpServletRequest req) {
        return BeansLocator.getInstance().getBean(MapBeanLocal.class);
    }

    private PriceBeanLocal getPriceBean(HttpServletRequest req) {
        return BeansLocator.getInstance().getBean(PriceBeanLocal.class);
    }
}
