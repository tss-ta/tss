package com.netcracker.tss.web.route.admin;

import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.entity.helper.Roles;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.*;
import com.netcracker.util.BeansLocator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author maks
 */

@ActionRoute(menu = "users")
public class Users {

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final Integer MIN_PAGE_NUMBER = 1;
    private static final String MENU_PARAMETER_NAME = "menu";
    private static final String MENU_PARAMETER_VALUE = "users";
    private static final String ACTION_PARAMETER_NAME = "action";

    @Action(action = "view")
    public ActionResponse getUsersView(HttpServletRequest request) {
            Integer page = parsePageNumberFromRequest(request);
//            if(page >= MIN_PAGE_NUMBER) {
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            Roles role = roleToEnum(request.getParameter("role"));
            request.setAttribute("rolesEnum", Roles.values());

            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
            pagerLink.addParameter(ACTION_PARAMETER_NAME, "view");
            pagerLink.addParameter("role", role.toString());

            request.setAttribute(RequestAttribute.PAGER.getName(),
                    userBeanLocal.getPager(page, DEFAULT_PAGE_SIZE, role));
//            System.out.println();
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
            request.setAttribute("users", userBeanLocal.getUsersByRolename(role, page, DEFAULT_PAGE_SIZE));//!!!
            request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CUSTOMERS_CONTENT.getType());
            return new ActionResponse(Page.ADMIN_CUSTOMERS_CONTENT.getAbsolutePath());

    }

    @Action(action = "add-role")
    public ActionResponse redirectToAddRole(HttpServletRequest request) {
        return new ActionResponse(Page.ADMIN_ADD_ROLES_CONTENT.getAbsolutePath());
    }

    @Action(action = "search")
    public ActionResponse searchUsers(HttpServletRequest request) {
            Integer page = parsePageNumberFromRequest(request);
            String email = request.getParameter("email");
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            request.setAttribute("rolesEnum", Roles.values());
            Roles role = roleToEnum(request.getParameter("role"));
            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
            pagerLink.addParameter(ACTION_PARAMETER_NAME, "search");
            pagerLink.addParameter("role", role.toString());
            pagerLink.addParameter("email", email);

            request.setAttribute(RequestAttribute.PAGER.getName(),
                    userBeanLocal.getPager(page, DEFAULT_PAGE_SIZE, role, email));
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
            request.setAttribute("users", userBeanLocal.searchUsersByEmailAndRole(email,
                    role, page, DEFAULT_PAGE_SIZE));//!!!
            request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CUSTOMERS_CONTENT.getType());
            return new ActionResponse(Page.ADMIN_CUSTOMERS_CONTENT.getAbsolutePath());
    }

    @Action(action = "add-roles", httpMethod = HttpMethod.POST)
    public ActionResponse getAddRoles(HttpServletRequest request) {
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            userBeanLocal.editRoles(Integer.parseInt(request.getParameter("id")), getRoles(request));
            return getUsersView(request);
    }

    private Roles roleToEnum(String roleName) {
        try {
            if (roleName == null) {
                return Roles.ADMIN;
            } else {
                return Roles.valueOf(roleName);
            }
        } catch (IllegalArgumentException e) {
            return Roles.ADMIN;
        }
    }

    private boolean isOn(String checkBoxText) {
        return "on".equals(checkBoxText);
    }

    private List<Roles> getRoles(HttpServletRequest req) {
        List<Roles> roles = new ArrayList<>();
        if (isOn(req.getParameter("customer"))) {
            roles.add(Roles.CUSTOMER);
        }
        if (isOn(req.getParameter("admin"))) {
            roles.add(Roles.ADMIN);
        }
        if (isOn(req.getParameter("driver"))) {
            roles.add(Roles.DRIVER);
        }
        if (isOn(req.getParameter("banned"))) {
            roles.add(Roles.BANNED);
        }
        return roles;
    }

    private Integer parsePageNumberFromRequest(HttpServletRequest request) {
        Integer pageNumber = RequestParameterParser.parseInteger(request, RequestParameter.PAGE.getValue());
        if (pageNumber == null || pageNumber < MIN_PAGE_NUMBER) {
            return MIN_PAGE_NUMBER;
        }
        return pageNumber;
    }
}
