
package com.netcracker.ejb;

import java.util.List;
import javax.ejb.EJBLocalObject;

/**
 * 
 * @author maks
 */
public interface ReportsBeanLocal extends EJBLocalObject {
    int countOrdersWithWifi (boolean haveWifi);
}
