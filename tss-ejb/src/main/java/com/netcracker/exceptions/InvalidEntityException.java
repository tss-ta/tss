package com.netcracker.exceptions;

/**
 * Created by maks on 11.05.15.
 */
public class InvalidEntityException extends RuntimeException {

    public InvalidEntityException () {
        super("Entity class have some invalid fields");
    }

    public InvalidEntityException (String message) {
        super(message);
    }

    public InvalidEntityException (Throwable cause) {
        super("Entity class have some invalid fields", cause);
    }

    public InvalidEntityException (String message, Throwable cause) {
        super(message, cause);
    }

}
