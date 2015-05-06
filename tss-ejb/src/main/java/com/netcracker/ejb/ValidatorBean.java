package com.netcracker.ejb;

import com.netcracker.entity.Car;

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

    public String validateCar(Car car) {
        Set<ConstraintViolation<Car>> constraintViolations = cretateValidator().validate(car);
        if(constraintViolations.isEmpty()) {
            return null;
        }
        return generateErrorMessageFromConstraintViolations(constraintViolations);
    }

    private Validator cretateValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    private String generateErrorMessageFromConstraintViolations(Set<ConstraintViolation<Car>> constraintViolations) {
        StringBuilder errorBuilder = new StringBuilder();
        for (ConstraintViolation<Car> constraintViolation : constraintViolations) {
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
