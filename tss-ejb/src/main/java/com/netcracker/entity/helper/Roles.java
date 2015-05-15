package com.netcracker.entity.helper;

public enum Roles {
    ADMIN("ADMIN"), DRIVER("DRIVER"), CUSTOMER("CUSTOMER"), BANNED("BANNED"),
    READ_ONLY("READ ONLY"), PRIVILEGED("PRIVILEGED"),
    MAJOR_ADMIN("MAJOR ADMIN"), RELIABLE_DRIVER("RELIABLE DRIVER"), REGULAR_CUSTOMER("REGULAR CUSTOMER");

    private final String rolename;

    Roles(String rolename) {
        this.rolename = rolename;
    }

    /**
     *
     * @return Well formatted for user role name
     */
    public String getFormattedName() {
        return rolename;
    }

    /**
     *
     * @return Array of main Roles that can have user
     */
    public static Roles[] getMainUserRoles(){
        Roles [] roles = {CUSTOMER, ADMIN, DRIVER, BANNED};
        return roles;
    }

    /**
     *
     * @return Array of Roles that can be attached to main user role
     */
    public static Roles[] getSubroles (Roles role){
        Roles [] roles;
        switch (role){
            case ADMIN: roles = new Roles[] {MAJOR_ADMIN, BANNED};
                break;
            case CUSTOMER: roles = new Roles[] {REGULAR_CUSTOMER, BANNED};
                break;
            case DRIVER: roles = new Roles[] {RELIABLE_DRIVER, BANNED};
                break;
            default: roles = new Roles[] {ADMIN, CUSTOMER, BANNED};
        }
        return roles;
    }

    /**
     *
     * @return Array of Roles that can be attached to group
     */
    public static Roles[] getGroupRoles (){
        Roles [] roles = {PRIVILEGED, READ_ONLY};
        return roles;
    }
}
