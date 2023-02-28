package org.example.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

    private RandomUtils() {
    }

    public static String getRandomString(int length) {
        return RandomStringUtils.random(length, true, true);
    }
}
