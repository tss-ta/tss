package com.netcracker.entity.helpEntity;

/**
 * Created by Illya on 25.04.2015.
 */
public enum Roles {
    ADMIN("admin"), DRIVER("driver"), CUSTOMER("customer");

    private final String catStr;

    Roles(String catStr) {
        this.catStr = catStr;
    }

    public String getCatStr() {
        return catStr;
    }
}
