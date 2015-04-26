package com.netcracker.tss.web.util;

/**
 * Created by Kyrylo Berehovyi on 26/04/2015.
 */

public enum Page {
    ADMIN_DASHBOARD("dashboard"),
    ADMIN_CARS("cars"),
    ADMIN_CUSTOMERS("customers"),
    ADMIN_DRIVERS("drivers"),
    ADMIN_GROUPS("groups"),
    ADMIN_TARIFFS("tariffs"),
    ADMIN_REPORTS("reports");

    private String pageType;

    Page(String pageType) {
        this.pageType = pageType;
    }

    public String getPageType() {
        return pageType;
    }
}
