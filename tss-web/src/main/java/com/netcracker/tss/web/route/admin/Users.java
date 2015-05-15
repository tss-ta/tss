package com.netcracker.tss.web.route.admin;

import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.entity.helper.Roles;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.*;
import com.netcracker.util.BeansLocator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
        UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
        Roles role = Roles.valueOf(request.getParameter("role"));
        request.setAttribute("rolesEnum", Roles.getUserRoles());

        PagerLink pagerLink = new PagerLink();
        pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
        pagerLink.addParameter(ACTION_PARAMETER_NAME, "view");
        pagerLink.addParameter("role", role.toString());

        request.setAttribute(RequestAttribute.PAGER.getName(),
                userBeanLocal.getPager(page, DEFAULT_PAGE_SIZE, role));
        request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
        request.setAttribute("users", userBeanLocal.getUsersByRolename(role, page, DEFAULT_PAGE_SIZE));//!!!
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_USERS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_USERS_CONTENT.getAbsolutePath());

    }

    @Action(action = "add-role")
    public ActionResponse redirectToAddRole(HttpServletRequest request) {
        request.setAttribute("rolesEnum", Roles.getSubroles(Roles.valueOf(request.getParameter("role"))));
        return new ActionResponse(Page.ADMIN_ADD_ROLES_CONTENT.getAbsolutePath());
    }

    @Action(action = "search")
    public ActionResponse searchUsers(HttpServletRequest request) {
        Integer page = parsePageNumberFromRequest(request);
        String email = request.getParameter("email");
        UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
        request.setAttribute("rolesEnum", Roles.getUserRoles());
        Roles role = Roles.valueOf(request.getParameter("role"));
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
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_USERS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_USERS_CONTENT.getAbsolutePath());
    }

    @Action(action = "add-roles", httpMethod = HttpMethod.POST)
    public ActionResponse getAddRoles(HttpServletRequest request) {
        UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
        Integer id = RequestParameterParser.parseInteger(request, "id");
        if (id == null || id < 0){
            ActionResponse response = getUsersView(request);
            response.setErrorMessage("Sorry, some troubles with id parameter was founded! Please try again later!");
            return  response;
        } else {
            userBeanLocal.editRoles(id, getRoles(request));
            return getUsersView(request);
        }
    }

//    private Roles roleToEnum(String roleName) {
//        try {
//            if (roleName == null) {
//                return Roles.CUSTOMER;
//            } else {
//                return Roles.valueOf(roleName);
//            }
//        } catch (IllegalArgumentException e) {
//            return Roles.CUSTOMER;
//        }
//    }


    private boolean isOn(String checkBoxText) {
        return "on".equals(checkBoxText);
    }

    private List<Roles> getRoles(HttpServletRequest req) {
        List<Roles> roles = new ArrayList<>();
        for (Roles role : Roles.getUserRoles()) {
            if (isOn(req.getParameter(role.toString()))) {
                roles.add(role);
            }
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
