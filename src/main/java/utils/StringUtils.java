package utils;

import static org.apache.commons.lang3.StringUtils.substringAfter;

public class StringUtils {
    private StringUtils() {

    }

    public static String substringAfterOrReturnOrigin(String string, String delimiter) {
        String result = substringAfter(string, delimiter);
        return result.isEmpty() ? string : result;
    }

    public static int extractNumbersFromStringAndReturnInt(String str) {
        return stringToNumber(str.replaceAll("\\D+", ""));
    }

    public static int stringToNumber(String string) {
        return Integer.parseInt(string);
    }
}