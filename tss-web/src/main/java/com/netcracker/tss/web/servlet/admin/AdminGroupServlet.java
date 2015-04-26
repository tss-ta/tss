package com.netcracker.tss.web.servlet.admin;

import com.netcracker.DTO.GroupDTO;
import com.netcracker.ejb.GroupBeanLocal;
import com.netcracker.ejb.GroupBeanLocalHome;
import com.netcracker.entity.Role;
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
        

        Context context;
        try {
            context = new InitialContext();
            GroupBeanLocalHome groupBeanLocalHome = (GroupBeanLocalHome) context.lookup("java:app/tss-ejb/GroupBean!com.netcracker.ejb.GroupBeanLocalHome");
            GroupBeanLocal groupBeanLocal = groupBeanLocalHome.create();    
            req.setAttribute("groups", groupBeanLocal.getGroup(1, 5));
        } catch (NamingException ex) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE, 
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/GroupBean!com.netcracker.ejb.GroupBeanLocal ", ex);
            //redirect to error page
        }


        req.getRequestDispatcher("/WEB-INF/views/admin/groups.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    
    
}
