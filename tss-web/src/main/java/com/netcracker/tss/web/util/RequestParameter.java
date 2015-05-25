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
    REPORT_COUNTABLE("countable"),
    REPORT_FILTER_CRITERIA_AMOUNT("crAmount"),
    REPORT_FILTER_CRITERION_ID_PREFIX("crId"),
    REPORT_FILTER_CRITERION_NAME_PREFIX("crName"),
    REPORT_FILTER_CRITERION_TYPE_PREFIX("crType"),
    REPORT_FILTER_SEQUENTIAL_NUMBER_PREFIX("crSeqNum"),
    REPORT_FILTERABLE("filterable");

    private String value;

    RequestParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
