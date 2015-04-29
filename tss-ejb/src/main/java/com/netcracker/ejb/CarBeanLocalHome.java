package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
 */

public interface CarBeanLocalHome extends EJBLocalHome {

    CarBeanLocal create();
}
