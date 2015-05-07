package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

/**
 * @author Kyrylo Berehovyi
 */

public interface PageCalculatorBeanLocalHome extends EJBLocalHome {
    PageCalculatorBeanLocal create();
}
