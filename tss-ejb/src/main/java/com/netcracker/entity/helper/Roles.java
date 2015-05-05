package com.netcracker.entity.helper;

public enum Roles {
    ADMIN("ADMIN"), DRIVER("DRIVER"), CUSTOMER("CUSTOMER"), BANNED("BANNED");

    private final String rolename;

    Roles(String rolename) {
        this.rolename = rolename;
    }

    public String getRolename() {
        return rolename;
    }
}
