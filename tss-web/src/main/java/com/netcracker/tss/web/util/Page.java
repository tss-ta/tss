package com.netcracker.tss.web.util;

/**
 * @author Kyrylo Berehovyi
 * @author maks
 */

public enum Page {

    ADMIN_TEMPLATE("template", "admin/admin-template.jsp"),
    ADMIN_DASHBOARD_CONTENT("dashboard", "admin/content/dashboard.jsp"),
    ADMIN_CARS_CONTENT("cars", "admin/content/cars.jsp"),
    ADMIN_ADD_CAR_CONTENT("cars", "admin/content/add-car.jsp"),
    ADMIN_VIEW_CAR_CONTENT("cars", "admin/content/view-car.jsp"),
    ADMIN_USERS_CONTENT("customers", "admin/content/users.jsp"),
    ADMIN_ADD_ROLES_CONTENT("customers", "admin/content/add-roles.jsp"),
    ADMIN_DRIVERS_CONTENT("drivers", "admin/content/drivers.jsp"),
    ADMIN_ADD_DRIVER_CONTENT("drivers", "admin/content/add-driver.jsp"),
    ADMIN_SEND_TOKEN_CONTENT("drivers", "admin/content/send-token.jsp"),
    ADMIN_GROUPS_CONTENT("groups", "admin/content/groups.jsp"),
    ADMIN_ADD_GROUP_CONTENT("groups", "admin/content/add-group.jsp"),
    ADMIN_ADD_TO_GROUP_CONTENT("groups", "admin/content/add-users-to-group.jsp"),
    ADMIN_TARIFFS_CONTENT("tariffs", "admin/content/tariffs.jsp"),
    ADMIN_EDIT_TARIFFS_CONTENT("tariffs", "admin/content/edit-tariffs.jsp"),
    ADMIN_REPORTS_CONTENT("reports", "admin/content/reports.jsp"),
    ADMIN_REPORT_CONTENT("reports", "admin/content/report.jsp"),
    ADMIN_ADD_REPORT_CONTENT("reports", "admin/content/add-report.jsp"),
    ADMIN_REPORT_INFO_CONTENT("reports", "admin/content/report-info.jsp"),
    ADMIN_CAR_OPTIONS_REPORTS_CONTENT("reports", "admin/content/car-options-report.jsp"),
    ADMIN_NEW_ORDERS_REPORTS_CONTENT("reports", "admin/content/new-orders-report.jsp"),
    ADMIN_CHOOSE_USER_REPORTS_CONTENT("reports", "admin/content/choose-customer.jsp"),
    ERROR_500_CONTENT("", "admin/content/500.jsp"),
    ERROR_404_CONTENT("", "content/404.jsp"),
    INCORRECT_ID_CONTENT("", "content/incorrect-id.jsp"),

    CUSTOMER_CELEBRATION_SERVICE_CONTENT("celebrService", "customer/content/customer-celebrService.jsp"),
    CUSTOMER_TEMPLATE_CONTENT("", "customer/customer-template.jsp"),
    TAXI_ORDER_CONFIRMATION_CONTENT("", "customer/content/confirmation.jsp");


    public static final String ROOT_PATH_PREFIX = "/WEB-INF/views/";

    private String type;
    private String relativePath;

    Page(String type, String relativePath) {
        this.type = type;
        this.relativePath = relativePath;
    }

    public String getType() {
        return type;
    }

    public String getAbsolutePath() {
        return ROOT_PATH_PREFIX + relativePath;
    }
}
