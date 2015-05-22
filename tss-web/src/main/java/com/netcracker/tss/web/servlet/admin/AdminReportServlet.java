package com.netcracker.tss.web.servlet.admin;

import com.netcracker.ejb.ReportsBeanLocal;
import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.entity.helper.Roles;
import com.netcracker.tss.web.util.*;
import com.netcracker.util.BeansLocator;
import com.netcracker.util.ExcelExport;
import com.netcracker.util.reports.ReportsRow;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kyrylo Berehovyi
 * @author maks
 */

@WebServlet(urlPatterns = "/admin/report")
public class AdminReportServlet extends HttpServlet {

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final Integer MIN_PAGE_NUMBER = 1;

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
        } else if ("popular-car-category".equals(action)) {
            redirectToCarCategoryReport(req, resp);
        } else if ("new-orders-per-period".equals(action)) {
            redirectToNewOrdersReport(req, resp);
        } else if ("overall-car-options-to-excel".equals(action)) {
            sendOverallCarOptionsReport(resp);
        } else if ("car-category-to-excel".equals(action)) {
            sendCarCategoryReport(resp);
        } else if ("new-orders-excel".equals(action)) {
            sendOrdersReport(req, resp);
        } else if ("to-excel".equals(action)) {
            toExcel(req, resp);
        } else {
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_REPORTS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        }
    }

    private void toExcel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("reptype");
        if ("car-category".equals(action)){
            sendCarCategoryReport(resp);
        } else if ("overall-car-options".equals(action)){
            sendOverallCarOptionsReport(resp);
        } else if ("user-car-options".equals(action)){
            sendUserCarOptionsReport(req, resp);
        }
    }

    private Date dateParser(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm_dd-MM-yyyy", Locale.US);
            Date date = dateFormat.parse(dateString);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(AdminReportServlet.class.getName()).log(Level.SEVERE, "Can't covert to Date", ex);
            return Calendar.getInstance().getTime();
        }
    }

    private void redirectToNewOrdersReport(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        Integer page = parsePageNumberFromRequest(request);
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        Date begin = dateParser(request.getParameter("begin"));
        Date end = dateParser(request.getParameter("end"));
        request.setAttribute(RequestAttribute.PAGER.getName(),
                reportsBean.getOrdersReportPager(page, DEFAULT_PAGE_SIZE, begin, end));
        request.setAttribute("orders", reportsBean.getBookedOrders(begin, end, page, DEFAULT_PAGE_SIZE));
        request.setAttribute("allTO", reportsBean.countAllOrders(begin, end));
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
        request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_NEW_ORDERS_REPORTS_CONTENT.getAbsolutePath());
        request.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(request, resp);
    }
        
    private void redirectToCarCategoryReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        req.setAttribute("report", reportsBean.getCarCategoryReport());
        req.setAttribute("allTO", reportsBean.countAllOrders());
        req.setAttribute("header", "Most Popular Car Categories");
        req.setAttribute("reptype", "car-category");
        redirectToCarReport(req, resp);
    }

    private void sendCarCategoryReport(HttpServletResponse response) throws ServletException, IOException {
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        File excelFile = new ExcelExport().exportReportRows("Most Popular Car Categories for customer ",
                reportsBean.countAllOrders(), reportsBean.getCarCategoryReport());
        sendFile(excelFile, response);
    }

    private void sendOverallCarOptionsReport(HttpServletResponse response) throws ServletException, IOException {
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        File excelFile = new ExcelExport().exportReportRows("Most Popular Car Options Overall",
                reportsBean.countAllOrders(), reportsBean.getCarOptionsReport());
        sendFile(excelFile, response);
    }

    private void sendUserCarOptionsReport(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userid"));
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        File excelFile = new ExcelExport().exportReportRows(req.getParameter("header"),
                reportsBean.countAllOrders(userId), reportsBean.getCustomerCarOptionsReport(userId));
        sendFile(excelFile, response);
    }

    private void sendOrdersReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        Date begin = dateParser(request.getParameter("begin"));
        Date end = dateParser(request.getParameter("end"));
        File excelFile = new ExcelExport().exportOrdersReport(begin, end,
                reportsBean.countAllOrders(begin, end), reportsBean.getBookedOrders(begin, end));
        sendFile(excelFile, response);
    }

    private void sendFile(File excelFile, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition","attachment; filename=report.xls");
         OutputStream out = response.getOutputStream();
         FileInputStream in = new FileInputStream(excelFile);
         byte[] buffer = new byte[4096];
         int length;
         while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
         }
         in.close();
         out.flush();
        excelFile.delete();
    }
        @Deprecated
    private void redirectToOverallCarOptionsReports(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        req.setAttribute("report", reportsBean.getCarOptionsReport());
        req.setAttribute("allTO", reportsBean.countAllOrders());
        req.setAttribute("header", "Most Popular Car Options Overall");
        req.setAttribute("reptype", "overall-car-options");
        redirectToCarReport(req, resp);
    }

    private void redirectToCustomerCarOptionsReports(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userid"));
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        req.setAttribute("report", reportsBean.getCustomerCarOptionsReport(userId));
        req.setAttribute("allTO", reportsBean.countAllOrders(userId));
        req.setAttribute("header", "Popular Car Options For " + req.getParameter("email"));
        req.setAttribute("reptype", "user-car-options&userid=" + userId);
        redirectToCarReport(req, resp);
    }
        @Deprecated
    private void redirectToCarReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CAR_OPTIONS_REPORTS_CONTENT.getAbsolutePath());
        req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
    }

    private void redirectToUsers(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer page = parsePageNumberFromRequest(request);
            UserBeanLocal userBean = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            request.setAttribute(RequestAttribute.PAGER.getName(),
                    userBean.getPager(page, DEFAULT_PAGE_SIZE, Roles.CUSTOMER));
            request.setAttribute("customers", userBean.getCustomers(page, DEFAULT_PAGE_SIZE));
            request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
            request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CHOOSE_USER_REPORTS_CONTENT.getAbsolutePath());
            request.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(request, resp);
        } catch (RuntimeException e) {
            Logger.getLogger(AdminReportServlet.class.getName()).log(Level.SEVERE,
                    "Can't show customers", e);
            request.getRequestDispatcher("/500.jsp").forward(request, resp);
        }
    }

    private void searchUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer page = parsePageNumberFromRequest(req);
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            String emailPart = req.getParameter("email");
            req.setAttribute(RequestAttribute.PAGER.getName(),
                    userBeanLocal.getPager(page, DEFAULT_PAGE_SIZE, Roles.CUSTOMER, emailPart));
            req.setAttribute("customers", userBeanLocal.searchUsersByEmail(emailPart, page, DEFAULT_PAGE_SIZE));
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CHOOSE_USER_REPORTS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } catch (RuntimeException e) {
            Logger.getLogger(AdminReportServlet.class.getName()).log(Level.SEVERE,
                    "Can't show users", e);
            req.getRequestDispatcher("/500.jsp").forward(req, resp);
        }
    }

    private Integer parsePageNumberFromRequest(HttpServletRequest request) {
        Integer pageNumber = RequestParameterParser.parseInteger(request, RequestParameter.PAGE.getValue());
        if (pageNumber == null || pageNumber < MIN_PAGE_NUMBER) {
            return MIN_PAGE_NUMBER;
        }
        return pageNumber;
    }
}
