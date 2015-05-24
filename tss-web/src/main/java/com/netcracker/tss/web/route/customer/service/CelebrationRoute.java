package com.netcracker.tss.web.route.customer.service;

import com.netcracker.dao.ContactsDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.ejb.*;
import com.netcracker.entity.Address;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.CarCategory;
import com.netcracker.entity.helper.PersonalAddress;
import com.netcracker.entity.helper.Status;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.DateParser;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.tss.web.util.UserUtils;
import com.netcracker.util.BeansLocator;
import org.json.JSONException;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Illia Rudenko
 */

@ActionRoute(menu = "celebration")
public class CelebrationRoute {

    public static final String ATTRIBUTE_TAXI_ORDER = "taxiOrder";
    public static final String ATTRIBUTE_TAXI_ORDER_ID = "taxiOrderId";

    public static final String ATTRIBUTE_PERSONAL_ADDRESS = "personal_addr";


    @Action(action = "addCelebration", httpMethod = HttpMethod.GET)
    public ActionResponse getCelebrationPage(HttpServletRequest req) {
        req.getSession().removeAttribute(ATTRIBUTE_TAXI_ORDER);

        req.setAttribute(ATTRIBUTE_PERSONAL_ADDRESS, getPersonalAddresses());
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.CUSTOMER_CELEBRATION_SERVICE_CONTENT.getType());
        ActionResponse actResp = new ActionResponse();
        actResp.setPageContent(Page.CUSTOMER_CELEBRATION_SERVICE_CONTENT.getAbsolutePath());

        return actResp;
    }

    @Action(action = "addCelebration", httpMethod = HttpMethod.POST)
    public ActionResponse addCelebration(HttpServletRequest req) {
        CelebrationServiceBeanLocal celBean = BeansLocator.getInstance().getBean(CelebrationServiceBeanLocal.class);

        String driversAmountStr = req.getParameter("driversAmount");
        String durationStr = req.getParameter("duration");
        String fromAddress = req.getParameter("fromAddr");
        String priceStr = req.getParameter("price");

        ActionResponse actResp = new ActionResponse();

        if(checkParameter(driversAmountStr) &&
                checkParameter(durationStr) &&
                checkParameter(fromAddress)) {
            Date orderTime = DateParser.parseDate(req);
            Date bookingTime = new Date();
            if (orderTime.before(bookingTime)){
                actResp.setErrorMessage("It is impossible to order taxi at the past! Please input the correct order time.");
                req.getSession().removeAttribute(ATTRIBUTE_TAXI_ORDER);

                req.setAttribute(ATTRIBUTE_PERSONAL_ADDRESS, getPersonalAddresses());
                req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.CUSTOMER_CELEBRATION_SERVICE_CONTENT.getType());
                actResp.setPageContent(Page.CUSTOMER_CELEBRATION_SERVICE_CONTENT.getAbsolutePath());
                return actResp;
            }
            Integer driversAmount = Integer.parseInt(driversAmountStr);
            Integer duration = Integer.parseInt(durationStr);

            TaxiOrder taxiOrder = new TaxiOrder();
            taxiOrder.setStatus(Status.QUEUED);
            taxiOrder.setBookingTime(new Date());
            taxiOrder.setOrderTime(orderTime);
            taxiOrder.setPayment(0);
            taxiOrder.setCarCategory(CarCategory.BUSINESS.getId());

            User user = UserUtils.findCurrentUser();

            double servicePrice;
            if(checkParameter(priceStr)) {
                servicePrice = Double.parseDouble(priceStr);
            } else {
                PriceBeanLocal priceBean = BeansLocator.getInstance().getBean(PriceBeanLocal.class);
                servicePrice = priceBean.calculateCelebrationServicePrice(driversAmount, duration,
                        orderTime, user);
            }

            taxiOrder.setPrice(roundToHundredth(servicePrice));
            taxiOrder.setContactsId(createContacts(user));

            celBean.addCelebrationService(taxiOrder,
                    toAddress(fromAddress),
                    null,
                    driversAmount,
                    duration);

            int latestTOId = BeansLocator.getInstance()
                    .getBean(TaxiOrderBeanLocal.class)
                    .getTaxiOrderHistory(1, 1, user)
                    .get(0).getId();


            req.setAttribute(ATTRIBUTE_TAXI_ORDER_ID, latestTOId);
            actResp.setPageContent(Page.TAXI_ORDER_CONFIRMATION_CONTENT.getAbsolutePath());
        } else {
            actResp.setErrorMessage("Please, check all input parameters and correct it!");
            req.getSession().removeAttribute(ATTRIBUTE_TAXI_ORDER);

            req.setAttribute(ATTRIBUTE_PERSONAL_ADDRESS, getPersonalAddresses());
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.CUSTOMER_CELEBRATION_SERVICE_CONTENT.getType());
            actResp.setPageContent(Page.CUSTOMER_CELEBRATION_SERVICE_CONTENT.getAbsolutePath());
        }

        return actResp;
    }

    private Contacts createContacts(User user) {
        ContactsDAO contactsDAO = null;
        UserDAO userDAO = null;
        Contacts contacts = null;
        try {
            userDAO = new UserDAO();
            User userFromDB = null;
            try {
                userFromDB = userDAO.getByEmail(user.getEmail());
            } catch (NoResultException nre) {
            }
            contactsDAO = new ContactsDAO();
            if (userFromDB != null) {
                contactsDAO.persist(new Contacts(userFromDB));
            } else {
                contactsDAO.persist(new Contacts(user.getUsername(), user
                        .getEmail()));
            }
            contacts = contactsDAO.getByEmail(user.getEmail());
        } finally {
            if (userDAO != null) {
                userDAO.close();
            }
            if (contactsDAO != null) {
                contactsDAO.close();
            }
        }
        return contacts;
    }

    private Address toAddress(String addr) {
        MapBeanLocal mapBeanLocal = BeansLocator.getInstance().getBean(MapBeanLocal.class);
        double[] from = { 0, 0 };
        try {
            from = mapBeanLocal.geocodeAddress(addr);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return new Address((float) from[1], (float) from[0]);
    }


    private boolean checkParameter(String parameter) {
        return parameter != null && !"".equals(parameter);
    }

    private double roundToHundredth(double d) {
        return Math.rint(d * 100.0) / 100.0;
    }

    private List<PersonalAddress> getPersonalAddresses() {
        UserBeanLocal userBeanLocal = BeansLocator.getInstance().getUserBean();
        return userBeanLocal.toPersonalAddress(UserUtils.findCurrentUser());
    }

}
