package com.netcracker.tss.web.servlet.admin;

import com.netcracker.ejb.ReportsBeanLocal;
import com.netcracker.report.Report;
import com.netcracker.tss.web.util.RequestParameter;
import com.netcracker.tss.web.util.RequestParameterParser;
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
        ReportsBeanLocal reportsBean;
        if (id != null) {
            reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
            Report report = reportsBean.getBigReport(id);
            ExcelExport excelExport = new ExcelExport();
            File file = excelExport.createExcelReport(report);
            sendFile(file, resp);

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
