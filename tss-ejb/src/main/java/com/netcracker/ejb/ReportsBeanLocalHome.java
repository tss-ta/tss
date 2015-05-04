
package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

/**
 * 
 * @author maks
 */
public interface ReportsBeanLocalHome extends EJBLocalHome {

    ReportsBeanLocal create();

}
