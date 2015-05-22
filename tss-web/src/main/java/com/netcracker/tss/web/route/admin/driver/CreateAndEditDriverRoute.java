package com.netcracker.tss.web.route.admin.driver;


import com.netcracker.ejb.*;
import com.netcracker.entity.Car;
import com.netcracker.entity.Driver;
import com.netcracker.entity.helper.Category;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.*;

import com.netcracker.util.BeansLocator;
import com.netcracker.util.TokenGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * @author Illia Rudenko
 * @author maks
 */

@ActionRoute(menu = "drivers")
public class CreateAndEditDriverRoute {

    private static final Integer MIN_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 15;
    private static final String ATTRIBUTE_DRIVER = "driver";
    private static final String MENU_PARAMETER_NAME = "menu";
    private static final String MENU_PARAMETER_VALUE = "drivers";
    private static final String ACTION_PARAMETER_NAME = "action";
    private static final String ALL_CARS_ACTION_PARAMETER_VALUE = "getCarPage";

    public static final String ACTION_ASSIGN_CAR = "assign";
    public static final String ACTION_ASSIGN_CAR_VALUE = "true";

    public static final String PARAMETER_DRIVER_ID = "id";
    public static final String PARAMETER_CAR_ID = "carid";
    public static final String PARAMETER_CATEGORY = "category";
    public static final String PARAMETER_AVAILABLE = "available";
    public static final String PARAMETER_IS_MALE = "ismale";
    public static final String PARAMETER_SMOKES = "smokes";
    public static final String PARAMETER_DRIVER_EMAIL = "email";

