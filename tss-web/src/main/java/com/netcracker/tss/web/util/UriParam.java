package com.netcracker.tss.web.util;

/**
 * @author Kyrylo Berehovyi
 */
public enum UriParam {
    MENU("menu"),
    ACTION("action");

    private String paramName;

    UriParam(String paramName) {
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }
}
