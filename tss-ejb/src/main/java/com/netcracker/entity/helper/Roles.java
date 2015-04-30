package com.netcracker.entity.helper;

/**
 * Created by Illya on 25.04.2015.
 */
public enum Roles {
    ADMIN("ADMIN"), DRIVER("DRIVER"), CUSTOMER("CUSTOMER");

    private final String dbRolename;

    Roles(String dbRolename) {
        this.dbRolename = dbRolename;
    }

    public String getDBRolename() {
        return dbRolename;
    }
}
