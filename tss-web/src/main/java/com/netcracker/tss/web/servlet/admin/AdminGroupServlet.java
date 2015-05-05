package com.netcracker.tss.web.servlet.admin;

import com.netcracker.tss.web.util.Page;
import com.netcracker.ejb.GroupBeanLocal;
import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.entity.helper.Roles;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.BeansLocator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kyrylo Berehovyi on 25/04/2015.
 *
 * @author maks
 */
@WebServlet(urlPatterns = "/admin/group")
public class AdminGroupServlet extends HttpServlet {

    public static final Page defaultPage = Page.ADMIN_GROUPS_CONTENT;
    public static final Page template = Page.ADMIN_TEMPLATE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), defaultPage.getType());

        String action = req.getParameter("action");
        if ("addgroup".equals(action)) {
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } else if ("edit-group".equals(action)) {
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } else if ("manage-users".equals(action)) {
            redirectToUsers(req, resp);
        } else if ("search".equals(action)) {
            try {
                GroupBeanLocal groupBeanLocal = BeansLocator.getInstance().getBean(GroupBeanLocal.class);
                req.setAttribute("groups", groupBeanLocal.searchGroupByName(req.getParameter("groupname"), 1, 10));

                req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_GROUPS_CONTENT.getAbsolutePath());
                req.getRequestDispatcher(template.getAbsolutePath()).forward(req, resp);
            } catch (RuntimeException e) {
                req.getRequestDispatcher("/500.jsp").forward(req, resp);
            }
        } else if ("search-users".equals(action)) {
            searchUsers(req, resp);
        } else {
            redirectToGroups(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String groupName = req.getParameter("name");
        String action = req.getParameter("action");
        if ("newgroup".equals(action)) {
            try {
                GroupBeanLocal groupBeanLocal = BeansLocator.getInstance().getBean(GroupBeanLocal.class);
                groupBeanLocal.addGroup(groupName, getRoles(req));
                redirectToGroups(req, resp);
            } catch (RuntimeException e) {
                req.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "Group with name '" + groupName + "' is alredy exist");
                req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
                req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
            }

        } else if ("edit-group".equals(action)) {
            try {
                GroupBeanLocal groupBeanLocal = BeansLocator.getInstance().getBean(GroupBeanLocal.class);
                groupBeanLocal.editGroup(Integer.parseInt(req.getParameter("id")), groupName, getRoles(req));
                redirectToGroups(req, resp);
            } catch (RuntimeException e) {
                req.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "Can't edit this group");
                req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
                req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
            }
        } else if ("delete-group".equals(action)) {
            try {
                GroupBeanLocal groupBeanLocal = BeansLocator.getInstance().getBean(GroupBeanLocal.class);
                groupBeanLocal.deleteGroup(Integer.parseInt(req.getParameter("id")));
                redirectToGroups(req, resp);
            } catch (RuntimeException e) {
                Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                        "Can't delete group", e);
                req.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "Sorry! Can't delete this group");
                redirectToGroups(req, resp);
            }
        } else if ("add-to-group".equals(action)) {
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            boolean isAdded = userBeanLocal.addToGroup(Integer.parseInt(req.getParameter("userid")),
                    Integer.parseInt(req.getParameter("groupid")));
            if (isAdded) {
                req.setAttribute(RequestAttribute.SUCCESS_MESSAGE.getName(), "User was added to group " + req.getParameter("groupname"));
            } else {
                req.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "User was already added to this group later " + req.getParameter("groupname"));
            }
            redirectToUsers(req, resp);
        } else if ("remove-from-group".equals(action)) {
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            userBeanLocal.deleteFromGroup(Integer.parseInt(req.getParameter("userid")),
                    Integer.parseInt(req.getParameter("groupid")));
            req.setAttribute(RequestAttribute.SUCCESS_MESSAGE.getName(), "User was removed from group " + req.getParameter("groupname"));
            redirectToUsers(req, resp);
        } else {
            redirectToGroups(req, resp);
        }
    }

    private void redirectToUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            req.setAttribute("customers", userBeanLocal.getUsers(1, 10));
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_GROUPS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_TO_GROUP_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } catch (RuntimeException e) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't show users", e);
            req.getRequestDispatcher("/500.jsp").forward(req, resp);
        }
    }

    private void searchUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserBeanLocal userBeanLocal = BeansLocator.getInstance().getBean(UserBeanLocal.class);
            req.setAttribute("customers", userBeanLocal.searchUsersByEmail(req.getParameter("email"), 1, 10));
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_GROUPS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_TO_GROUP_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } catch (RuntimeException e) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't show users", e);
            req.getRequestDispatcher("/500.jsp").forward(req, resp);
        }
    }

    private void redirectToGroups(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            GroupBeanLocal groupBeanLocal = BeansLocator.getInstance().getBean(GroupBeanLocal.class);
            req.setAttribute("groups", groupBeanLocal.getGroupPage(1, 10));
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_GROUPS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(template.getAbsolutePath()).forward(req, resp);
        } catch (RuntimeException e) {
            req.getRequestDispatcher("/500.jsp").forward(req, resp);
        }
    }

    private boolean isOn(String checkBoxText) {
        return "on".equals(checkBoxText);
    }

    private List<Roles> getRoles(HttpServletRequest req) {
        List<Roles> roles = new ArrayList<>();
        if (isOn(req.getParameter("admin"))) {
            roles.add(Roles.ADMIN);
        }
        if (isOn(req.getParameter("customer"))) {
            roles.add(Roles.CUSTOMER);
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
