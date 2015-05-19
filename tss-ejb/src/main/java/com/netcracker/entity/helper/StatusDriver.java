/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.entity.helper;

/**
 *
 * @author Виктор
 */
public enum StatusDriver {

    QUEUED("QUEUED"), ASSIGNED("ASSIGNED"), IN_PROGRESS("IN_PROGRESS");

    private final String statusDriverName;

    StatusDriver(String rolename) {
        this.statusDriverName = rolename;
    }

    public String getName() {
        return statusDriverName;
    }

    public static StatusDriver[] getDriverStatus() {
        StatusDriver[] status = {StatusDriver.QUEUED, StatusDriver.ASSIGNED, StatusDriver.IN_PROGRESS};
        return status;
    }
}
