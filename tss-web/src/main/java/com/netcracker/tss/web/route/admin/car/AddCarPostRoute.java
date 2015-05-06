package com.netcracker.tss.web.route.admin.car;

import com.netcracker.ejb.CarBeanLocal;
import com.netcracker.ejb.CarBeanLocalHome;
import com.netcracker.entity.Car;
import com.netcracker.tss.web.router.ActionRequest;
import com.netcracker.tss.web.router.DefaultActionRequest;
import com.netcracker.tss.web.router.Route;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.tss.web.util.RequestParameterParser;
import com.netcracker.util.BeansLocator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kyrylo Berehovyi on 28/04/2015.
 */

@Deprecated
public class AddCarPostRoute implements Route {

    public static final String ROUTE_NAME = "post&menu=cars&action=add-car";

    public static final String LICENSE_REQUEST_PARAMETER = "license";
    public static final String AVAILABLE_REQUEST_PARAMETER = "available";
    public static final String ANIMAILABLE_REQUEST_PARAMETER = "animailable";
    public static final String WIFI_REQUEST_PARAMETER = "wifi";
    public static final String CONDITIONER_REQUEST_PARAMETER = "conditioner";
    public static final String CATEGORY_REQUEST_PARAMETER = "category";

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public ActionRequest action(HttpServletRequest request) {
        Car car = createCarFromRequestParameters(request);
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_CARS_CONTENT.getType());
        String errorMessage = validationErrors(car);
        if(errorMessage == null) {
            doBusinessOperations(request, car);
        } else {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), errorMessage);
        }
        request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_CAR_CONTENT.getAbsolutePath());
        return new DefaultActionRequest(Page.ADMIN_TEMPLATE.getAbsolutePath(), false);
    }

    @Override
    public String getRouteName() {
        return ROUTE_NAME;
    }

    private Car createCarFromRequestParameters(HttpServletRequest request) {
        Car car = new Car();
        boolean animalable = RequestParameterParser.parseBoolean(request, ANIMAILABLE_REQUEST_PARAMETER);
        boolean available = RequestParameterParser.parseBoolean(request, AVAILABLE_REQUEST_PARAMETER);
        boolean wifi = RequestParameterParser.parseBoolean(request, WIFI_REQUEST_PARAMETER);
        boolean conditioner = RequestParameterParser.parseBoolean(request, CONDITIONER_REQUEST_PARAMETER);
        String license = request.getParameter(LICENSE_REQUEST_PARAMETER);
        car.setLicPlate(license != null ? license.toUpperCase() : license);
        car.setCategory(RequestParameterParser.parseInteger(request, CATEGORY_REQUEST_PARAMETER));
        car.setAnimalable(animalable);
        car.setAvailable(available);
        car.setWifi(wifi);
        car.setConditioner(conditioner);

        System.out.println("Parsed Car: " + car);
        return car;
    }

    private void doBusinessOperations(HttpServletRequest request, Car car) {
        if (BeansLocator.getInstance().getCarBean().insertCar(car)) {
            request.setAttribute(RequestAttribute.SUCCESS_MESSAGE.getName(), "New Car was successfully added.");
            return;
        }
        request.setAttribute(RequestAttribute.ERROR_MESSAGE.getName(), "License plate number is busy.");

    }

    public String validationErrors(Car car) {
        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);
        if(constraintViolations.isEmpty()) {
            return null;
        }
        return generateErrorMessageFromConstraintViolations(constraintViolations);
    }

    private String generateErrorMessageFromConstraintViolations(Set<ConstraintViolation<Car>> constraintViolations) {
        StringBuilder errorBuilder = new StringBuilder();
        for (ConstraintViolation<Car> constraintViolation : constraintViolations) {
            errorBuilder.append(constraintViolation.getMessage()).append("\n");
        }
        return errorBuilder.toString();
    }
}
