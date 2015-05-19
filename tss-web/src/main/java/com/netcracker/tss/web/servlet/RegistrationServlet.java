
package com.netcracker.tss.web.servlet;

import com.netcracker.ejb.*;
import com.netcracker.entity.Driver;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.Category;
import com.netcracker.exceptions.InvalidEntityException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.netcracker.tss.web.util.RequestParameterParser;
import com.netcracker.util.BeansLocator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Виктор
 * @author maks
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet", "/driver-registration"})
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
            Integer driverToken = RequestParameterParser.parseInteger(request, "token");

            if (password.equals(confirmPassword)) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                password = encoder.encode(password);

                RegistrationBeanLocal rb = BeansLocator.getInstance().getBean(RegistrationBeanLocal.class);

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
                rb.registrate(user);
                response.sendRedirect("/customer");
            } else {
                response.sendRedirect("/signup.jsp");
            }
        } catch (InvalidEntityException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
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
        try {
            Integer driverToken = RequestParameterParser.parseInteger(request, "token");
            if (driverToken != null) {
                DriverLocal driverBean = BeansLocator.getInstance().getDriverBean();
                Driver driver = driverBean.getDriverByToken(driverToken);
                request.setAttribute("email", driver.getEmail());
                request.getRequestDispatcher("/WEB-INF/views/driver-registration.jsp").forward(request, response);
            }
        } catch (InvalidEntityException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/driver-registration.jsp").forward(request, response);
        }
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
