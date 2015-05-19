package com.netcracker.tss.web.util;

/**
 * @author Kyrylo Berehovyi
 */
public enum RequestParameter {
    ID("id"),
    PAGE("page"),
    SEARCH_WORD("search"),
    REPORT_NAME("name"),
    REPORT_DESCRIPTION("description"),
    REPORT_SELECT_QUERY("selectQuery"),
    REPORT_COUNT_QUERY("countQuery"),
    REPORT_PAGE_SIZE("pageSize"),
    REPORT_EXPORT_SIZE("exportSize"),
    REPORT_COUNTABLE("countable");

    private String value;

    RequestParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
