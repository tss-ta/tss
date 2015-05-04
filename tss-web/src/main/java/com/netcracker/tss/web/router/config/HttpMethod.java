package com.netcracker.tss.web.router.config;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
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
