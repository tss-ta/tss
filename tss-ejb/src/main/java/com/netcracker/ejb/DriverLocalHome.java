package com.netcracker.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 * Created by Illya on 26.04.2015.
 */
public interface DriverLocalHome extends EJBLocalHome {
    com.netcracker.ejb.DriverLocal create();
}
