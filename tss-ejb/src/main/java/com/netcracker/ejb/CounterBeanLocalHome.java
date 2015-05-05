package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

/**
 * @author Kyrylo Berehovyi
 */
public interface CounterBeanLocalHome extends EJBLocalHome {
    CounterBeanLocal create();
}
