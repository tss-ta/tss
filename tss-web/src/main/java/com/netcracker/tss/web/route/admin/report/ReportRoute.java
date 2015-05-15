package com.netcracker.tss.web.route.admin.report;

import com.netcracker.dao.ReportDataDAO;
import com.netcracker.ejb.PageCalculatorBeanLocal;
import com.netcracker.ejb.ReportsBeanLocal;
import com.netcracker.entity.ReportInfo;
import com.netcracker.report.Report;
import com.netcracker.router.ContentType;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.*;
import com.netcracker.util.BeansLocator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Kyrylo Berehovyi
 */

@ActionRoute(menu = "report")
public class ReportRoute {

    private static final Integer MIN_PAGE_NUMBER = 1;
    private static final String MENU_PARAMETER_NAME = "menu";
    private static final String MENU_PARAMETER_VALUE = "report";
    private static final String ACTION_PARAMETER_NAME = "action";
    private static final String ALL_REPORTS_ACTION_PARAMETER_VALUE = "all";
    private static final String VIEW_REPORT_ACTION_PARAMETER_VALUE = "view";
    private static final int DEFAULT_PAGE_SIZE = 15;

    @Action(action = "view")
    public ActionResponse getReport(HttpServletRequest request) {
        Integer id = RequestParameterParser.parseInteger(request, RequestParameter.ID.getValue());
        Integer page = parsePageNumberFromRequest(request);
        ActionResponse response = new ActionResponse();
        ReportsBeanLocal reportsBean;
        if (id != null) {
            reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
            Report report = reportsBean.getReport(id, page);
            request.setAttribute(RequestAttribute.REPORT.getName(), report);
            if(report.getInfo().isCountable()) {
                request.setAttribute(RequestAttribute.PAGER.getName(),
                        reportsBean.getReportPager(report.getInfo(), page));
                request.setAttribute(RequestAttribute.PAGER_LINK.getName(), createPagerLinkForViewAction(id));
            }
            response.setPageContent(Page.ADMIN_REPORT_CONTENT.getAbsolutePath());
            request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORT_CONTENT.getType());
        } else {
            response.setPageContent(Page.INCORRECT_ID_CONTENT.getAbsolutePath());
        }
        return response;
    }

    @Action(action = "json", responseContentType = ContentType.JSON)
    public ActionResponse getReportJson(HttpServletRequest request) {
        Integer id = RequestParameterParser.parseInteger(request, RequestParameter.ID.getValue());
        Integer page = parsePageNumberFromRequest(request);
        ActionResponse response = new ActionResponse();
        ReportsBeanLocal reportsBean;
        if (id != null) {
            reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
            Report report = reportsBean.getReport(id, page);
            response.setModel(report);

        } else {
            response.setPageContent(Page.INCORRECT_ID_CONTENT.getAbsolutePath());
        }
        return response;
    }

    @Action(action = "add")
    public ActionResponse viewAddPage(HttpServletRequest request) {
        ActionResponse response = new ActionResponse();
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_REPORT_CONTENT.getType());
        response.setPageContent(Page.ADMIN_ADD_REPORT_CONTENT.getAbsolutePath());
        return response;
    }

    @Action(action = "add", httpMethod = HttpMethod.POST)
    public ActionResponse createReport(HttpServletRequest request) {
        ActionResponse response = new ActionResponse();
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        ReportInfo reportInfo = createReportInfoFromRequest(request);
        reportsBean.createReportInfo(reportInfo);
        response.setSuccessMessage("Report was successfully created.");
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_REPORT_CONTENT.getType());
        response.setPageContent(Page.ADMIN_ADD_REPORT_CONTENT.getAbsolutePath());
        return response;
    }

    private ReportInfo createReportInfoFromRequest(HttpServletRequest request) {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setName(request.getParameter("name"));
        reportInfo.setDescription(request.getParameter("description"));
        reportInfo.setSelectQuery(request.getParameter("selectQuery"));
        reportInfo.setExportSize(RequestParameterParser.parseInteger(request, "exportSize"));
        reportInfo.setCountable(RequestParameterParser.parseBoolean(request, "countable"));
        if (reportInfo.isCountable()) {
            reportInfo.setCountQuery(request.getParameter("countQuery"));
            reportInfo.setPageSize(RequestParameterParser.parseInteger(request, "pageSize"));
        }
        return reportInfo;
    }

    @Action(action = "all")
    public ActionResponse getPageOfReports(HttpServletRequest request) {
        Integer page = parsePageNumberFromRequest(request);
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        PageCalculatorBeanLocal pageCalculatorBean = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
        if(page >= MIN_PAGE_NUMBER) {
            List<ReportInfo> reportInfoList = reportsBean.getPageOfReportsInfo(page, DEFAULT_PAGE_SIZE);
            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
            pagerLink.addParameter(ACTION_PARAMETER_NAME, ALL_REPORTS_ACTION_PARAMETER_VALUE);
            request.setAttribute(RequestAttribute.PAGER.getName(),
                    pageCalculatorBean.createPager(ReportInfo.class, page, DEFAULT_PAGE_SIZE));
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
            request.setAttribute(RequestAttribute.REPORT_LIST.getName(), reportInfoList);
        }
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORTS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_REPORTS_CONTENT.getAbsolutePath());
    }

    private Integer parsePageNumberFromRequest(HttpServletRequest request) {
        Integer pageNumber = RequestParameterParser.parseInteger(request, RequestParameter.PAGE.getValue());
        if (pageNumber == null)
            return MIN_PAGE_NUMBER;
        return pageNumber;
    }

    private PagerLink createPagerLinkForViewAction(Integer id) {
        PagerLink pagerLink = new PagerLink();
        pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
        pagerLink.addParameter(ACTION_PARAMETER_NAME, VIEW_REPORT_ACTION_PARAMETER_VALUE);
        pagerLink.addParameter(RequestParameter.ID.getValue(), id.toString());
        return pagerLink;
    }
}
