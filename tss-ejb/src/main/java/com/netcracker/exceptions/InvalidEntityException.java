package com.netcracker.exceptions;

import javax.ejb.EJBException;

/**
 * Created by maks on 11.05.15.
 */
public class InvalidEntityException extends EJBException {

    public InvalidEntityException () {
        super("Entity class have some invalid fields");
    }

    public InvalidEntityException (String message) {
        super(message);
    }

    public InvalidEntityException (Exception cause) {
        super("Entity class have some invalid fields", cause);
    }

    public InvalidEntityException (String message, Exception cause) {
        super(message, cause);
    }

}
