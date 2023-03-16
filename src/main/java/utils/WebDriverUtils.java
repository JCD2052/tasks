package utils;

import aquality.selenium.browser.AqualityServices;

public class WebDriverUtils {
    public static String getCurrentUrl() {
        return AqualityServices.getBrowser().getCurrentUrl();
    }

    public static void goToPage(String url) {
        AqualityServices.getBrowser().goTo(url);
    }
}
