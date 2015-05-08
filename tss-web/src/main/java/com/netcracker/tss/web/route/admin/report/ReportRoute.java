package com.netcracker.tss.web.route.admin.report;

import com.netcracker.dao.ReportDataDAO;
import com.netcracker.ejb.ReportsBeanLocal;
import com.netcracker.entity.ReportInfo;
import com.netcracker.report.Report;
import com.netcracker.report.mapper.DataType;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.tss.web.util.RequestParameter;
import com.netcracker.tss.web.util.RequestParameterParser;
import com.netcracker.util.BeansLocator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyrylo Berehovyi
 */

@ActionRoute(menu = "report")
public class ReportRoute {

    @Action(action = "view")
    public ActionResponse getReport(HttpServletRequest request) {
        Integer id = RequestParameterParser.parseInteger(request, RequestParameter.ID.getValue());
        ReportsBeanLocal reportsBean;
        ActionResponse response = new ActionResponse();
        if (id != null) {
            reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
//            ReportInfo reportInfo = reportsBean.getReportInfoById(id);
            Report report = reportsBean.getReportById(id);
//            request.setAttribute("dataType", DataType.);
            request.setAttribute(RequestAttribute.REPORT.getName(), report);
            response.setPageContent(Page.ADMIN_REPORT_CONTENT.getAbsolutePath());
            request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORT_CONTENT.getType());
        } else {
            response.setPageContent(Page.INCORRECT_ID_CONTENT.getAbsolutePath());
        }
        return response;
    }

    @Action(action = "test")
    public ActionResponse getTest(HttpServletRequest request) {
        ActionResponse response = new ActionResponse();
        ReportDataDAO dao = new ReportDataDAO();
        dao.createReportData("select * from test");
        response.setPageContent(Page.ADMIN_REPORT_INFO_CONTENT.getAbsolutePath());
        return response;
    }
}
