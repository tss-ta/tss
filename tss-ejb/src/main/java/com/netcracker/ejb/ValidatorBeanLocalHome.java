package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

/**
 * @author Kyrylo Berehovyi
 */
public interface ValidatorBeanLocalHome extends EJBLocalHome {
    ValidatorBeanLocal create();
}
