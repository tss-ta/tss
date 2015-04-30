package com.netcracker.tss.web.router.config;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
 */

public enum RouterNameDescriber {
    ACTION("action"),
    MENU("menu");

    private String name;

    RouterNameDescriber(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
