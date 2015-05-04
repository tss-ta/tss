package com.netcracker.tss.web.servlet.admin;

import com.netcracker.ejb.ReportsBean;
import com.netcracker.ejb.ReportsBeanLocal;
import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.tss.web.util.DateParser;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.BeansLocator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kyrylo Berehovyi on 25/04/2015.
 *
 * @author maks
 */
@WebServlet(urlPatterns = "/admin/report")
public class AdminReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if ("overall-popular-car-options".equals(action)) {
            redirectToOverallCarOptionsReports(req, resp);
        } else if ("customer-popular-car-options".equals(action)) {
            redirectToUsers(req, resp);
        } else if ("user-car-options-report".equals(action)) {
            redirectToCustomerCarOptionsReports(req, resp);
        } else if ("new-orders-report".equals(action)) {
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_NEW_ORDERS_REPORTS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } else if ("search-users".equals(action)) {
            searchUsers(req, resp);
        } else if ("new-orders-per-period".equals(action)) {
            redirectToNewOrdersReport(req, resp);

        } else {
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_REPORTS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        }
    }

    private Date dateParser(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm, dd MM yyyy", Locale.US);
            Date date = dateFormat.parse(dateString);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(AdminReportServlet.class.getName()).log(Level.SEVERE, "Can't covert to Date", ex);
            return Calendar.getInstance().getTime();
        }
    }

    private void redirectToNewOrdersReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        Date begin = dateParser(req.getParameter("begin"));
        Date end = dateParser(req.getParameter("end"));
        req.setAttribute("orders", reportsBean.getBookedOrders(begin, end, 1, 10));//!!!!!!!
        req.setAttribute("allTO", reportsBean.countAllOrders(begin, end));
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_NEW_ORDERS_REPORTS_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
    }

    private void redirectToOverallCarOptionsReports(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        req.setAttribute("report", reportsBean.getCarOptionsReport());
        req.setAttribute("allTO", reportsBean.countAllOrders());
        req.setAttribute("user", "All");
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CAR_OPTIONS_REPORTS_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
    }

    private void redirectToCustomerCarOptionsReports(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userid"));
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        req.setAttribute("report", reportsBean.getCustomerCarOptionsReport(userId));
        req.setAttribute("allTO", reportsBean.countAllOrders(userId));
        req.setAttribute("user", req.getParameter("email"));
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CAR_OPTIONS_REPORTS_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
    }

    private void redirectToUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            req.setAttribute("customers", userBeanLocal.getCustomers(1, 10));
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CHOOSE_USER_REPORTS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } catch (RuntimeException e) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't show users", e);
            req.getRequestDispatcher("/500.jsp").forward(req, resp);
        }
    }

    private void searchUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            req.setAttribute("customers", userBeanLocal.searchUsersByEmail(req.getParameter("email"), 1, 10));
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CHOOSE_USER_REPORTS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } catch (RuntimeException e) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't show users", e);
            req.getRequestDispatcher("/500.jsp").forward(req, resp);
        }
    }
}
