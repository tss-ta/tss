package com.netcracker.router;

/**
 * @author Kyrylo Berehovyi
 */

public enum HttpMethod {
    POST("post"),
    GET("get");

    private String name;

    HttpMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
