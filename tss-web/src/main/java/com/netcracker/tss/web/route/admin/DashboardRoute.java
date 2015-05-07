package com.netcracker.tss.web.route.admin;

import com.netcracker.ejb.CounterBeanLocal;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.BeansLocator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyrylo Berehovyi
 */

@ActionRoute(menu = "dashboard")
public class DashboardRoute {

    private static final String CUSTOMERS_AMOUNT_ATTRIBUTE = "customers";
    private static final String CARS_AMOUNT_ATTRIBUTE = "cars";
    private static final String DRIVERS_AMOUNT_ATTRIBUTE = "drivers";
    private static final String GROUPS_AMOUNT_ATTRIBUTE = "groups";
    private static final String ORDERS_AMOUNT_ATTRIBUTE = "orders";
    private static final String TARIFFS_AMOUNT_ATTRIBUTE = "tariffs";

    @Action(action = "view")
    public ActionResponse getDashboardView(HttpServletRequest request) {
        CounterBeanLocal local = BeansLocator.getInstance().getBean(CounterBeanLocal.class);
        request.setAttribute(CUSTOMERS_AMOUNT_ATTRIBUTE, local.countAllCustomers());
        request.setAttribute(CARS_AMOUNT_ATTRIBUTE, local.countAllCars());
        request.setAttribute(DRIVERS_AMOUNT_ATTRIBUTE, local.countAllDrivers());
        request.setAttribute(TARIFFS_AMOUNT_ATTRIBUTE, local.countAllTariffs());
        request.setAttribute(GROUPS_AMOUNT_ATTRIBUTE, local.countAllGroups());
        request.setAttribute(ORDERS_AMOUNT_ATTRIBUTE, local.countAllOrders());
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_DASHBOARD_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_DASHBOARD_CONTENT.getAbsolutePath());
    }
}
