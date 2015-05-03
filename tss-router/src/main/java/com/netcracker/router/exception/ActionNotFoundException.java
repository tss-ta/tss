package com.netcracker.router.exception;

/**
 * @author Kyrylo Berehovyi
 */

public class ActionNotFoundException extends RuntimeException {
    public ActionNotFoundException() {
        super();
    }

    public ActionNotFoundException(String message) {
        super(message);
    }
}
