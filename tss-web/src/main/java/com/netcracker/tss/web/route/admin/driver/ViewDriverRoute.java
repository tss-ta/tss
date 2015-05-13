package com.netcracker.tss.web.route.admin.driver;

import com.netcracker.ejb.DriverLocal;
import com.netcracker.ejb.PageCalculatorBeanLocal;
import com.netcracker.entity.Driver;
import com.netcracker.entity.helper.Pager;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.*;
import com.netcracker.util.BeansLocator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Illia Rudenko
 */

@ActionRoute(menu = "driver")
public class ViewDriverRoute {

    private static final Integer MIN_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 15;
    public static final String MENU_PARAMETER_NAME = "menu";
    public static final String MENU_PARAMETER_VALUE = "driver";
    public static final String ACTION_PARAMETER_NAME = "action";
    public static final String ALL_DRIVERS_ACTION_PARAMETER_VALUE = "all";
    private static final String SEARCH_DRIVERS_ACTION_PARAMETER_VALUE = "search";

    @Action(action = "all")
    public ActionResponse getAllDriversPage(HttpServletRequest req) {
        Integer page = parsePageNumberFromRequest(req);

        if(page >= MIN_PAGE_NUMBER) {
            DriverLocal driverbean = BeansLocator.getInstance().getDriverBean();
            PageCalculatorBeanLocal pageCalculatorBeanLocal =
                    BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);

            List<Driver> drivers = driverbean.getDriverPage(page, DEFAULT_PAGE_SIZE);
            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
            pagerLink.addParameter(ACTION_PARAMETER_NAME, ALL_DRIVERS_ACTION_PARAMETER_VALUE);

            req.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
            req.setAttribute(RequestAttribute.PAGER.getName(),
                    pageCalculatorBeanLocal.createPager(Driver.class, page, DEFAULT_PAGE_SIZE));
            req.setAttribute(RequestAttribute.DRIVER_LIST.getName(), drivers);
        }

        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_DRIVERS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_DRIVERS_CONTENT.getAbsolutePath());
    }

    @Action(action = "search")
    public ActionResponse getSearchedDriversPage(HttpServletRequest request) {
        Integer page = parsePageNumberFromRequest(request);
        String searchWord = request.getParameter(RequestParameter.SEARCH_WORD.getValue());
        DriverLocal driverBean;
        PageCalculatorBeanLocal pageCalculatorBean;
        if(page >= MIN_PAGE_NUMBER && searchWordExistAndNotEmpty(searchWord)) {
            searchWord = searchWord.toUpperCase();
            driverBean = BeansLocator.getInstance().getDriverBean();
            pageCalculatorBean = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
            List<Driver> drivers = driverBean
                    .searchDriversByName(searchWord, page, DEFAULT_PAGE_SIZE);
            Pager pager = pageCalculatorBean.createSearchCarPager(page, DEFAULT_PAGE_SIZE, searchWord);
            request.setAttribute(RequestAttribute.PAGER.getName(), pager);
            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
            pagerLink.addParameter(ACTION_PARAMETER_NAME, SEARCH_DRIVERS_ACTION_PARAMETER_VALUE);
            pagerLink.addParameter(RequestParameter.SEARCH_WORD.getValue(), searchWord);

            request.setAttribute(RequestAttribute.PAGER.getName(),
                    pageCalculatorBean.createCarPager(page, DEFAULT_PAGE_SIZE));
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
            request.setAttribute(RequestAttribute.DRIVER_LIST.getName(), drivers);
        }
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_DRIVERS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_DRIVERS_CONTENT.getAbsolutePath());
    }

    private boolean searchWordExistAndNotEmpty(String word) {
        if (word != null && word.length() != 0) {
            return true;
        }
        return false;
    }

    private Integer parsePageNumberFromRequest(HttpServletRequest request) {
        Integer pageNumber = RequestParameterParser.parseInteger(request, RequestParameter.PAGE.getValue());
        if (pageNumber == null)
            return MIN_PAGE_NUMBER;
        return pageNumber;
    }
}
