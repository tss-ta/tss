package com.netcracker.ejb;

import com.netcracker.entity.TaxiOrder;
import com.netcracker.util.reports.ReportsRow;
import java.util.Date;
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
    
    int countAllOrders(Date begin, Date end);
    
    List<TaxiOrder> getBookedOrders (Date begin, Date end, int pageNumber, int paginationStep);
    
    List<ReportsRow> getCarCategoryReport();

    List<TaxiOrder> getBookedOrders (Date begin, Date end);
    
}
