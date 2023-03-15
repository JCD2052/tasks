package utils;

import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;

public class StringUtils {
    private StringUtils() {

    }

    public static String removeSpaces(String string) {
        return string.replace(" ", "");
    }

    public static String substringBeforeOrReturnOrigin(String string, String delimiter) {
        String result = substringBefore(string, delimiter);
        return result.isEmpty() ? string : result;
    }

    public static String substringAfterOrReturnOrigin(String string, String delimiter) {
        String result = substringAfter(string, delimiter);
        return result.isEmpty() ? string : result;
    }

    public static int stringToIntWithRemoving(String str) {
        return stringToNumber(str.replaceAll("\\D+", ""));
    }

    public static int stringToNumber(String string) {
        return Integer.parseInt(string);
    }
}