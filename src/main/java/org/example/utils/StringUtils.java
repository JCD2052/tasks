package org.example.utils;

public class StringUtils {
    private StringUtils() {

    }

    public static String extractFilenameWithoutExtension(String string) {
        String result = org.apache.commons.lang3.StringUtils.substringBefore(string, ".");
        return result.isEmpty() ? string : result;
    }
}
