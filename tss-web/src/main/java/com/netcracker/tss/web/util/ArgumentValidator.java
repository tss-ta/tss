package com.netcracker.tss.web.util;

/**
 * Created by Kyrylo Berehovyi on 29/04/2015.
 */

public class ArgumentValidator {

    public static void validateArgumentOnNull(String argumentName, Object argument) {
        if (argument == null) {
            if (argumentName == null) {
                throw new IllegalArgumentException("Argument should not be null.");
            } else {
                throw new IllegalArgumentException("Argument " + argumentName + " should not be null.");
            }
        }
    }

    public static void validateArgumentOnNull(Object argument) {
        if (argument == null) {
            throw new IllegalArgumentException("Argument should not be null.");
        }
    }
}
