
package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

public interface TariffBeanLocalHome extends EJBLocalHome {

    TariffBeanLocal create();

}
