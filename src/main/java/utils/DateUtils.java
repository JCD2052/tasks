package utils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DateUtils {
    public static Date getRandomDate() {
        return new Date((long) ThreadLocalRandom.current().nextInt() * new Random().nextInt());
    }
}
