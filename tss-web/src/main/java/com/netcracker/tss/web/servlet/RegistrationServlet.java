/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.tss.web.servlet;

import com.netcracker.ejb.RegistrationBeanLocal;
import com.netcracker.ejb.RegistrationBeanLocalHome;
import com.netcracker.entity.Driver;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.Category;
import com.netcracker.exceptions.InvalidEntityException;
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

import com.netcracker.util.BeansLocator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Виктор
 * @author maks
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirPassword");
            Integer driverToken = null;
            if (!"".equals(request.getParameter("token")) && (request.getParameter("token") != null)) {
                driverToken = Integer.valueOf(request.getParameter("token"));
            }


            if (password.equals(confirmPassword)) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                password = encoder.encode(password);

                RegistrationBeanLocal rb = BeansLocator.getInstance().getBean(RegistrationBeanLocal.class);//!!

                if (driverToken != null) {
                    Driver driver = createDriverWithSpecifiedParameters(request);
                    driver.setUsername(userName);
                    driver.setPasswordHash(password);
                    driver.setToken(driverToken);

                    rb.registrateDriver(driver);
                    response.sendRedirect("/driver");
                    return;
                }

                User user = new User(userName, email, password);
                if (!rb.isUserExist(user)) {
                    rb.registrate(user);
                    response.sendRedirect("/customer");
                }
            } else {
                response.sendRedirect("/signup.jsp");
            }
        } catch (InvalidEntityException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/signup.jsp").forward(request,response);
//            response.sendRedirect("/signup.jsp?errorMessage=" + e.getMessage());
        }

    }

    private Driver createDriverWithSpecifiedParameters(HttpServletRequest req) {
        return new Driver(Category.valueOf(req.getParameter("category")),
                isOn(req.getParameter("available")),
                isOn(req.getParameter("ismale")),
                isOn(req.getParameter("smokes")));
    }


    private boolean isOn(String checkBoxText) {
        return "on".equals(checkBoxText);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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


}
