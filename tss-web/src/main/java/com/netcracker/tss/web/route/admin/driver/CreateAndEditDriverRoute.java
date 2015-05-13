package com.netcracker.tss.web.route.admin.driver;

<<<<<<< HEAD
import com.netcracker.dao.*;
import com.netcracker.ejb.DriverLocal;
import com.netcracker.ejb.MailerBeanLocal;
=======
import com.netcracker.ejb.DriverLocal;
import com.netcracker.ejb.MailerBeanLocal;
import com.netcracker.ejb.PageCalculatorBeanLocal;
>>>>>>> develop
import com.netcracker.entity.Driver;
import com.netcracker.entity.helper.Category;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.Page;
<<<<<<< HEAD
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.BeansLocator;
import com.netcracker.util.TokenGenerator;
import org.springframework.context.annotation.Bean;
=======
import com.netcracker.tss.web.util.PagerLink;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.BeansLocator;
import com.netcracker.util.TokenGenerator;
>>>>>>> develop

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

        if(driverEmail != null) {
            driver = new Driver(driverEmail, token);
            BeansLocator.getInstance().getDriverBean().addDriver(driver);
        }

        ActionResponse actResp = new ActionResponse();

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

<<<<<<< HEAD
    @Action(action = "editdriver")
=======
    @Action(action = "editdriver", httpMethod = HttpMethod.POST)
>>>>>>> develop
    public ActionResponse editDriver(HttpServletRequest req) throws ServletException, IOException {
        DriverLocal driverLocal = BeansLocator.getInstance().getDriverBean();
        Driver driver = driverLocal.getDriver(Integer.valueOf(req.getParameter(PARAMETER_DRIVER_ID)));

        if(driver != null) {
            driverLocal.editDriver(updateDriverFromRequest(driver, req));
        }

<<<<<<< HEAD
        List<Driver> drivers = driverLocal.getDriverPage(1, 10);
        req.setAttribute(RequestAttribute.DRIVER_LIST.getName(), drivers);

        return new ActionResponse(Page.ADMIN_DRIVERS_CONTENT.getAbsolutePath());
=======
        return new ViewDriverRoute().getAllDriversPage(req);
>>>>>>> develop
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
}
