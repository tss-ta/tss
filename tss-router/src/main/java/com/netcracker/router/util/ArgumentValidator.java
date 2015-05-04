package com.netcracker.router.util;

/**
 * @author Kyrylo Berehovyi
 */

public class ArgumentValidator {
    private static final String MESSAGE_PREFIX = "Argument '";
    private static final String MESSAGE_SUFIX = "' must not be null, but he is null.";

    public static void validateOnNull(Object argument, String argumentName) {
        if (argument == null) {
            throw new IllegalArgumentException(MESSAGE_PREFIX + argumentName + MESSAGE_SUFIX);
        }
    }
}
