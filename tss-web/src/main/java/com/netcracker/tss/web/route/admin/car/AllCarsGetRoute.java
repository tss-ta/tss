package com.netcracker.tss.web.route.admin.car;

import com.netcracker.ejb.CarBeanLocal;
import com.netcracker.ejb.CarBeanLocalHome;
import com.netcracker.ejb.PageCalculatorBeanLocal;
import com.netcracker.entity.Car;
import com.netcracker.entity.helper.Pager;
import com.netcracker.tss.web.router.ActionRequest;
import com.netcracker.tss.web.router.DefaultActionRequest;
import com.netcracker.tss.web.router.Route;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.tss.web.util.RequestParameterParser;
import com.netcracker.util.BeansLocator;
import com.netcracker.util.JndiNameBuilder;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
 */

@Deprecated
public class AllCarsGetRoute implements Route {

    private static final String ROUTE_NAME = "get&menu=cars&action=all";

    private static final String PAGE_REQUEST_PARAMETER = "page";
    private static final Integer DEFAULT_PAGE_NUMBER = 1;

    @Override
    public ActionRequest action(HttpServletRequest request) {
//        Integer page = parsePageNumberFromRequest(request);
//        CarBeanLocal carBeanLocal = BeansLocator.getInstance().getBean(CarBeanLocal.class);
//        PageCalculatorBeanLocal pageCalculatorBeanLocal =
//                BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
//        List<Car> carList = carBeanLocal.getPageOfCars(page);
//        Pager pager = pageCalculatorBeanLocal.createCarPager(page, 15);
//        System.out.println("page=" + page);
//        System.out.println(pager);
//        request.setAttribute(RequestAttribute.PAGER.getName(), pageCalculatorBeanLocal.createCarPager(page, 15));
//        request.setAttribute(RequestAttribute.CAR_LIST.getName(), carList);
//        request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CARS_CONTENT.getAbsolutePath());
//        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CARS_CONTENT.getType());

        return new DefaultActionRequest(Page.ADMIN_TEMPLATE.getAbsolutePath(), false);
    }

    @Override
    public String getRouteName() {
        return ROUTE_NAME;
    }

    private Integer parsePageNumberFromRequest(HttpServletRequest request) {
        Integer pageNumber = RequestParameterParser.parseInteger(request, PAGE_REQUEST_PARAMETER);
        if (pageNumber == null)
            return DEFAULT_PAGE_NUMBER;
        return pageNumber;
    }

//    private CarBeanLocal getCarBean() {
//        Context context;
//        try {
//            context = new InitialContext();
//            CarBeanLocalHome carBeanLocalHome = (CarBeanLocalHome) context.lookup("java:app/tss-ejb/CarBean!com.netcracker.ejb.CarBeanLocalHome");
//            return carBeanLocalHome.create();
//        } catch (NamingException ex) {
//            Logger.getLogger(AllCarsGetRoute.class.getName()).log(Level.SEVERE,
//                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/CarBean!com.netcracker.ejb.CarBeanLocal ", ex);
//            throw new RuntimeException("Internal server error!");
//        }
//    }
}
