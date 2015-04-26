/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.servlet;

import com.netcracker.ejb.RegistrationBeanLocal;
import com.netcracker.ejb.RegistrationBeanLocalHome;
import com.netcracker.entity.User;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Виктор
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirPassword");

        if (password.equals(confirmPassword)) {
            RegistrationBeanLocal rb = getRegistrationBean(request);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            password = encoder.encode(password);
            User user = new User(userName, email, password);
            if (!rb.isUserExist(user)) {
                rb.registrate(user);
                //       request.getRequestDispatcher("/customer").forward(request, response);
                response.sendRedirect("/customer");
            } else {
                response.sendRedirect("/signup.jsp");
            }
        } else {
            response.sendRedirect("/signup.jsp");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "registration";
    }// </editor-fold>

    private RegistrationBeanLocal getRegistrationBean(HttpServletRequest req) {
        Context context;
        try {
            context = new InitialContext();
            RegistrationBeanLocalHome regBeanLocalHome = (RegistrationBeanLocalHome) context.lookup("java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome");
            return regBeanLocalHome.create();
        } catch (NamingException ex) {
            Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome", ex);
            throw new RuntimeException("Internal server error!" + 
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome");// maybe have to create custom exception?
        } catch (ClassCastException ex){
                        Logger.getLogger(AdminGroupServlet.class.getName()).log(Level.SEVERE,
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome", ex);
            throw new RuntimeException("Internal server error!" + 
                    "Can't find groupBeanLocalHome with name java:app/tss-ejb/RegistrationBean!com.netcracker.ejb.RegistrationBeanLocalHome");
        }
    }
}
