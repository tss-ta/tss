package com.netcracker.tss.web.route.admin.car;

import com.netcracker.tss.web.router.ActionRequest;
import com.netcracker.tss.web.router.DefaultActionRequest;
import com.netcracker.tss.web.router.Route;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
 */

public class AddCarGetRoute implements Route {

    private static final String ROUTE_NAME = "get&menu=cars&action=add-car";

    @Override
    public ActionRequest action(HttpServletRequest request) {
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_CAR_CONTENT.getType());
        request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_CAR_CONTENT.getAbsolutePath());
        return new DefaultActionRequest(Page.ADMIN_TEMPLATE.getAbsolutePath(), false);
    }

    @Override
    public String getRouteName() {
        return ROUTE_NAME;
    }
}