    @Action(action = "add")
    public ActionResponse getAddDriverForm(HttpServletRequest req) {
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_SEND_TOKEN_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_SEND_TOKEN_CONTENT.getAbsolutePath());
    }

    @Action(action = "assignFields")
    public ActionResponse assignFieldsToADriver(HttpServletRequest req) {
        return new ActionResponse(Page.ADMIN_ADD_DRIVER_CONTENT.getAbsolutePath());
    }

    @Action(action = "add", httpMethod = HttpMethod.POST)
    public ActionResponse sendTokenToDriverEmail(HttpServletRequest req) throws ServletException, IOException {

        String driverEmail = req.getParameter(PARAMETER_DRIVER_EMAIL);
        Driver driver;
        Integer token = TokenGenerator.generate();

        ActionResponse actResp = new ActionResponse();

        if(driverEmail != null && !"".equals(driverEmail)) {
            driverEmail = driverEmail.toLowerCase();
            Driver checkedDriver;

            checkedDriver = BeansLocator.getInstance().getDriverBean().getDriver(driverEmail);

            if(checkedDriver == null) {
                driver = new Driver(driverEmail, token);
                String errorMessage = validateDriver(driver);

                if(errorMessage == null) {
                    BeansLocator.getInstance().getDriverBean().addDriver(driver);
                    MailerBeanLocal mailerBean = BeansLocator.getInstance().getBean(MailerBeanLocal.class);
                    String signUpURL = ServletUtils.getBaseUrl(req) + "/RegistrationServlet?token=" + token;
                    mailerBean.sendDriverInvite(driverEmail, signUpURL);
                    actResp.setSuccessMessage("Invite was successfully sent.");
                } else {
                    actResp.setErrorMessage(errorMessage);
                }

            } else {
                actResp.setErrorMessage("System already contains user with such an email!");
            }
        }



        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_SEND_TOKEN_CONTENT.getType());
        actResp.setPageContent(Page.ADMIN_SEND_TOKEN_CONTENT.getAbsolutePath());

        return actResp;
    }


    @Action(action = "editdriver", httpMethod = HttpMethod.POST)

    public ActionResponse editDriver(HttpServletRequest req) throws ServletException, IOException {
        DriverLocal driverLocal = BeansLocator.getInstance().getDriverBean();
        Driver driver = driverLocal.getDriver(Integer.valueOf(req.getParameter(PARAMETER_DRIVER_ID)));

        ViewDriverRoute viewDriverRoute = new ViewDriverRoute();
        if(driver != null) {
            driver = updateDriverFromRequest(driver, req);
            ValidatorBeanLocal validatorBean = BeansLocator.getInstance().getBean(ValidatorBeanLocal.class);
            String errorMessage = validatorBean.validate(driver);

            if(errorMessage != null) {
                req.setAttribute("errorMsg", errorMessage);
                return viewDriverRoute.getAllDriversPage(req);
            } else {
                driverLocal.editDriver(driver);
            }
        }

        return viewDriverRoute.getAllDriversPage(req);

    }

    @Action(action = "getCarPage")
    public ActionResponse getAssignCarPage(HttpServletRequest req) {

        Integer page = parsePageNumberFromRequest(req);
        if(page >= MIN_PAGE_NUMBER) {
            CarBeanLocal carBeanLocal = BeansLocator.getInstance().getBean(CarBeanLocal.class);
            PageCalculatorBeanLocal pageCalculatorBeanLocal =
                    BeansLocator.getInstance().getBean(PageCalculatorBeanLocal.class);
            List<Car> carList = carBeanLocal.getPageOfCars(page, DEFAULT_PAGE_SIZE);
            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter(MENU_PARAMETER_NAME, MENU_PARAMETER_VALUE);
            pagerLink.addParameter(ACTION_PARAMETER_NAME, ALL_CARS_ACTION_PARAMETER_VALUE);
            pagerLink.addParameter(ACTION_ASSIGN_CAR, ACTION_ASSIGN_CAR_VALUE);
            pagerLink.addParameter(PARAMETER_DRIVER_ID, req.getParameter(PARAMETER_DRIVER_ID));
            req.setAttribute(RequestAttribute.PAGER.getName(),
                    pageCalculatorBeanLocal.createCarPager(page, DEFAULT_PAGE_SIZE));
            req.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
            req.setAttribute(RequestAttribute.CAR_LIST.getName(), carList);
        }
        req.setAttribute(ACTION_ASSIGN_CAR, ACTION_ASSIGN_CAR_VALUE);
        req.setAttribute(PARAMETER_DRIVER_ID, req.getParameter(PARAMETER_DRIVER_ID));
        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CARS_CONTENT.getType());
        return new ActionResponse(Page.ADMIN_CARS_CONTENT.getAbsolutePath());
    }

    @Action(action = "assigncar")
    public ActionResponse assignCar(HttpServletRequest req) {

        Integer driverId = Integer.valueOf(req.getParameter(PARAMETER_DRIVER_ID));
        Integer carId = Integer.valueOf(req.getParameter(PARAMETER_CAR_ID));
        ActionResponse actResp = new ActionResponse();

        if(driverId != null && carId != null) {
            BeansLocator.getInstance().getDriverBean().assignCar(driverId, carId);
            Driver driver = BeansLocator.getInstance().getDriverBean().getDriver(driverId);
            req.setAttribute(ATTRIBUTE_DRIVER, driver);
            actResp.setSuccessMessage("Car is successfully assigned");
        } else {
            actResp.setErrorMessage("Error while car assign");
        }

        req.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_DRIVER_CONTENT.getType());
        actResp.setPageContent(Page.ADMIN_ADD_DRIVER_CONTENT.getAbsolutePath());

        return actResp;
    }

    private Driver updateDriverFromRequest(Driver driver, HttpServletRequest req) throws ServletException, IOException {

            driver.setCategory(Category.valueOf(req.getParameter(PARAMETER_CATEGORY)));
            driver.setAvailable(isOn(req.getParameter(PARAMETER_AVAILABLE)));
            driver.setMale(isOn(req.getParameter(PARAMETER_IS_MALE)));
            driver.setSmokes(isOn(req.getParameter(PARAMETER_SMOKES)));

            return driver;
    }

    private boolean isOn (String checkBoxText){
        return "on".equals(checkBoxText);
    }

    private String validateDriver(Driver driver) {
        ValidatorBeanLocal validatorBean = BeansLocator.getInstance().getBean(ValidatorBeanLocal.class);
        return validatorBean.validate(driver);
    }

    private Integer parsePageNumberFromRequest(HttpServletRequest request) {
        Integer pageNumber = RequestParameterParser.parseInteger(request, RequestParameter.PAGE.getValue());
        if (pageNumber == null)
            return MIN_PAGE_NUMBER;
        return pageNumber;
    }
}
