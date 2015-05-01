package com.netcracker.tss.web.route.admin.car;

import com.netcracker.ejb.CarBeanLocal;
import com.netcracker.ejb.CarBeanLocalHome;
import com.netcracker.entity.Car;
import com.netcracker.tss.web.router.ActionRequest;
import com.netcracker.tss.web.router.DefaultActionRequest;
import com.netcracker.tss.web.router.Route;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.tss.web.util.RequestParameterParser;
import com.netcracker.util.BeansLocator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by happy on 30/04/2015.
 */

public class SearchCarsByLicensePostRoute implements Route {

    private static final String ROUTE_NAME = "post&menu=cars&action=search";

    private static final String SEARCH_WORD_REQUEST_PARAMETER = "search";
    private static final int LICENSE_LENGTH = 11;

    @Override
    public ActionRequest action(HttpServletRequest request) {
        String searchWord = request.getParameter(SEARCH_WORD_REQUEST_PARAMETER);
        System.out.println("search: " + searchWord);
        List<Car> carList = BeansLocator.getInstance().getCarBean().searchByLicPlate(searchWord.toUpperCase());
        System.out.println("Car list size: " + carList.size());
        request.setAttribute(RequestAttribute.CAR_LIST.getName(), carList);
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CARS_CONTENT.getType());
        request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CARS_CONTENT.getAbsolutePath());
        return new DefaultActionRequest(Page.ADMIN_TEMPLATE.getAbsolutePath(), false);
    }

    @Override
    public String getRouteName() {
        return ROUTE_NAME;
    }

//    private CarBeanLocal getCarBean() {
//        Context context;
//        try {
//            context = new InitialContext();
//            CarBeanLocalHome carBeanLocalHome = (CarBeanLocalHome) context.lookup("java:app/tss-ejb/CarBean!com.netcracker.ejb.CarBeanLocalHome");
//            return carBeanLocalHome.create();
//        } catch (NamingException ex) {
//            Logger.getLogger(SearchCarsByLicensePostRoute.class.getName()).log(Level.SEVERE,
//                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/CarBean!com.netcracker.ejb.CarBeanLocal ", ex);
//            throw new RuntimeException("Internal server error!");
//        }
//    }

}
