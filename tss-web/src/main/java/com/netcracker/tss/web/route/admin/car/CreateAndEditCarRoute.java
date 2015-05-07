package com.netcracker.tss.web.route.admin.car;

import com.netcracker.ejb.CarBeanLocal;
import com.netcracker.ejb.ValidatorBeanLocal;
import com.netcracker.entity.Car;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.*;
import com.netcracker.util.BeansLocator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyrylo Berehovyi
 */

@ActionRoute(menu = "car")
public class CreateAndEditCarRoute {

    private static final String EDIT_CAR_ACTION = "edit";
    private static final String ADD_CAR_ACTION = "add";
    private static final String CAR_MENU = "car";

    private static final String LICENSE_REQUEST_PARAMETER = "license";
    private static final String AVAILABLE_REQUEST_PARAMETER = "available";
    private static final String ANIMAILABLE_REQUEST_PARAMETER = "animailable";
    private static final String WIFI_REQUEST_PARAMETER = "wifi";
    private static final String CONDITIONER_REQUEST_PARAMETER = "conditioner";
    private static final String CATEGORY_REQUEST_PARAMETER = "category";

    @Action(action = "add", httpMethod = HttpMethod.POST)
    public ActionResponse addCar(HttpServletRequest request) {
        Car car = createCarFromRequestParameters(request);
        ActionResponse actionResponse = new ActionResponse();
        ValidatorBeanLocal validatorBean = BeansLocator.getInstance().getBean(ValidatorBeanLocal.class);
        String errorMessage = validatorBean.validateCar(car);
        if(errorMessage == null) {
            performAddCar(actionResponse, car);
        } else {
            actionResponse.setErrorMessage(errorMessage);
        }
        request.setAttribute(RequestAttribute.PAGER_LINK.getName(), createAddPageLink());
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CARS_CONTENT.getType());
        actionResponse.setPageContent(Page.ADMIN_ADD_CAR_CONTENT.getAbsolutePath());
        return actionResponse;
    }

    @Action(action = "edit", httpMethod = HttpMethod.POST)
    public ActionResponse editCar(HttpServletRequest request) {
        Car car = createCarFromRequestParameters(request);
        ActionResponse actionResponse = new ActionResponse();
        ValidatorBeanLocal validatorBean = BeansLocator.getInstance().getBean(ValidatorBeanLocal.class);
        String errorMessage = validatorBean.validateCar(car);
        if(errorMessage == null) {
            performEditCar(actionResponse, car);
        } else {
            actionResponse.setErrorMessage(errorMessage);
        }
        request.setAttribute(RequestAttribute.CAR.getName(), car);
        request.setAttribute(RequestAttribute.PAGER_LINK.getName(), createEditPageLink(car.getId()));
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CARS_CONTENT.getType());
        actionResponse.setPageContent(Page.ADMIN_ADD_CAR_CONTENT.getAbsolutePath());
        return actionResponse;
    }

    @Action(action = "add")
    public ActionResponse getAddCarForm(HttpServletRequest request) {
        request.setAttribute(RequestAttribute.PAGER_LINK.getName(), createAddPageLink());
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_CAR_CONTENT.getType());
        request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_CAR_CONTENT.getAbsolutePath());
        return new ActionResponse(Page.ADMIN_ADD_CAR_CONTENT.getAbsolutePath());
    }

    @Action(action = "edit")
    public ActionResponse getEditCarForm(HttpServletRequest request) {
        Integer id = RequestParameterParser.parseInteger(request, RequestParameter.ID.getValue());
        ActionResponse actionResponse = new ActionResponse();
        CarBeanLocal carBean;
        Car car;
        if(id != null) {
            carBean = BeansLocator.getInstance().getBean(CarBeanLocal.class);
            car = carBean.getById(id);
            request.setAttribute(RequestAttribute.CAR.getName(), car);
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), createEditPageLink(id));
            request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_ADD_CAR_CONTENT.getType());
            actionResponse.setPageContent(Page.ADMIN_ADD_CAR_CONTENT.getAbsolutePath());
        } else {
            actionResponse.setPageContent(Page.ERROR_404_CONTENT.getAbsolutePath());
        }
        return actionResponse;
    }

    private void performAddCar(ActionResponse actionResponse, Car car) {
        if (BeansLocator.getInstance().getCarBean().insertCar(car)) {
            actionResponse.setSuccessMessage("New Car was successfully added.");
            return;
        }
        actionResponse.setErrorMessage("License plate number is busy.");
    }

    private void performEditCar(ActionResponse actionResponse, Car car) {
        BeansLocator.getInstance().getCarBean().updateCar(car);
        actionResponse.setSuccessMessage("Car was successfully updated.");
    }

    private PagerLink createEditPageLink(Integer id) {
        PagerLink pagerLink = new PagerLink();
        pagerLink.addParameter(UriParam.MENU.getParamName(), CAR_MENU);
        pagerLink.addParameter(UriParam.ACTION.getParamName(), EDIT_CAR_ACTION);
        pagerLink.addParameter(RequestParameter.ID.getValue(), id.toString());
        return pagerLink;
    }

    private PagerLink createAddPageLink() {
        PagerLink pagerLink = new PagerLink();
        pagerLink.addParameter(UriParam.MENU.getParamName(), CAR_MENU);
        pagerLink.addParameter(UriParam.ACTION.getParamName(), ADD_CAR_ACTION);
        return pagerLink;
    }

    private Car createCarFromRequestParameters(HttpServletRequest request) {
        Car car = new Car();
        boolean animalable = RequestParameterParser.parseBoolean(request, ANIMAILABLE_REQUEST_PARAMETER);
        boolean available = RequestParameterParser.parseBoolean(request, AVAILABLE_REQUEST_PARAMETER);
        boolean wifi = RequestParameterParser.parseBoolean(request, WIFI_REQUEST_PARAMETER);
        boolean conditioner = RequestParameterParser.parseBoolean(request, CONDITIONER_REQUEST_PARAMETER);
        String license = request.getParameter(LICENSE_REQUEST_PARAMETER);
        car.setId(RequestParameterParser.parseInteger(request, RequestParameter.ID.getValue()));
        car.setLicPlate(license != null ? license.toUpperCase() : license);
        car.setCategory(RequestParameterParser.parseInteger(request, CATEGORY_REQUEST_PARAMETER));
        car.setAnimalable(animalable);
        car.setAvailable(available);
        car.setWifi(wifi);
        car.setConditioner(conditioner);
        return car;
    }
}
