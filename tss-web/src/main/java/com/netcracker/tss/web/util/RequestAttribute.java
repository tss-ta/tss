package com.netcracker.tss.web.util;

/**
 * Created by happy on 27/04/2015.
 */
public enum RequestAttribute {
    PAGE_CONTENT("pageContent"),
    PAGE_TYPE("pageType"),
    CAR_LIST("carList"),
    CAR("car"),
    DRIVER_LIST("driverList"),
    ERROR_MESSAGE("errorMessage"),
    SUCCESS_MESSAGE("successMessage"),
    PAGER("pager"),
    PAGER_LINK("pagerLink"),
    PAGE_TITLE("pageTitle");

    private String name;

    RequestAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
