package com.netcracker.tss.web.route.admin;

import com.netcracker.ejb.GroupBeanLocal;
import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.entity.helper.Roles;
import com.netcracker.exceptions.InvalidEntityException;
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
@ActionRoute(menu = "groups")
public class Groups {

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final Integer MIN_PAGE_NUMBER = 1;
    private static final String MENU_PARAMETER_NAME = "menu";
    private static final String MENU_PARAMETER_VALUE = "groups";
    private static final String ACTION_PARAMETER_NAME = "action";



    @Action(action = "search-users")
    public ActionResponse searchUsers(HttpServletRequest request) {
        Integer page = parsePageNumberFromRequest(request);
        String email = request.getParameter("email");
        UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
        PagerLink pagerLink = new PagerLink();
        pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
        pagerLink.addParameter(ACTION_PARAMETER_NAME, "search-users");
        pagerLink.addParameter("email", email);
        pagerLink.addParameter("groupid", request.getParameter("groupid"));
        pagerLink.addParameter("userid", request.getParameter("userid"));
        pagerLink.addParameter("groupname", request.getParameter("groupname"));

        request.setAttribute(RequestAttribute.PAGER.getName(),
                userBeanLocal.getPager(page, DEFAULT_PAGE_SIZE, email));
        request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
        request.setAttribute("users", userBeanLocal.searchUsersByEmail(email, page, DEFAULT_PAGE_SIZE));
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_GROUPS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_ADD_TO_GROUP_CONTENT.getAbsolutePath());
    }

    @Action(action = "view-users")
    public ActionResponse viewUsers(HttpServletRequest request) {
        Integer page = parsePageNumberFromRequest(request);
        UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
        PagerLink pagerLink = new PagerLink();
        pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
        pagerLink.addParameter(ACTION_PARAMETER_NAME, "view-users");
        pagerLink.addParameter("groupid", request.getParameter("groupid"));
        pagerLink.addParameter("userid", request.getParameter("userid"));
        pagerLink.addParameter("groupname", request.getParameter("groupname"));

        request.setAttribute(RequestAttribute.PAGER.getName(),
                userBeanLocal.getPager(page, DEFAULT_PAGE_SIZE));
        request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
        request.setAttribute("users", userBeanLocal.getUsers(page, DEFAULT_PAGE_SIZE));
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_GROUPS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_ADD_TO_GROUP_CONTENT.getAbsolutePath());
    }

    @Action(action = "edit-group")
    public ActionResponse redirectToEditGroup(HttpServletRequest request){
        request.setAttribute("rolesEnum", Roles.getGroupRoles());
        request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
        return new ActionResponse(Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
    }
    @Action(action = "add-group")
    public ActionResponse redirectToAddGroup(HttpServletRequest request){
        request.setAttribute("rolesEnum", Roles.getGroupRoles());
        request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
        return new ActionResponse(Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
    }

    @Action(action = "view")
    public ActionResponse redirectToGroups(HttpServletRequest request){
        Integer page = parsePageNumberFromRequest(request);
        GroupBeanLocal groupBeanLocal = BeansLocator.getInstance().getBean(GroupBeanLocal.class);

        PagerLink pagerLink = new PagerLink();
        pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
        pagerLink.addParameter(ACTION_PARAMETER_NAME, "view");

        request.setAttribute(RequestAttribute.PAGER.getName(),
                groupBeanLocal.getPager(page, DEFAULT_PAGE_SIZE));
        request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
        request.setAttribute("groups", groupBeanLocal.getGroupPage(page, DEFAULT_PAGE_SIZE));
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_GROUPS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_GROUPS_CONTENT.getAbsolutePath());
    }

    @Action(action = "search")
    public ActionResponse searchGroups(HttpServletRequest request){
        Integer page = parsePageNumberFromRequest(request);
        String groupNamePart =  request.getParameter("groupname");
        GroupBeanLocal groupBeanLocal = BeansLocator.getInstance().getBean(GroupBeanLocal.class);

        PagerLink pagerLink = new PagerLink();
        pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
        pagerLink.addParameter(ACTION_PARAMETER_NAME, "search");
        pagerLink.addParameter("groupname", groupNamePart);

        request.setAttribute(RequestAttribute.PAGER.getName(),
                groupBeanLocal.getPager(page, DEFAULT_PAGE_SIZE, groupNamePart));
        request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
        request.setAttribute("groups", groupBeanLocal.searchGroupByName(groupNamePart, page, DEFAULT_PAGE_SIZE));
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_GROUPS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_GROUPS_CONTENT.getAbsolutePath());
    }


    @Action(action = "newgroup", httpMethod = HttpMethod.POST)
    public ActionResponse addGroup(HttpServletRequest request){
        String groupName = request.getParameter("name");
        try {
            GroupBeanLocal groupBeanLocal = BeansLocator.getInstance().getBean(GroupBeanLocal.class);
            groupBeanLocal.addGroup(groupName, getRoles(request));
            return redirectToGroups(request);
        } catch (InvalidEntityException e) {
            request.setAttribute("rolesEnum", Roles.getGroupRoles());
            request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
            ActionResponse response = new ActionResponse(Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
            response.setErrorMessage(e.getMessage());
            return response;
        }
    }

    @Action(action = "edit-group", httpMethod = HttpMethod.POST)
    public ActionResponse editGroup(HttpServletRequest request){
        String groupName = request.getParameter("name");
        try {
            GroupBeanLocal groupBeanLocal = BeansLocator.getInstance().getBean(GroupBeanLocal.class);
            groupBeanLocal.editGroup(Integer.parseInt(request.getParameter("id")), groupName, getRoles(request));
            return redirectToGroups(request);
        } catch (InvalidEntityException e) {
            request.setAttribute("rolesEnum", Roles.getGroupRoles());
            request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
            ActionResponse response = new ActionResponse(Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
            response.setErrorMessage(e.getMessage());
            return response;
        }
    }

    @Action(action = "delete-group", httpMethod = HttpMethod.POST)
    public ActionResponse deleteGroup(HttpServletRequest request){
        try {
            GroupBeanLocal groupBeanLocal = BeansLocator.getInstance().getBean(GroupBeanLocal.class);
            groupBeanLocal.deleteGroup(Integer.parseInt(request.getParameter("id")));
            return redirectToGroups(request);
        } catch (InvalidEntityException e) {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "Sorry! Can't delete this group");
            return redirectToGroups(request);
        }
    }

    @Action(action = "add-to-group", httpMethod = HttpMethod.POST)
    public ActionResponse addToGroup(HttpServletRequest request){
        UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
        boolean isAdded = userBeanLocal.addToGroup(Integer.parseInt(request.getParameter("userid")),
                Integer.parseInt(request.getParameter("groupid")));
        if (isAdded) {
            request.setAttribute(RequestAttribute.SUCCESS_MESSAGE.getName(),
                    "User was added to group " + request.getParameter("groupname"));
        } else {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(),
                    "User was already added to this group later " + request.getParameter("groupname"));
        }
        return viewUsers(request);
    }

    @Action(action = "remove-from-group", httpMethod = HttpMethod.POST)
    public ActionResponse removeFromGroup(HttpServletRequest request){
        UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
        userBeanLocal.deleteFromGroup(Integer.parseInt(request.getParameter("userid")),
                Integer.parseInt(request.getParameter("groupid")));
        request.setAttribute(RequestAttribute.SUCCESS_MESSAGE.getName(),
                "User was removed from group " + request.getParameter("groupname"));
        return viewUsers(request);
    }


    private boolean isOn(String checkBoxText) {
        return "on".equals(checkBoxText);
    }

    private List<Roles> getRoles(HttpServletRequest req) {
        List<Roles> roles = new ArrayList<>();
        for (Roles role : Roles.getGroupRoles()){
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
