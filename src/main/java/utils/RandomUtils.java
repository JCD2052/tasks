package utils;

import java.util.Random;

public class RandomUtils {
    public static int getRandomIntInRange(int min, int max) {
        return new Random().nextInt(Math.abs(max - min)) + min;
    }
}