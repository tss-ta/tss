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
    PAGE_TITLE("pageTitle"),
    REPORT("report"),
    REPORT_LIST("reportList"),
    REPORT_INFO("reportInfo"),
    FORM_TYPE("formType"),
    FORM_EDIT_TYPE("edit"),
    FORM_CREATE_TYPE("create");

    private String name;

    RequestAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
