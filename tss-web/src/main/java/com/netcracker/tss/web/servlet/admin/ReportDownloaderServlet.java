package com.netcracker.tss.web.servlet.admin;

import com.netcracker.ejb.ReportsBeanLocal;
import com.netcracker.entity.ReportInfo;
import com.netcracker.entity.helper.ReportFilter;
import com.netcracker.report.Report;
import com.netcracker.tss.web.util.*;
import com.netcracker.util.BeansLocator;
import com.netcracker.util.ExcelExport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Kyrylo Berehovyi
 * @author maks
 */

@WebServlet(urlPatterns = "/admin/report/download")
public class ReportDownloaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = RequestParameterParser.parseInteger(req, RequestParameter.ID.getValue());
        ReportInfo info;
        ReportsBeanLocal reportsBean;
        ReportFilter reportFilter;
        if (id != null) {
            reportFilter = createReportFilterFromRequest(req);
            reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
            info = reportsBean.getReportInfoById(id);

            if (info == null) {
                sendIncorrectIdContent(req, resp);
                return;
            }

            if (info.isFilterable() && reportFilter == null) {
                req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.INCORRECT_FILTER_STATE.getAbsolutePath());
                req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
                return;
            }

            Report report = reportsBean.getBigReport(info, reportFilter);
            ExcelExport excelExport = new ExcelExport();
            File file = excelExport.createExcelReport(report);
            sendFile(file, resp);
        } else {
            sendIncorrectIdContent(req, resp);
        }
    }

    private void sendIncorrectIdContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.INCORRECT_ID_CONTENT.getAbsolutePath());
        request.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(request, response);
    }

    private ReportFilter createReportFilterFromRequest(HttpServletRequest req) {
        try {
            return new ReportFilterParser().parse(req);
        } catch (Exception e) {
            return null;
        }
    }

    private void sendFile(File excelFile, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition","attachment; filename=report.xls");
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(excelFile);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        excelFile.delete();
    }


}
