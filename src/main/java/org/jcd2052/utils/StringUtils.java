package org.jcd2052.utils;

import static org.apache.commons.lang3.StringUtils.substringBefore;

public class StringUtils {
    private StringUtils() {

    }

    public static String substringBeforeOrReturnOrigin(String string, String delimiter) {
        String result = substringBefore(string, delimiter);
        return result.isEmpty() ? string : result;
    }
}