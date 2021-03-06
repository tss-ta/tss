package com.netcracker.ejb;

import com.netcracker.dao.ReportDataDAO;
import com.netcracker.dao.ReportInfoDAO;
import com.netcracker.dao.TaxiOrderDAO;
import com.netcracker.dao.exceptions.NoSuchEntityException;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.Criterion;
import com.netcracker.entity.ReportInfo;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.helper.CarCategory;
import com.netcracker.entity.helper.Pager;
import com.netcracker.entity.helper.ReportFilter;
import com.netcracker.report.Report;
import com.netcracker.report.container.ReportData;
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
 * @author maks
 * @author Kyrylo Berehovyi
 */

public class ReportsBean implements SessionBean {

    public static final int ONE_PAGE = 1;

    public ReportInfo getReportInfoById(Integer id) {
        ReportInfoDAO dao = null;
        try {
            dao = new ReportInfoDAO();
            return dao.get(id);
        } catch (NoSuchEntityException e) {
			e.printStackTrace();
		} finally {
            if (dao != null) {
                dao.close();
            }
        }
		return null;
    }

    public Report getReportById(Integer id) {
        ReportInfoDAO infoDAO = null;
        ReportDataDAO dataDAO;
        ReportInfo reportInfo;
        ReportData reportData;
        Report report = null;
        try {
            infoDAO = new ReportInfoDAO();
            dataDAO = new ReportDataDAO();
            reportInfo = infoDAO.get(id);
            reportData = dataDAO.createReportData(reportInfo.getSelectQuery());
            report = new Report(reportInfo, reportData);
        } catch (NoSuchEntityException e) {
			e.printStackTrace();
		} finally {
            if (infoDAO != null) {
                infoDAO.close();
            }
        }
        return report;
    }

    public void deleteReportInfo(ReportInfo reportInfo) {
        ReportInfoDAO infoDAO = null;
        try {
            infoDAO = new ReportInfoDAO();
            infoDAO.delete(reportInfo);
        } finally {
            if (infoDAO != null) {
                infoDAO.close();
            }
        }
    }

    public int countReports(ReportInfo info) {
        ReportDataDAO dataDAO = new ReportDataDAO();
        Long counter = dataDAO.countResults(info.getCountQuery());
        return counter.intValue();
    }

    public int countFilterableReports(ReportInfo info, ReportFilter filter) {
        ReportDataDAO dataDAO = new ReportDataDAO();
        Long counter = dataDAO.countResults(info.getCountQuery(), filter);
        return counter.intValue();
    }

    public Pager getReportPager(ReportInfo info, int page) {
        PageCalculatorBeanLocal pageCalculator = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        return pageCalculator.calculatePages(page, info.getPageSize(), countReports(info));
    }
    public Pager getOrdersReportPager(int page, int pageSize, Date begin, Date end) {
        PageCalculatorBeanLocal pageCalculator = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        return pageCalculator.calculatePages(page, pageSize, countAllOrders(begin, end));
    }

    public Pager getFilterableReportPager(ReportInfo info, int page, ReportFilter filter) {
        PageCalculatorBeanLocal pageCalculator = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        return pageCalculator.calculatePages(page, info.getPageSize(), countFilterableReports(info, filter));
    }

    public int countAllReportsInfo() {
        ReportInfoDAO infoDAO = null;
        int counter;
        try {
            infoDAO = new ReportInfoDAO();
            counter = infoDAO.count();
        } finally {
            if (infoDAO != null) {
                infoDAO.close();
            }
        }
        return counter;
    }

    public void createReportInfo(ReportInfo reportInfo) {
        ReportInfoDAO infoDAO = null;
        try {
            infoDAO = new ReportInfoDAO();
            infoDAO.persist(reportInfo);
        } finally {
            if (infoDAO != null) {
                infoDAO.close();
            }
        }
    }

    public String validateReportInfo(ReportInfo reportInfo, ReportFilter filter) {
        return new ReportDataDAO().validateQuery(reportInfo, filter);
    }

    public void updateReportInfo(ReportInfo reportInfo) {
        ReportInfoDAO infoDAO = null;
        ReportInfo oldReportInfo = null;
        try {
            infoDAO = new ReportInfoDAO();
            oldReportInfo = infoDAO.get(reportInfo.getId());
            updateReportFilter(oldReportInfo.getFilter(), reportInfo.getFilter());
            infoDAO.update(reportInfo);
        } catch(NoSuchEntityException e) {
            e.printStackTrace();
        } finally {
            if (infoDAO != null) {
                infoDAO.close();
            }
        }
    }

    private void updateReportFilter(List<Criterion> oldFilter, List<Criterion> newFilter) {
        CriterionBeanLocal criterionBean = BeansLocator.getInstance().getBean(CriterionBeanLocal.class);

        if (oldFilter.size() == newFilter.size()) {

            System.out.println("---- '==' -----");

            for (int index = 0; index < newFilter.size(); index++) {
                newFilter.get(index).setId(oldFilter.get(index).getId());
            }
            criterionBean.updateCriterionList(newFilter);
        } else if (oldFilter.size() > newFilter.size()) {

            System.out.println("---- '=' -----");


            for (int index = 0; index < newFilter.size(); index++) {
                newFilter.get(index).setId(oldFilter.get(index).getId());
            }
            criterionBean.updateCriterionList(newFilter);
            criterionBean.removeCriterionList(oldFilter.subList(newFilter.size(), oldFilter.size()));
        } else if (oldFilter.size() < newFilter.size()) {

            System.out.println("---- '<' -----");

            for (int index = 0; index < oldFilter.size(); index++) {
                newFilter.get(index).setId(oldFilter.get(index).getId());
            }
            criterionBean.updateCriterionList(newFilter.subList(0, oldFilter.size()));
            criterionBean.insertCriterionList(newFilter.subList(oldFilter.size(), newFilter.size()));
        }
    }

