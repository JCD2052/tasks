package org.jcd2052.utils;

import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();

    private RandomUtils() {
    }

    public static <T> T getRandomElementFromList(List<T> list) {
        int count = list.size();
        return list.get(getRandomIntInRange(0, count));
    }

    public static int getRandomIntInRange(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }
}