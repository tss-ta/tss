package com.netcracker.ejb;

import com.netcracker.dao.ContactsDAO;
import com.netcracker.dao.TaxiOrderDAO;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.util.BeansLocator;
import com.netcracker.util.reports.ReportsRow;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
        
    public int countAllOrders(int userId) {
        TaxiOrderDAO dao = null;
        try {
            UserBeanLocal userBean = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            Contacts userContacts = userBean.getContacts(userId);
            dao = new TaxiOrderDAO();
            return dao.countUserOrders(userContacts);
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }
      
    public int countAllOrders(Date begin, Date end) {
        TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            return dao.countBookedOrdersByPeriod(begin, end);
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

    public List<ReportsRow> getCustomerCarOptionsReport(int userId) {

        TaxiOrderDAO dao = null;
        try {

            UserBeanLocal userBean = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            Contacts userContacts = userBean.getContacts(userId);
            dao = new TaxiOrderDAO();
            List<ReportsRow> report = new ArrayList<>();
            report.add(new ReportsRow("Wi-Fi", dao.countOrdersWithWifi(userContacts)));
            report.add(new ReportsRow("Conditioner", dao.countOrdersWithConditioner(userContacts)));
            report.add(new ReportsRow("Animalable", dao.countOrdersWithAnimalable(userContacts)));
            Collections.sort(report);
            return report;
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }
    
    public List<TaxiOrder> getBookedOrders (Date begin, Date end, int pageNumber, int paginationStep){
         TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            return dao.findBookedOrdersByPeriod(begin, end, pageNumber, pageNumber);
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
