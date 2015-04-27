package com.netcracker.tss.web.util;

/**
 * Created by happy on 27/04/2015.
 */
public enum RequestAttribute {
    PAGE_CONTENT("pageContent"),
    PAGE_TYPE("pageType"),
    CAR_LIST("carList"),
    DRIVER_LIST("driverList"),
    ALERT_MESSAGE("alertMessage");

    private String name;

    RequestAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
