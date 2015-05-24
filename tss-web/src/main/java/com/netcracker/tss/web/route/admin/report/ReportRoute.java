package com.netcracker.tss.web.route.admin.report;

import com.netcracker.ejb.PageCalculatorBeanLocal;
import com.netcracker.ejb.ReportsBeanLocal;
import com.netcracker.entity.Criterion;
import com.netcracker.entity.ReportInfo;
import com.netcracker.entity.helper.Pager;
import com.netcracker.entity.helper.ReportFilter;
import com.netcracker.report.Report;
import com.netcracker.report.mapper.DataType;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.*;
import com.netcracker.util.BeansLocator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedList;
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
    public static final String REPORT_SUCCESS_CREATE_MESSAGE = "New report was successfully created.";
    public static final String REPORT_SUCCESS_UPDATE_MESSAGE = "Report was successfully updated.";
    public static final String ALL_REPORTS_URI = "/admin?menu=report&action=all";
    public static final String INCORRECT_FILTER_VALUES_MESSAGE = "Incorrect Filter value(s)";
    public static final String FILTERABLE = "filterable";


    @Action(action = "view")
    public ActionResponse getReport(HttpServletRequest request) {
        Integer id = RequestParameterParser.parseInteger(request, RequestParameter.ID.getValue());
        Integer page = parsePageNumberFromRequest(request);
        ActionResponse response = new ActionResponse();

        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        ReportFilter filter = createReportFilter(request, response);

        if (id == null) {
            return createIncorrectIdResponse(response);
        }

        Report report = reportsBean.getReport(id, page);

        if (report == null) {
            return createIncorrectIdResponse(response);
        }

        request.setAttribute(RequestAttribute.REPORT.getName(), report);

        if(report.getInfo().isCountable()) {
            request.setAttribute(RequestAttribute.PAGER.getName(),
                    reportsBean.getReportPager(report.getInfo(), page));
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), createPagerLinkForViewAction(id));
        }

        response.setPageContent(Page.ADMIN_REPORT_CONTENT.getAbsolutePath());
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORT_CONTENT.getType());
        return response;
    }

    @Action(action = "filter")
    public ActionResponse getFilteredReport(HttpServletRequest request) {
        Integer id = RequestParameterParser.parseInteger(request, RequestParameter.ID.getValue());
        Integer page = parsePageNumberFromRequest(request);
        ActionResponse response = new ActionResponse();
        ReportFilter filter = createReportFilter(request, response);
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);

        if (id == null) {
            return createIncorrectIdResponse(response);
        }

        ReportInfo reportInfo = reportsBean.getReportInfoById(id);
        if (reportInfo == null) {
            return createIncorrectIdResponse(response);
        }

        Report report = reportsBean.getReport(reportInfo, page, filter);
        request.setAttribute(RequestAttribute.REPORT.getName(), report);

        if(report.getInfo().isCountable()) {
            Pager pager = reportsBean.getFilterableReportPager(report.getInfo(), page, filter);
            request.setAttribute(RequestAttribute.PAGER.getName(), pager);
        }

        if (filter != null) {
            request.setAttribute(RequestAttribute.FILTER.getName(), filter.getCriteria());
        }
        response.setPageContent(Page.ADMIN_REPORT_CONTENT.getAbsolutePath());
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_REPORT_CONTENT.getType());
        request.setAttribute(RequestAttribute.FILTER_PAGER.getName(), true);
        return response;
    }


    private ActionResponse createIncorrectIdResponse(ActionResponse response) {
        response.setPageContent(Page.INCORRECT_ID_CONTENT.getAbsolutePath());
        return response;
    }


    @Action(action = "add")
    public ActionResponse viewAddPage(HttpServletRequest request) {
        ActionResponse response = new ActionResponse();
        request.setAttribute(RequestAttribute.DATA_TYPE.getName(), Arrays.asList(DataType.values()));
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_REPORT_CONTENT.getType());
        request.setAttribute(RequestAttribute.FORM_TYPE.getName(), RequestAttribute.FORM_CREATE_TYPE.getName());
        response.setPageContent(Page.ADMIN_ADD_REPORT_CONTENT.getAbsolutePath());
        return response;
    }

    @Action(action = "add", httpMethod = HttpMethod.POST)
    public ActionResponse createReportInfo(HttpServletRequest request) {
        ActionResponse response = new ActionResponse();
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        ReportInfo reportInfo = createReportInfoFromRequest(request);
        ReportFilterParser parser = new ReportFilterParser();

        response.setErrorMessage(reportsBean.validateReportInfo(reportInfo, parser.createDefaultFilter(reportInfo.getFilter())));
        if (response.getErrorMessage() == null) {
            reportsBean.createReportInfo(reportInfo);
            response.setSuccessMessage(REPORT_SUCCESS_CREATE_MESSAGE);
        }
        request.setAttribute(RequestAttribute.DATA_TYPE.getName(), Arrays.asList(DataType.values()));
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_REPORT_CONTENT.getType());
        request.setAttribute(RequestAttribute.FORM_TYPE.getName(), RequestAttribute.FORM_CREATE_TYPE.getName());
        response.setPageContent(Page.ADMIN_ADD_REPORT_CONTENT.getAbsolutePath());
        return response;
    }

    @Action(action = "edit")
    public ActionResponse viewEditPage(HttpServletRequest request) {
        ActionResponse response = new ActionResponse();
        Integer id = RequestParameterParser.parseInteger(request, RequestParameter.ID.getValue());
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        ReportInfo reportInfo;

        if (id == null) {
            return createIncorrectIdResponse(response);
        }

        reportInfo = reportsBean.getReportInfoById(id);

        if (reportInfo == null) {
            return createIncorrectIdResponse(response);
        }

        response.setPageContent(Page.ADMIN_ADD_REPORT_CONTENT.getAbsolutePath());
        request.setAttribute(RequestAttribute.DATA_TYPE.getName(), Arrays.asList(DataType.values()));
        request.setAttribute(RequestAttribute.REPORT_INFO.getName(), reportInfo);
        request.setAttribute(RequestAttribute.FORM_TYPE.getName(), RequestAttribute.FORM_EDIT_TYPE.getName());
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_REPORT_CONTENT.getType());
        return response;
    }

    @Action(action = "edit", httpMethod = HttpMethod.POST)
    public ActionResponse edirReportInfo(HttpServletRequest request) {
        ActionResponse response = new ActionResponse();
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        ReportInfo reportInfo = createReportInfoFromRequest(request);
        ReportFilterParser parser = new ReportFilterParser();

        if (reportInfo.getId() == null) {
            return createIncorrectIdResponse(response);
        }

        response.setErrorMessage(reportsBean.validateReportInfo(reportInfo, parser.createDefaultFilter(reportInfo.getFilter())));
        if (response.getErrorMessage() == null) {
            reportsBean.updateReportInfo(reportInfo);
            response.setSuccessMessage(REPORT_SUCCESS_UPDATE_MESSAGE);
        }

        response.setPageContent(Page.ADMIN_ADD_REPORT_CONTENT.getAbsolutePath());
        request.setAttribute(RequestAttribute.DATA_TYPE.getName(), Arrays.asList(DataType.values()));
        request.setAttribute(RequestAttribute.REPORT_INFO.getName(), reportInfo);
        request.setAttribute(RequestAttribute.FORM_TYPE.getName(), RequestAttribute.FORM_EDIT_TYPE.getName());
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_REPORT_CONTENT.getType());
        return response;
    }

    @Action(action = "delete")
    public ActionResponse deleteReport(HttpServletRequest request) {
        ActionResponse response = new ActionResponse();
        Integer id = RequestParameterParser.parseInteger(request, RequestParameter.ID.getValue());
        ReportsBeanLocal reportsBean = BeansLocator.getInstance().getBean(ReportsBeanLocal.class);
        ReportInfo reportInfo;

        if (id == null) {
            return createIncorrectIdResponse(response);
        }

        reportInfo = reportsBean.getReportInfoById(id);

        if (reportInfo == null) {
            return createIncorrectIdResponse(response);
        }

        reportsBean.deleteReportInfo(reportInfo);
        response.setRedirectURI(ALL_REPORTS_URI);
        return response;
    }

    private ReportInfo createReportInfoFromRequest(HttpServletRequest request) {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setId(RequestParameterParser.parseInteger(request, RequestParameter.ID.getValue()));
        reportInfo.setName(request.getParameter(RequestParameter.REPORT_NAME.getValue()));
        reportInfo.setDescription(request.getParameter(RequestParameter.REPORT_DESCRIPTION.getValue()));
        reportInfo.setSelectQuery(request.getParameter(RequestParameter.REPORT_SELECT_QUERY.getValue()));
        reportInfo.setCountable(RequestParameterParser.parseBoolean(request,
                RequestParameter.REPORT_COUNTABLE.getValue()));
        if (reportInfo.isCountable()) {
            reportInfo.setCountQuery(request.getParameter(RequestParameter.REPORT_COUNT_QUERY.getValue()));
            reportInfo.setExportSize(RequestParameterParser.parseInteger(request,
                    RequestParameter.REPORT_EXPORT_SIZE.getValue()));
            reportInfo.setPageSize(RequestParameterParser.parseInteger(request,
                    RequestParameter.REPORT_PAGE_SIZE.getValue()));
        }

        reportInfo.setFilterable(RequestParameterParser.parseBoolean(request, RequestParameter.REPORT_FILTERABLE.getValue()));

        if (reportInfo.isFilterable()) {
            reportInfo.setFilter(createCriterionListFromRequest(request, reportInfo));
        }
        return reportInfo;
    }

    public ReportFilter createReportFilter(HttpServletRequest request, ActionResponse response) {
        ReportFilter filter = null;

        try {
            filter = new ReportFilterParser().parse(request);
        } catch (Exception e) {
            response.setErrorMessage(INCORRECT_FILTER_VALUES_MESSAGE);
            e.printStackTrace();
        }
        return filter;
    }

    private List<Criterion> createCriterionListFromRequest(HttpServletRequest request, ReportInfo reportInfo) {
        List<Criterion> criterionList;
        Integer criterionAmount = RequestParameterParser.parseInteger(request,
                RequestParameter.REPORT_FILTER_CRITERIA_AMOUNT.getValue());
        if (criterionAmount == null) {
            return null;
        }

        criterionList = new LinkedList<>();

        for (int criterionIndex = 1; criterionIndex <= criterionAmount; criterionIndex++) {
                criterionList.add(createCriterionFromRequest(request, criterionIndex, reportInfo));
        }

        return criterionList;
    }

    private Criterion createCriterionFromRequest(HttpServletRequest request, int index, ReportInfo reportInfo) {
        Integer id = RequestParameterParser.parseInteger(request,
                RequestParameter.REPORT_FILTER_CRITERION_ID_PREFIX.getValue() + index);
        String name = request.getParameter(RequestParameter.REPORT_FILTER_CRITERION_NAME_PREFIX.getValue() + index);
        Integer type = RequestParameterParser.parseInteger(request, RequestParameter.REPORT_FILTER_CRITERION_TYPE_PREFIX.getValue() + index);
        Integer seqNum = RequestParameterParser.parseInteger(request, RequestParameter.REPORT_FILTER_SEQUENTIAL_NUMBER_PREFIX.getValue() + index);

        return new Criterion(id, name, type, reportInfo, seqNum);

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
