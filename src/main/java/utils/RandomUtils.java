package utils;

import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();

    private RandomUtils() {

    }

    public static int getRandomIntInRange(int min, int max) {
        return RANDOM.nextInt(Math.abs(max - min)) + min;
    }
}