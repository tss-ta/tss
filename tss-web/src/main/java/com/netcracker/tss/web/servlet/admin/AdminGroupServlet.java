package com.netcracker.tss.web.servlet.admin;

import com.netcracker.DTO.GroupDTO;
import com.netcracker.ejb.GroupBeanLocal;
import com.netcracker.ejb.GroupBeanLocalHome;
import com.netcracker.entity.helpEntity.Roles;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addgroup".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/add-group.jsp").forward(req, resp);
        }
        redirectToGroups(req, resp);

//        Context context;
//        try {
//            context = new InitialContext();
//            GroupBeanLocalHome groupBeanLocalHome = (GroupBeanLocalHome) context.lookup("java:app/tss-ejb/GroupBean!com.netcracker.ejb.GroupBeanLocalHome");
//            GroupBeanLocal groupBeanLocal = groupBeanLocalHome.create();
//            req.setAttribute("groups", groupBeanLocal.getGroup(1, 5));
//        } catch (NamingException ex) {
//            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
//                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/GroupBean!com.netcracker.ejb.GroupBeanLocal ", ex);
//            //redirect to error page
//        }
//        try {
//            GroupBeanLocal groupBeanLocal = getGroupBean(req);
//            req.setAttribute("groups", groupBeanLocal.getGroup(1, 5));
//        } catch (RuntimeException e) {
//            req.getRequestDispatcher("/500.jsp").forward(req, resp);
//        }
//
//        req.getRequestDispatcher("/WEB-INF/views/admin/groups.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Roles> roles = new ArrayList<>();
        roles.add(Roles.ADMIN);
        roles.add(Roles.DRIVER);

        String groupName = req.getParameter("name");
        String action = req.getParameter("action");
        if ("newgroup".equals(action)) {
            try {
                GroupBeanLocal groupBeanLocal = getGroupBean(req);
                groupBeanLocal.addGroup(new GroupDTO(groupName, roles));
                redirectToGroups(req, resp);
            } 
            catch (RuntimeException e) {
                req.getRequestDispatcher("/WEB-INF/views/admin/add-group.jsp").forward(req, resp);
            }

        }
    }

    private void redirectToGroups(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            GroupBeanLocal groupBeanLocal = getGroupBean(req);
            req.setAttribute("groups", groupBeanLocal.getGroup(1, 5));
            req.getRequestDispatcher("/WEB-INF/views/admin/groups.jsp").forward(req, resp);
        } catch (RuntimeException e) {
            req.getRequestDispatcher("/500.jsp").forward(req, resp);
        }
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
