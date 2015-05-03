package com.netcracker.ejb;

import com.netcracker.util.reports.ReportsRow;
import java.util.List;
import java.util.Set;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author maks
 */
public interface ReportsBeanLocal extends EJBLocalObject {

    int countAllOrders();

    List<ReportsRow> getCarOptionsReport();
}
