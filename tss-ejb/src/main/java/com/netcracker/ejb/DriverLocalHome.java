package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

/**
 * @author Illia Rudenko
 */

public interface DriverLocalHome extends EJBLocalHome {
    com.netcracker.ejb.DriverLocal create();
}
