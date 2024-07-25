package com.justin4u.util;

import java.util.Random;

public class IntegerUtils {

    public static int randomPositiveInt() {
        return new Random().nextInt() & Integer.MAX_VALUE;
    }
}
