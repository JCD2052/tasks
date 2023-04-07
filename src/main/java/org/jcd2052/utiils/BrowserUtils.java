package org.jcd2052.utiils;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.Dimension;

public class BrowserUtils {
    private BrowserUtils() {

    }

    public static Dimension getBrowserSize() {
        return AqualityServices.getBrowser().getDriver().manage().window().getSize();
    }
}
