package com.netcracker.ejb;

import com.netcracker.entity.ReportInfo;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.helper.Pager;
import com.netcracker.report.Report;
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

    ReportInfo getReportInfoById(Integer id);

    Report getReportById(Integer id);

    List<ReportInfo> getPageOfReportsInfo(int pageNumber, int pageSize);

    Report getReport(int id, int pageNumber);

    Pager getReportPager(ReportInfo info, int page);

    List<TaxiOrder> getBookedOrders (Date begin, Date end);

    Report getBigReport(int id);

    void createReportInfo(ReportInfo reportInfo);

    void updateReportInfo(ReportInfo reportInfo);

    void deleteReportInfo(ReportInfo reportInfo);
}
