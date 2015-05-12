package com.netcracker.ejb;

import com.netcracker.entity.Car;

import javax.ejb.EJBLocalObject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Kyrylo Berehovyi
 */
public interface ValidatorBeanLocal extends EJBLocalObject {

    String validateCar(Car car);

    Validator cretateValidator();

    <T> String generateErrorMessageFromViolations(Set<ConstraintViolation<T>> constraintViolations);

    <T> String validate(T entity);

    //String generateErrorMessageFromConstraintViolations(Set<ConstraintViolation<Car>> constraintViolations);
}

