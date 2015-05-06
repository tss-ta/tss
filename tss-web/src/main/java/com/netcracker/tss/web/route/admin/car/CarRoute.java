package com.netcracker.tss.web.route.admin.car;

import com.netcracker.ejb.CarBeanLocal;
import com.netcracker.ejb.PageCalculatorBeanLocal;
import com.netcracker.entity.Car;
import com.netcracker.entity.helper.Pager;
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

@ActionRoute(menu = "car")
public class CarRoute {

    private static final Integer DEFAULT_PAGE_SIZE = 15;
    private static final Integer MIN_PAGE_NUMBER = 1;
    private static final String MENU_PARAMETER_NAME = "menu";
    private static final String MENU_PARAMETER_VALUE = "car";
    private static final String ACTION_PARAMETER_NAME = "action";
    private static final String ALL_CARS_ACTION_PARAMETER_VALUE = "all";
    private static final String SEARCH_CARS_ACTION_PARAMETER_VALUE = "search";


    @Action(action = "all")
    public ActionResponse getAllCarsPage(HttpServletRequest request) {
        Integer page = parsePageNumberFromRequest(request);
        if(page >= MIN_PAGE_NUMBER) {
            CarBeanLocal carBeanLocal = BeansLocator.getInstance().getBean(CarBeanLocal.class);
            PageCalculatorBeanLocal pageCalculatorBeanLocal =
                    BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
            List<Car> carList = carBeanLocal.getPageOfCars(page, DEFAULT_PAGE_SIZE);
            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
            pagerLink.addParameter(ACTION_PARAMETER_NAME, ALL_CARS_ACTION_PARAMETER_VALUE);
            request.setAttribute(RequestAttribute.PAGER.getName(),
                    pageCalculatorBeanLocal.createCarPager(page, DEFAULT_PAGE_SIZE));
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
            request.setAttribute(RequestAttribute.CAR_LIST.getName(), carList);
        }
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CARS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_CARS_CONTENT.getAbsolutePath());
    }

    @Action(action = "search")
    public ActionResponse getSearchedCarsPage(HttpServletRequest request) {
        Integer page = parsePageNumberFromRequest(request);
        String searchWord = request.getParameter(RequestParameter.SEARCH_WORD.getValue());
        CarBeanLocal carBean;
        PageCalculatorBeanLocal pageCalculatorBean;
        if(page >= MIN_PAGE_NUMBER && searchWordExistAndNotEmpty(searchWord)) {
            carBean = BeansLocator.getInstance().getBean(CarBeanLocal.class);
            pageCalculatorBean = BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
            List<Car> carList = carBean
                    .getPageOfCarsSearchedByLicPlate(page, DEFAULT_PAGE_SIZE, searchWord.toUpperCase());
            Pager pager = pageCalculatorBean.createSearchCarPager(page, DEFAULT_PAGE_SIZE, searchWord);
            System.out.println(pager);
            request.setAttribute(RequestAttribute.PAGER.getName(), pager);
            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
            pagerLink.addParameter(ACTION_PARAMETER_NAME, SEARCH_CARS_ACTION_PARAMETER_VALUE);
            pagerLink.addParameter(RequestParameter.SEARCH_WORD.getValue(), searchWord);
            System.out.println("pageLINK=" + pagerLink);
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
            request.setAttribute(RequestAttribute.CAR_LIST.getName(), carList);
        }
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CARS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_CARS_CONTENT.getAbsolutePath());
    }

    private Integer parsePageNumberFromRequest(HttpServletRequest request) {
        Integer pageNumber = RequestParameterParser.parseInteger(request, RequestParameter.PAGE.getValue());
        if (pageNumber == null)
            return MIN_PAGE_NUMBER;
        return pageNumber;
    }

    private boolean searchWordExistAndNotEmpty(String word) {
        if (word != null && word.length() != 0) {
            return true;
        }
        return false;
    }
}
