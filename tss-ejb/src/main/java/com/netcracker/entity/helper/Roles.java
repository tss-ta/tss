package com.netcracker.entity.helper;

/**
 * Created by Illya on 25.04.2015.
 */
public enum Roles {
    ADMIN("ADMIN"), DRIVER("DRIVER"), CUSTOMER("CUSTOMER");

    private final String catStr;

    Roles(String catStr) {
        this.catStr = catStr;
    }

    public String getCatStr() {
        return catStr;
    }
}
