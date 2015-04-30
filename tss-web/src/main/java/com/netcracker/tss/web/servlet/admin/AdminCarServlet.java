package com.netcracker.tss.web.servlet.admin;

import com.netcracker.dao.CarDao;
import com.netcracker.entity.Car;
import com.netcracker.tss.web.router.ActionRequest;
import com.netcracker.tss.web.router.ActionRouter;
import com.netcracker.tss.web.router.config.ActionRouterDescriber;
import com.netcracker.tss.web.router.Route;
import com.netcracker.tss.web.router.config.HttpMethod;
import com.netcracker.tss.web.router.config.RouterNameDescriber;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kyrylo Berehovyi on 25/04/2015.
 */

@WebServlet(urlPatterns = "/admin/cars")
public class AdminCarServlet extends HttpServlet {

    public static final Page template = Page.ADMIN_TEMPLATE;
    public static final Page defaultPageContent = Page.ADMIN_CARS_CONTENT;
    public static final Page addCarPageContent = Page.ADMIN_ADD_CAR_CONTENT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), defaultPageContent.getType());
//        req.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "Test error Message");
//        req.setAttribute(RequestAttribute.SUCCESS_MESSAGE.getName(), "Test success Message");

//        String action = req.getParameter("action");
//        if ("add-car".equals(action)) {
//            req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), addCarPageContent.getAbsolutePath());
//            req.getRequestDispatcher(template.getAbsolutePath()).forward(req, resp);
//            return;
//        }
//        redirectToCars(2, 10, req, resp);
//        ActionRouter router = (ActionRouter) req.getServletContext()
//                .getAttribute(ActionRouterDescriber.ACTION_ROUTER.getAttributeName());
//        Route route = router.getRoute(req.getParameter("action"));
//        ActionRequest actionRequest = route.action(req);
//        if (actionRequest.isForward())
//            req.getRequestDispatcher(actionRequest.getDestinationResource()).forward(req, resp);
//        else
//            resp.sendRedirect(actionRequest.getDestinationResource());


        doProcessing(req, resp, HttpMethod.GET);
    }

    private void redirectToCars(int pageNumber, int pageSize, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDao carDao = new CarDao();
        List<Car> cars = carDao.getPage(pageNumber, pageSize);
        carDao.close();

        req.setAttribute(RequestAttribute.CAR_LIST.getName(), cars);
        req.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), defaultPageContent.getAbsolutePath());
        req.getRequestDispatcher(template.getAbsolutePath()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        if ("add-car".equals(action)) {
//            CarDao carDao = new CarDao();
//            carDao.persist(new Car(req.getParameter("plate"), isOn(req.getParameter("avaliable")),
//                    Integer.parseInt(req.getParameter("category")), isOn(req.getParameter("animalable")),
//                    isOn(req.getParameter("wi-fi")), isOn(req.getParameter("conditioner"))));
//            carDao.close();
//            List<Car> cars = carDao.getPage(1, 10);
//            carDao.close();
//
//            if(cars != null) {
//                req.setAttribute(ATTRIBUTE_CARS, cars);
//            }

//            redirectToCars(1, 10, req, resp);
            doProcessing(req, resp, HttpMethod.POST);
//        }
    }

    private boolean isOn (String checkBoxText){
        return "on".equals(checkBoxText);
    }

    private void doProcessing(HttpServletRequest request, HttpServletResponse response, HttpMethod method)
            throws ServletException, IOException {
        Route route = findRoute(request, response, method);
        ActionRequest actionRequest = null;
        try {
            actionRequest = route.action(request);
        } catch (Exception e) {
            forwardToErrorPage(request, response, actionRequest, e.getMessage());
            return;
        }
        sendRequest(request, response, actionRequest);
    }

    private void sendRequest(HttpServletRequest request, HttpServletResponse response, ActionRequest actionRequest)
            throws ServletException, IOException {
        if (actionRequest == null || actionRequest.getDestinationResource() == null) {
            forwardToErrorPage(request, response, actionRequest, null);
            return;
        }

        setMessagesIfExists(request, actionRequest);
        if (actionRequest.isRedirect()) {
            response.sendRedirect(actionRequest.getDestinationResource());
            return;
        }
        request.getRequestDispatcher(actionRequest.getDestinationResource()).forward(request, response);
    }

    private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response,
            ActionRequest actionRequest, String errorMessage) throws ServletException, IOException {
        setMessagesIfExists(request, actionRequest, errorMessage);
        request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ERROR_500_CONTENT.getAbsolutePath());
        request.getRequestDispatcher(Page.ADMIN_TEMPLATE.getAbsolutePath()).forward(request, response);
    }

    private void setMessagesIfExists(HttpServletRequest request, ActionRequest actionRequest, String errorMessage) {
        setMessagesIfExists(request, actionRequest);

        String resultErrorMessage;
        if (errorMessage != null) {
            resultErrorMessage = (String) request.getAttribute(RequestAttribute.ERROR_MESSAGE.getName());
            if(resultErrorMessage != null) {
                resultErrorMessage += "\n" + errorMessage;
            } else {
                resultErrorMessage = errorMessage;
            }
            request.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), resultErrorMessage);
        }
    }

    private void setMessagesIfExists(HttpServletRequest request, ActionRequest actionRequest) {
        if (actionRequest == null) {
            return;
        }
        if(actionRequest.getErrorMessage() != null) {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), actionRequest.getErrorMessage());
        }
        if(actionRequest.getSuccessMessage() != null) {
            request.setAttribute(RequestAttribute.SUCCESS_MESSAGE.getName(), actionRequest.getSuccessMessage());
        }
    }

    private Route findRoute(HttpServletRequest request, HttpServletResponse response, HttpMethod method) {
        ActionRouter router = getActionRouterFromServletContext(request.getServletContext());
        String routeName = calculateRouteName(method, request);
        System.out.println(routeName);
        return router.getRoute(routeName);
    }

    private ActionRouter getActionRouterFromServletContext(ServletContext context) {
        return  (ActionRouter) context.getAttribute(ActionRouterDescriber.ACTION_ROUTER.getAttributeName());
    }

    private List<RouterNameDescriber> getRouterNameDescriberListFromServletContext(ServletContext context) {
        return  (List) context.getAttribute(ActionRouterDescriber.ROUTER_NAME_DESCRIBER_LIST.getAttributeName());
    }

    private String calculateRouteName(HttpMethod method, HttpServletRequest request) {
       List<RouterNameDescriber> nameDescriberList =
               getRouterNameDescriberListFromServletContext(request.getServletContext());
        StringBuilder builder = new StringBuilder(method.getName());
        String parameterName;
        for (RouterNameDescriber describer : nameDescriberList) {
            parameterName = request.getParameter(describer.getName());
            if (parameterName != null) {
                builder.append("&").append(describer.getName())
                       .append("=").append(parameterName);
            }
        }
        return builder.toString();
    }

}
