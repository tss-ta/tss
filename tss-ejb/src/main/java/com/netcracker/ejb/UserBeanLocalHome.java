
package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

public interface UserBeanLocalHome extends EJBLocalHome {

    UserBeanLocal create();

}
