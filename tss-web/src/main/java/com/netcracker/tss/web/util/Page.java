package com.netcracker.tss.web.util;

/**
 * Created by Kyrylo Berehovyi on 26/04/2015.
 */

public enum Page {
    ADMIN_TEMPLATE("template", "admin/admin-template.jsp"),
    ADMIN_DASHBOARD_CONTENT("dashboard", "admin/content/dashboard.jsp"),
    ADMIN_CARS_CONTENT("cars", "admin/content/cars.jsp"),
    ADMIN_ADD_CAR_CONTENT("cars", "admin/content/add-car.jsp"),
    ADMIN_CUSTOMERS_CONTENT("customers", "admin/content/customers.jsp"),
    ADMIN_DRIVERS_CONTENT("drivers", "admin/content/drivers.jsp"),
    ADMIN_ADD_DRIVER_CONTENT("drivers", "admin/content/add-driver.jsp"),
    ADMIN_GROUPS_CONTENT("groups", "admin/content/groups.jsp"),
    ADMIN_ADD_GROUP_CONTENT("groups", "admin/content/add-group.jsp"),
    ADMIN_TARIFFS_CONTENT("tariffs", "admin/content/tariffs.jsp"),
    ADMIN_REPORTS_CONTENT("reports", "admin/content/reports.jsp"),
    ERROR_500_CONTENT("", "admin/content/500.jsp");

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
