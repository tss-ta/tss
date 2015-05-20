package com.netcracker.ejb;

import com.netcracker.entity.Car;
import com.netcracker.entity.User;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * @author Kyrylo Berehovyi
 */

public class ValidatorBean implements SessionBean {

    public String validate(User user) {
        Set<ConstraintViolation<User>> constraintViolations = cretateValidator().validate(user);
        if(constraintViolations.isEmpty()) {
            return null;
        }
        return generateErrorMessageFromViolations(constraintViolations);
    }

    public <T> String validate(T entity) {
        Set<ConstraintViolation<T>> constraintViolations = cretateValidator().validate(entity);
        if(constraintViolations.isEmpty()) {
            return null;
        }
        return generateErrorMessageFromViolations(constraintViolations);
    }

    public String validateCar(Car car) {
        Set<ConstraintViolation<Car>> constraintViolations = cretateValidator().validate(car);
        if(constraintViolations.isEmpty()) {
            return null;
        }
        return generateErrorMessageFromConstraintViolations(constraintViolations);
    }

    public Validator cretateValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    private String generateErrorMessageFromConstraintViolations(Set<ConstraintViolation<Car>> constraintViolations) {
        StringBuilder errorBuilder = new StringBuilder();
        for (ConstraintViolation<Car> constraintViolation : constraintViolations) {
            errorBuilder.append(constraintViolation.getMessage()).append("\n");
        }
        return errorBuilder.toString();
    }

    public <T> String generateErrorMessageFromViolations(Set<ConstraintViolation<T>> constraintViolations) {
        StringBuilder errorBuilder = new StringBuilder();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            errorBuilder.append(constraintViolation.getMessage()).append("\n");
        }
        return errorBuilder.toString();
    }

    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {

    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {

    }
}
