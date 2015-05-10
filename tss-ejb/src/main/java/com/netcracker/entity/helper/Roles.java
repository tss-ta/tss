package com.netcracker.entity.helper;

public enum Roles {
    ADMIN("ADMIN"), DRIVER("DRIVER"), CUSTOMER("CUSTOMER"), BANNED("BANNED"), READ_ONLY("READ ONLY"), PRIVILEGED("PRIVILEGED");

    private final String rolename;

    Roles(String rolename) {
        this.rolename = rolename;
    }

    public String getFormattedName() {
        return rolename;
    }

    public static Roles[] getUserRoles (){
        Roles [] roles = {Roles.ADMIN, Roles.DRIVER, Roles.CUSTOMER, Roles.BANNED};
        return roles;
    }
    public static Roles[] getGroupRoles (){
        Roles [] roles = {Roles.PRIVILEGED, Roles.READ_ONLY};
        return roles;
    }
}
