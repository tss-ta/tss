package com.netcracker.tss.web.route.admin;

import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.entity.helper.Roles;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.BeansLocator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kyrylo Berehovyi
 */

@ActionRoute(menu = "users")
public class Users {


    @Action(action = "view")
    public ActionResponse getUsersView(HttpServletRequest request) {
        try {
            String roleName = request.getParameter("role");
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            request.setAttribute("users", userBeanLocal.getUsersByRolename(roleToEnum(roleName), 1, 10));//!!!
            request.setAttribute("rolesEnum", Roles.values());
            request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CUSTOMERS_CONTENT.getType());
            return new ActionResponse(Page.ADMIN_CUSTOMERS_CONTENT.getAbsolutePath());
        } catch (RuntimeException e) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't show customers", e);
            return new ActionResponse(Page.ERROR_500_CONTENT.getAbsolutePath());
        }
    }

    @Action(action = "add-role")
    public ActionResponse redirectToAddRole(HttpServletRequest request) {
            return new ActionResponse(Page.ADMIN_ADD_ROLES_CONTENT.getAbsolutePath());
    }

    @Action(action = "search")
    public ActionResponse searchUsers(HttpServletRequest request) {
        try {
            String roleName = request.getParameter("role");
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            request.setAttribute("rolesEnum", Roles.values());
            request.setAttribute("users", userBeanLocal.searchUsersByEmailAndRole(request.getParameter("email"),
                    roleToEnum(roleName), 1, 10));//!!!!
            request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CUSTOMERS_CONTENT.getType());
            return new ActionResponse(Page.ADMIN_CUSTOMERS_CONTENT.getAbsolutePath());
        } catch (RuntimeException e) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't show customers", e);
            return new ActionResponse(Page.ERROR_500_CONTENT.getAbsolutePath());
        }
    }

    @Action(action = "add-roles", httpMethod = HttpMethod.POST)
    public ActionResponse getAddRoles(HttpServletRequest request) {
        try {
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            userBeanLocal.editRoles(Integer.parseInt(request.getParameter("id")), getRoles(request));
            return getUsersView(request);
        } catch (RuntimeException e) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't show customers", e);
            return new ActionResponse(Page.ERROR_500_CONTENT.getAbsolutePath());
        }
    }

    private Roles roleToEnum (String roleName){
        try {
            if (roleName == null){
                return Roles.ADMIN;
            } else {
                return Roles.valueOf(roleName);
            }
        } catch (IllegalArgumentException e){
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
}
