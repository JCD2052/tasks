package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static String extractUrl(String text) {
        List<String> containedUrls = new ArrayList<>();
        Pattern pattern = Pattern.compile(StringRegex.FIND_URL, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);
        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }
        return containedUrls
                .stream()
                .findFirst()
                .orElse("");
    }

    public static String substringTextBeforeValue(String textToSplit, String splitter) {
        return textToSplit.substring(0, textToSplit.indexOf(splitter) + splitter.length());
    }

    public static int stringToInt(String string) {
        return Integer.parseInt(string);
    }
}
