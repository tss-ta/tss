package com.netcracker.tss.web.util;

/**
 * @author Kyrylo Berehovyi
 */
public enum RequestParameter {
    PAGE("page");

    private String value;

    RequestParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
