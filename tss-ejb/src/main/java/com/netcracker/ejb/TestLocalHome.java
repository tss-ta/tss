package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

public interface TestLocalHome extends EJBLocalHome {
    TestLocal create();
}