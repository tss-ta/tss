package com.netcracker.router;

/**
 * @author Kyrylo Berehovyi
 */
public enum ContentType {
    HTML("text/html"),
    JSON("application/json");

    private String header;

    ContentType(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}
