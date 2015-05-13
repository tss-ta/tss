package com.netcracker.router.exception;

/**
 * @author Kyrylo Berehovyi
 */
public class RouterActionInvocationAxception extends RuntimeException {
    public RouterActionInvocationAxception() {
    }

    public RouterActionInvocationAxception(String message) {
        super(message);
    }
}