    public List<ReportInfo> getPageOfReportsInfo(int pageNumber, int pageSize) {
        ReportInfoDAO infoDAO = null;
        List<ReportInfo> reportInfoList;
        try {
            infoDAO = new ReportInfoDAO();
            reportInfoList = infoDAO.getPage(pageNumber, pageSize);
        } finally {
            if (infoDAO != null) {
                infoDAO.close();
            }
        }
        return reportInfoList;
    }

    public Report getReport(int id, int pageNumber) {
        ReportDataDAO dataDAO = new ReportDataDAO();
        ReportInfoDAO infoDAO = null;
        ReportInfo reportInfo;
        ReportData reportData;
        Report report = null;
        try {
            infoDAO = new ReportInfoDAO();
            reportInfo = infoDAO.get(id);
            if (reportInfo.isCountable()) {
                reportData = dataDAO.createReportData(reportInfo.getSelectQuery(), pageNumber, reportInfo.getPageSize());
            } else {
                reportData = dataDAO.createReportData(reportInfo.getSelectQuery());
            }
            report = new Report(reportInfo, reportData);
        }  catch (NoSuchEntityException e) {
//            e.printStackTrace();
        } finally {
            if (infoDAO != null) {
                infoDAO.close();
            }
        }
        return report;
    }

    public Report getBigReport(ReportInfo info) {
        ReportDataDAO dataDAO = new ReportDataDAO();
        ReportData reportData;
        Report report;
        if (info.isCountable()) {
            reportData = dataDAO.createReportData(info.getSelectQuery(), ONE_PAGE, info.getExportSize());
        } else {
            reportData = dataDAO.createReportData(info.getSelectQuery());
        }
        return new Report(info, reportData);
    }

    public Report getReport(ReportInfo reportInfo, int pageNumber, ReportFilter filter) {

        if (filter == null)
            return new Report(reportInfo, null);

        ReportDataDAO dataDAO = new ReportDataDAO();
        ReportData reportData = null;
        Report report = null;

        if (reportInfo.isCountable() && !reportInfo.isFilterable()) {
            reportData = dataDAO.createReportData(reportInfo.getSelectQuery(), pageNumber, reportInfo.getPageSize());
        } else if (!reportInfo.isCountable() && !reportInfo.isFilterable()) {
            reportData = dataDAO.createReportData(reportInfo.getSelectQuery());
        } else if (reportInfo.isCountable() && reportInfo.isFilterable()) {
            reportData = dataDAO.
                    createReportData(reportInfo.getSelectQuery(), pageNumber, reportInfo.getPageSize(), filter);
        } else if (!reportInfo.isCountable() && reportInfo.isFilterable()) {
            reportData = dataDAO.createReportData(reportInfo.getSelectQuery(), filter);
        }

        report = new Report(reportInfo, reportData);

        return report;
    }

    public Report getBigReport(ReportInfo reportInfo, ReportFilter filter) {

        if (filter == null)
            return getBigReport(reportInfo);

        ReportDataDAO dataDAO = new ReportDataDAO();
        ReportData reportData = null;
        Report report = null;

        if (reportInfo.isCountable() && !reportInfo.isFilterable()) {
            reportData = dataDAO.createReportData(reportInfo.getSelectQuery(), ONE_PAGE, reportInfo.getExportSize());
        } else if (!reportInfo.isCountable() && !reportInfo.isFilterable()) {
            reportData = dataDAO.createReportData(reportInfo.getSelectQuery());
        } else if (reportInfo.isCountable() && reportInfo.isFilterable()) {
            reportData = dataDAO.
                    createReportData(reportInfo.getSelectQuery(), ONE_PAGE, reportInfo.getExportSize(), filter);
        } else if (!reportInfo.isCountable() && reportInfo.isFilterable()) {
            reportData = dataDAO.createReportData(reportInfo.getSelectQuery(), filter);
        }
        report = new Report(reportInfo, reportData);

        return report;
    }


    public Report getBigReport(int id, ReportFilter filter) {
        ReportDataDAO dataDAO = new ReportDataDAO();
        ReportInfoDAO infoDAO = null;
        ReportInfo reportInfo;
        ReportData reportData;
        Report report = null;
        try {
            infoDAO = new ReportInfoDAO();
            reportInfo = infoDAO.get(id);
            if (reportInfo.isFilterable()) {
                report = getReport(reportInfo, ONE_PAGE, filter);
            } else {
                if (reportInfo.isCountable()) {
                    reportData = dataDAO.createReportData(reportInfo.getSelectQuery(), ONE_PAGE, reportInfo.getExportSize());
                } else {
                    reportData = dataDAO.createReportData(reportInfo.getSelectQuery());
                }
                report = new Report(reportInfo, reportData);
            }
        } catch (NoSuchEntityException e) {
			e.printStackTrace();
		} finally {
            if (infoDAO != null) {
                infoDAO.close();
            }
        }
        return report;
    }

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

    public List<TaxiOrder> getBookedOrders (Date begin, Date end){
        TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            return dao.findBookedOrdersByPeriod(begin, end);
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
            return dao.findBookedOrdersByPeriod(begin, end, pageNumber, paginationStep);
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }
    
        
    public List<ReportsRow> getCarCategoryReport() {
        List<ReportsRow> report = new ArrayList<>();
        TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            for (CarCategory category : CarCategory.values()){
                report.add(new ReportsRow(category.toString(), dao.countOrdersWithCarCategory(category)));
            }
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
