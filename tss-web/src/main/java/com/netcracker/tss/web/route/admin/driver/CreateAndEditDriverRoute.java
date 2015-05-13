package com.netcracker.tss.web.route.admin.driver;


import com.netcracker.ejb.*;
import com.netcracker.entity.Driver;
import com.netcracker.entity.helper.Category;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.Page;

import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.BeansLocator;
import com.netcracker.util.TokenGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author Illia Rudenko
 */

@ActionRoute(menu = "drivers")
public class CreateAndEditDriverRoute {

    public static final String PARAMETER_DRIVER_ID = "id";
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
        Driver driver = null;
        Integer token = TokenGenerator.generate();

        ActionResponse actResp = new ActionResponse();

        if(driverEmail != null) {
            driver = new Driver(driverEmail, token);
            String errorMessage = validateDriver(driver);

            if(errorMessage == null) {
                BeansLocator.getInstance().getDriverBean().addDriver(driver);
            } else {
                actResp.setErrorMessage(errorMessage);
            }
        }

        if(driver != null) {
            MailerBeanLocal mailerBean = BeansLocator.getInstance().getBean(MailerBeanLocal.class);
            mailerBean.sendToken(driverEmail, token);
            actResp.setSuccessMessage("Token was successfully sent");
        } else {
            actResp.setErrorMessage("Error while sending token");
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
}
