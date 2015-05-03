package com.netcracker.ejb;

import com.netcracker.dao.TaxiOrderDAO;
import com.netcracker.entity.User;
import com.netcracker.util.reports.ReportsRow;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 *
 * @author maks
 */
public class ReportsBean implements SessionBean {

    public int countAllOrders() {
        TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            return dao.count();
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

//    public Map<String, Long> getCarOptionsReport() {
//        Map<String, Long> report = new HashMap<>();
//        ValueMapComparator comparator = new ValueMapComparator(report);
////        Map<String, Long> sortedReport = new LinkedHashMap<>();
//        TaxiOrderDAO dao = null;
//        try {
//            
//            dao = new TaxiOrderDAO();
//            Long wifi = dao.countOrdersWithWifi();
//            Long cond = dao.countOrdersWithConditioner();
//            Long animal = dao.countOrdersWithAnimalable();
////            report.put("WiFi", dao.countOrdersWithWifi());
////            report.put("Conditioner", dao.countOrdersWithConditioner());
////            report.put("Animalable", dao.countOrdersWithAnimalable());
//            report.put("WiFi", wifi);
//            report.put("Conditioner", cond);
//            report.put("Animalable", animal);
//            Map<String, Long> sortedReport = new TreeMap<>(comparator);
//            sortedReport.putAll(report);
//            return  sortedReport;
//        } finally {
//            if (dao != null) {
//                dao.close();
//            }
//        }
//    }
    
  
    
    public List<ReportsRow> getCarOptionsReport() {
        List<ReportsRow> report = new ArrayList<>();
        TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            report.add(new ReportsRow("Wi-Fi", dao.countOrdersWithWifi()));
            report.add(new ReportsRow("Conditioner", dao.countOrdersWithConditioner()));
            report.add(new ReportsRow("Animalable", dao.countOrdersWithAnimalable()));
            Collections.sort(report);
            return report;
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }
        
    public List<ReportsRow> getCustomerCarOptionsReport(User user) {
        List<ReportsRow> report = new ArrayList<>();
        TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            report.add(new ReportsRow("Wi-Fi", dao.countOrdersWithWifi()));
            report.add(new ReportsRow("Conditioner", dao.countOrdersWithConditioner()));
            report.add(new ReportsRow("Animalable", dao.countOrdersWithAnimalable()));
            Collections.sort(report);
            return report;
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }

    @Override
    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {

    }

}
