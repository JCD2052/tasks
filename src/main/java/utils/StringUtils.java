package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {

    public static String getRandomString(int length) {
        return RandomStringUtils.random(length, true, true);
    }

    public static int stringToIntWithRemoving(String str) {
        return Integer.parseInt(str.replaceAll(RegexUtils.ONLY_NUMBERS, ""));
    }

    public static int stringToInt(String string) {
        return Integer.parseInt(string);
    }
}