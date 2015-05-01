package com.netcracker.tss.web.servlet.admin;

import com.netcracker.ejb.TariffBean;
import com.netcracker.ejb.TariffBeanLocal;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.BeansLocator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kyrylo Berehovyi on 25/04/2015.
 */
@WebServlet(urlPatterns = "/admin/tariff")
public class AdminTariffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            redirectToEditTariffs(req, resp);
        } else {
            redirectToTariffs(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String action = req.getParameter("action");
        if ("edit".equals(action)) {
            TariffBeanLocal tariffBean = BeansLocator.getInstance().getTariffBean();
            tariffBean.editTariff(Integer.parseInt(req.getParameter("id")), new Float(req.getParameter("add")), 
                    new Float(req.getParameter("mult")));
            req.setAttribute(RequestAttribute.SUCCESS_MESSAGE.getName(), "Saved");
            redirectToEditTariffs(req, resp);
        } else {
            redirectToTariffs(req, resp);
        }
    }
    
    

    private void redirectToTariffs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TariffBeanLocal groupBeanLocal = BeansLocator.getInstance().getTariffBean();
            req.setAttribute("tariffs", groupBeanLocal.getTariffPage(1, 10));
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_TARIFFS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_TARIFFS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } catch (RuntimeException e) {
            req.getRequestDispatcher("/500.jsp").forward(req, resp);
        }
    }
    
    private void redirectToEditTariffs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TariffBeanLocal groupBeanLocal = BeansLocator.getInstance().getTariffBean();
            req.setAttribute("tariffs", groupBeanLocal.getTariffPage(1, 10));
            req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_TARIFFS_CONTENT.getType());
            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_EDIT_TARIFFS_CONTENT.getAbsolutePath());
            req.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(req, resp);
        } catch (RuntimeException e) {
            req.getRequestDispatcher("/500.jsp").forward(req, resp);
        }
    }
}
