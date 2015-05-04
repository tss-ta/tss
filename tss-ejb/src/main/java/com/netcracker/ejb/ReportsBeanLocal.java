package com.netcracker.ejb;

import com.netcracker.util.reports.ReportsRow;
import java.util.List;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author maks
 */
public interface ReportsBeanLocal extends EJBLocalObject {

    int countAllOrders();
    
    int countAllOrders(int userId);

    List<ReportsRow> getCarOptionsReport();
    
    List<ReportsRow> getCustomerCarOptionsReport(int userId);
    
}
