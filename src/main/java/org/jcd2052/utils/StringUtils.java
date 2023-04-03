package org.jcd2052.utils;

import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.substringBetween;


public class StringUtils {

    private StringUtils() {

    }

    public static String substringBetweenOrReturnOriginal(String string, String startTag,
                                                          String endTag) {
        return substringBetween(string, startTag, endTag);
    }

    public static double getDoubleFromString(String string) {
        return Double.parseDouble(string.replaceAll("[^0-9.]", ""));
    }

    public static int sumOfIntegersFromStrings(String... strings) {
        return Arrays.stream(strings)
                .mapToInt(StringUtils::stringToInt)
                .sum();
    }

    public static int stringToInt(String string) {
        return Integer.parseInt(string);
    }
}
