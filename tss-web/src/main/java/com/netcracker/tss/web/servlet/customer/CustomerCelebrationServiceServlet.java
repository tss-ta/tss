package com.netcracker.tss.web.servlet.customer;

import com.netcracker.dao.ContactsDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.ejb.CelebrationServiceBeanLocal;
import com.netcracker.ejb.MapBeanLocal;
import com.netcracker.ejb.PriceBeanLocal;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.entity.Address;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.CarCategory;
import com.netcracker.entity.helper.Category;
import com.netcracker.entity.helper.Status;
import com.netcracker.tss.web.util.DateParser;
import com.netcracker.tss.web.util.UserUtils;
import com.netcracker.util.BeansLocator;
import org.json.JSONException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author Illia Rudenko
 */
@Deprecated
@WebServlet(urlPatterns = "/customer/selebrService")
public class CustomerCelebrationServiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>> Hello!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CelebrationServiceBeanLocal celBean = BeansLocator.getInstance().getBean(CelebrationServiceBeanLocal.class);

        String driversAmountStr = req.getParameter("driversAmount");
        String durationStr = req.getParameter("duration");
        String fromAddress = req.getParameter("fromAddr");
        String priceStr = req.getParameter("price");



        if(checkParameter(driversAmountStr) &&
           checkParameter(durationStr) &&
           checkParameter(fromAddress)) {
            Date orderTime = DateParser.parseDate(req);
            Integer driversAmount = Integer.parseInt(driversAmountStr);
            Integer duration = Integer.parseInt(durationStr);

            TaxiOrder taxiOrder = new TaxiOrder();
            taxiOrder.setStatus(Status.QUEUED);
            taxiOrder.setBookingTime(new Date());
            taxiOrder.setOrderTime(orderTime);
            taxiOrder.setPayment(0);
            taxiOrder.setCarCategory(CarCategory.BUSINESS.getId());

            User user = findCurrentUser();

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
            req.setAttribute("taxiOrderId", latestTOId);
            req.setAttribute("pageContent", "content/confirmation.jsp");
            req.getRequestDispatcher(
                    "/WEB-INF/views/customer/customer-template.jsp").forward(
                    req, resp);

        } else {
            req.setAttribute("errorMessage", "Please, fill all mandatory fields!");
            resp.sendRedirect("/customer/celebrServicePage");
        }


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

    private User findCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        UserDAO userDao = new UserDAO();
        User user = userDao.getByEmail(userDetails.getUsername());
        userDao.close();
        return user;
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

    private boolean checkParameter(String parameter) {
        return parameter != null && !"".equals(parameter);
    }

    private double roundToHundredth(double d) {
        return Math.rint(d * 100.0) / 100.0;
    }
}
