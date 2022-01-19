package utils;

public class StringUtils {
    public static String removeSpaces(String string) {
        return string.replace(" ", "");
    }

    public static int stringToNumber(String string) {
        return Integer.parseInt(string);
    }
}