package com.netcracker.tss.web.route.admin.car;

import com.netcracker.entity.Car;
import com.netcracker.tss.web.router.ActionRequest;
import com.netcracker.tss.web.router.DefaultActionRequest;
import com.netcracker.tss.web.router.Route;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.tss.web.util.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Kyrylo Berehovyi on 28/04/2015.
 */

public class AddCarPostRoute implements Route {

    public static final String ROUTE_NAME = "post&menu=cars&action=add-car";

    public static final String LICENSE_REQUEST_PARAMETER = "license";
    public static final String AVAILABLE_REQUEST_PARAMETER = "available";
    public static final String ANIMAILABLE_REQUEST_PARAMETER = "animailable";
    public static final String WIFI_REQUEST_PARAMETER = "wifi";
    public static final String CONDITIONER_REQUEST_PARAMETER = "conditioner";
    public static final String CATEGORY_REQUEST_PARAMETER = "category";

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();;

    @Override
    public ActionRequest action(HttpServletRequest request) {
        Car car = createCarFromRequestParameters(request);
        if(isValid(car)) {
            System.out.println("Start Add Car processing...");
            request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_CARS_CONTENT.getAbsolutePath());
            return new DefaultActionRequest(Page.ADMIN_TEMPLATE.getAbsolutePath(), false, true);
        } else {
            request.setAttribute(RequestAttribute.PAGE_CONTENT.getName(), Page.ADMIN_ADD_CAR_CONTENT.getAbsolutePath());
            return new DefaultActionRequest(Page.ADMIN_TEMPLATE.getAbsolutePath(), false, true);
        }
    }

    @Override
    public String getRouteName() {
        return ROUTE_NAME;
    }

    private Car createCarFromRequestParameters(HttpServletRequest request) {
        Car car = new Car();
        Boolean animalable = RequestParameterParser.parseBoolean(request, ANIMAILABLE_REQUEST_PARAMETER);
        Boolean available = RequestParameterParser.parseBoolean(request, AVAILABLE_REQUEST_PARAMETER);
        Boolean wifi = RequestParameterParser.parseBoolean(request, WIFI_REQUEST_PARAMETER);
        Boolean conditioner = RequestParameterParser.parseBoolean(request, CONDITIONER_REQUEST_PARAMETER);

        car.setLicPlate(request.getParameter(LICENSE_REQUEST_PARAMETER));
        car.setCategory(RequestParameterParser.parseInteger(request, CATEGORY_REQUEST_PARAMETER));

        if (animalable != null) {
            car.setAnimalable(animalable);
        }
        if (available != null) {
            car.setAvailable(available);
        }
        if (wifi != null) {
            car.setWifi(wifi);
        }
        if (conditioner != null) {
            car.setAvailable(conditioner);
        }

        return car;
    }

    public boolean isValid(Car car) {
        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);
        if(constraintViolations.isEmpty()) {
            return true;
        }
        for (ConstraintViolation<Car> carConstraintViolation : constraintViolations) {
            System.out.println(carConstraintViolation.getMessage());
        }
        return false;
    }
}
