package com.netcracker.tss.web.route.admin;

import com.netcracker.ejb.TariffBeanLocal;
import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.entity.Tariff;
import com.netcracker.entity.helper.Roles;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.servlet.admin.AdminGroupServlet;
import com.netcracker.tss.web.util.*;
import com.netcracker.util.BeansLocator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author maks
 */

@ActionRoute(menu = "tariffs")
public class Tariffs {

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final Integer MIN_PAGE_NUMBER = 1;
    private static final String MENU_PARAMETER_NAME = "menu";
    private static final String MENU_PARAMETER_VALUE = "tariffs";
    private static final String ACTION_PARAMETER_NAME = "action";


    @Action(action = "save", httpMethod = HttpMethod.POST)
    public ActionResponse post(HttpServletRequest req) {
        TariffBeanLocal tariffBean = BeansLocator.getInstance().getBean(TariffBeanLocal.class);
        tariffBean.editTariff(Integer.parseInt(req.getParameter("id")), new Float(req.getParameter("add")),
                new Float(req.getParameter("mult")));
        ActionResponse response = redirectToEditTariffs(req);
        response.setSuccessMessage("Tariff saved");
        return response;
    }

    @Action(action = "search")
    public ActionResponse searchTariffs(HttpServletRequest req) {
        return searchTariffs(Page.ADMIN_TARIFFS_CONTENT.getAbsolutePath(), req, "view");
    }

    @Action(action = "search-for-edit")
    public ActionResponse searchForEditTariffs(HttpServletRequest req) {
        return searchTariffs(Page.ADMIN_EDIT_TARIFFS_CONTENT.getAbsolutePath(), req, "search-for-edit");
    }


    private ActionResponse searchTariffs(String pagePath, HttpServletRequest request, String actionParameter) {
            Integer page = parsePageNumberFromRequest(request);
            String namePart = request.getParameter("name");
            TariffBeanLocal tariffBeanLocal = BeansLocator.getInstance().getBean(TariffBeanLocal.class);

            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
            pagerLink.addParameter(ACTION_PARAMETER_NAME, actionParameter);

            request.setAttribute(RequestAttribute.PAGER.getName(),
                    tariffBeanLocal.getPager(page, DEFAULT_PAGE_SIZE, namePart));
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);

            request.setAttribute("tariffs", tariffBeanLocal.searchTariffByName(namePart, page, DEFAULT_PAGE_SIZE));
            request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_TARIFFS_CONTENT.getType());
            request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), pagePath);
            return new ActionResponse(pagePath);
    }

    @Action(action = "view")
    public ActionResponse redirectToTariffs(HttpServletRequest req) {
        return redirectToTariffs(Page.ADMIN_TARIFFS_CONTENT.getAbsolutePath(), req, "view");
    }

    @Action(action = "edit")
    public ActionResponse redirectToEditTariffs(HttpServletRequest req) {
        return redirectToTariffs(Page.ADMIN_EDIT_TARIFFS_CONTENT.getAbsolutePath(), req, "edit");
    }

    private ActionResponse redirectToTariffs(String pagePath, HttpServletRequest request,  String actionParameter) {
            Integer page = parsePageNumberFromRequest(request);
            TariffBeanLocal tariffBeanLocal = BeansLocator.getInstance().getBean(TariffBeanLocal.class);

            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
            pagerLink.addParameter(ACTION_PARAMETER_NAME, actionParameter);

            request.setAttribute(RequestAttribute.PAGER.getName(),
                    tariffBeanLocal.getPager(page, DEFAULT_PAGE_SIZE));
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);

            request.setAttribute("tariffs", tariffBeanLocal.getTariffPage(page, DEFAULT_PAGE_SIZE));
            request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_TARIFFS_CONTENT.getType());
            return new ActionResponse(pagePath);
    }

    private Integer parsePageNumberFromRequest(HttpServletRequest request) {
        Integer pageNumber = RequestParameterParser.parseInteger(request, RequestParameter.PAGE.getValue());
        if (pageNumber == null || pageNumber < MIN_PAGE_NUMBER) {
            return MIN_PAGE_NUMBER;
        }
        return pageNumber;
    }
}
