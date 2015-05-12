package com.netcracker.util;

import java.util.Random;

/**
 * @author Illia Rudenko
 */

public class TokenGenerator {

    public static Integer generate() {
        return new Random().nextInt(Integer.MAX_VALUE) + 1;
    }
}
