
package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

public interface GroupBeanLocalHome extends EJBLocalHome {

    GroupBeanLocal create();

}
