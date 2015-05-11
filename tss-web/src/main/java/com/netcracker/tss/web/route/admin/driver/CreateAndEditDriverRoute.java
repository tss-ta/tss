package com.netcracker.tss.web.route.admin.driver;

import com.netcracker.dao.DriverDAO;
import com.netcracker.dao.RoleDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.dao.exceptions.NoSuchEntity;
import com.netcracker.ejb.DriverLocal;
import com.netcracker.entity.Driver;
import com.netcracker.entity.Role;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.Category;
import com.netcracker.entity.helper.Roles;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.route.admin.Users;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.BeansLocator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
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

    @Action(action = "checkUsers")
    public ActionResponse addDriver(HttpServletRequest req) {
        req.setAttribute("forAssignee", true);
        return new Users().getUsersView(req);
    }

    @Action(action = "assignFields")
    public ActionResponse assignFieldsToADriver(HttpServletRequest req) {
        return new ActionResponse(Page.ADMIN_ADD_DRIVER_CONTENT.getAbsolutePath());
    }

    @Action(action = "newdriver")
    public ActionResponse createDriver(HttpServletRequest req) throws ServletException, IOException {
        UserDAO userDAO = null;
        RoleDAO roleDAO = null;
        Driver driver = null;
        try {
            userDAO = new UserDAO();
            User user = userDAO.get(Integer.parseInt(req.getParameter(PARAMETER_DRIVER_ID)));

            roleDAO = new RoleDAO();
            Role role = roleDAO.findByRolename(Roles.DRIVER.getFormattedName());
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(role);

            driver = new Driver(user.getUsername(),
                                user.getEmail(),
                                user.getPasswordHash(),
                                Category.valueOf(req.getParameter(PARAMETER_CATEGORY)),
                                isOn(req.getParameter(PARAMETER_AVAILABLE)),
                                isOn(req.getParameter(PARAMETER_IS_MALE)),
                                isOn(req.getParameter(PARAMETER_SMOKES)));


            driver.setRoles(roles);

            userDAO.delete(user); //TODO:userDAO doesn't delete unnecessary user!

            BeansLocator.getInstance().getDriverBean().addDriver(driver);

        } catch (NoSuchEntity noSuchEntity) {
            noSuchEntity.printStackTrace();
        } finally {
            if(userDAO != null && userDAO.isOpen()) {
                userDAO.close();
            }

            if(roleDAO != null) {
                roleDAO.close();
            }
        }

        req.setAttribute("page", 1);
        req.setAttribute("role", Roles.DRIVER.getFormattedName());

        return new Users().getUsersView(req);
    }

    @Action(action = "editdriver")
    public ActionResponse editDriver(HttpServletRequest req) throws ServletException, IOException {
        DriverLocal driverLocal = BeansLocator.getInstance().getDriverBean();
        Driver driver = driverLocal.getDriver(Integer.valueOf(req.getParameter(PARAMETER_DRIVER_ID)));

        if(driver != null) {
            driverLocal.editDriver(updateDriverFromRequest(driver, req));
        }

        List<Driver> drivers = driverLocal.getDriverPage(1, 10);
        req.setAttribute(RequestAttribute.DRIVER_LIST.getName(), drivers);

        return new ActionResponse(Page.ADMIN_DRIVERS_CONTENT.getAbsolutePath());
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
