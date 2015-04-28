package com.netcracker.tss.web.servlet.admin;

import com.netcracker.tss.web.util.Page;
import com.netcracker.DTO.GroupDTO;
import com.netcracker.ejb.GroupBeanLocal;
import com.netcracker.ejb.GroupBeanLocalHome;
import com.netcracker.entity.helper.Roles;
import com.netcracker.tss.web.util.RequestAttribute;

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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by Kyrylo Berehovyi on 25/04/2015.
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
            return;
        } else if ("edit-group".equals(action)) {
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        }
        redirectToGroups(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String groupName = req.getParameter("name");
        String action = req.getParameter("action");
        if ("newgroup".equals(action)) {
            try {
                GroupBeanLocal groupBeanLocal = getGroupBean(req);
                groupBeanLocal.addGroup(new GroupDTO(groupName, getRoles(req)));
                redirectToGroups(req, resp);
            } catch (RuntimeException e) {
                req.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "Group with name '" + groupName + "' is alredy exist");
                req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
                req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
            }

        } else if ("edit-group".equals(action)) {
            try {
                GroupBeanLocal groupBeanLocal = getGroupBean(req);
                groupBeanLocal.editGroup(new GroupDTO(Integer.parseInt(req.getParameter("id")), groupName, getRoles(req)));
                redirectToGroups(req, resp);
            } catch (RuntimeException e) {
                req.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "Can't edit this group");
                req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_GROUP_CONTENT.getAbsolutePath());
                req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
            }
        } else if ("delete-group".equals(action)) {
            try {
                GroupBeanLocal groupBeanLocal = getGroupBean(req);
                groupBeanLocal.deleteGroup(Integer.parseInt(req.getParameter("id")));
                redirectToGroups(req, resp);
            } catch (RuntimeException e) {
                Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't delete group", e);
                req.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "Sorry! Can't delete this group");
                redirectToGroups(req, resp);
            }
        }
    }

    private void redirectToGroups(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            GroupBeanLocal groupBeanLocal = getGroupBean(req);
            req.setAttribute("groups", groupBeanLocal.getGroup(1, 10));
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
        } else if (isOn(req.getParameter("customer"))) {
            roles.add(Roles.CUSTOMER);
        } else if (isOn(req.getParameter("driver"))) {
            roles.add(Roles.DRIVER);
        }
        return roles;
    }

    private GroupBeanLocal getGroupBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            GroupBeanLocalHome groupBeanLocalHome = (GroupBeanLocalHome) context.lookup("java:app/tss-ejb/GroupBean!com.netcracker.ejb.GroupBeanLocalHome");
            return groupBeanLocalHome.create();
        } catch (NamingException ex) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/GroupBean!com.netcracker.ejb.GroupBeanLocal ", ex);
            throw new RuntimeException("Internal server error!");// maybe have to create custom exception?
        }
    }
}
