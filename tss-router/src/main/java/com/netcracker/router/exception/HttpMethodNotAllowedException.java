package com.netcracker.router.exception;

/**
 * @author Kyrylo Berehovyi
 */

public class HttpMethodNotAllowedException extends RuntimeException {
    public HttpMethodNotAllowedException() {
        super();
    }

    public HttpMethodNotAllowedException(String message) {
        super(message);
    }
}
