package com.netcracker.router.exception;

/**
 * @author Kyrylo Berehovyi
 */

public class RouterInitializationException extends RuntimeException {
    public RouterInitializationException() {}

    public RouterInitializationException(String message) {
        super(message);
    }
}
