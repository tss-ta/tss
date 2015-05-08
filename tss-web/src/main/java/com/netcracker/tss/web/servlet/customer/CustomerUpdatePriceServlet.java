package com.netcracker.tss.web.servlet.customer;

import java.io.IOException;
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

import com.netcracker.ejb.MapBeanLocal;
import com.netcracker.ejb.MapBeanLocalHome;
import com.netcracker.ejb.PriceBeanLocal;
import com.netcracker.ejb.PriceBeanLocalHome;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocalHome;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.AdditionalParameters;
import com.netcracker.tss.web.util.DateParser;

/**
 * Author Stanislav Zabielin
 */
@WebServlet("/price")
public class CustomerUpdatePriceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdatePriceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        float distance = calculateDistance(request);
        double price;
        PriceBeanLocal priceBean = getPriceBean(request);
        TaxiOrder taxiOrder = (TaxiOrder) request.getSession().getAttribute("taxiOrder");
        if (taxiOrder == null) {
            price = priceBean.calculatePrice(distance,
                    DateParser.parseDate(request), AdditionalParameters.taxiOrderAddParameters(request));
        } else {
            price = priceBean.calculatePrice(distance,
                    DateParser.parseDate(request), taxiOrder);

        }
        String text = String.valueOf(price);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(text);
    }

    private float calculateDistance(HttpServletRequest request) {
        MapBeanLocal mapBean = getMapBean(request);
        try {
            return mapBean.calculateDistance(request.getParameter("fromAddr"),
                    request.getParameter("toAddr"));
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
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
