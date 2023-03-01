package org.example.utils;

import aquality.appium.mobile.application.AqualityServices;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.Dimension;


public class ControlUtils {
    private ControlUtils() {
    }

    public static void pressKeyOnKeyboard(AndroidKey key) {
        ((AndroidDriver) AqualityServices.getApplication().getDriver()).pressKey(new KeyEvent(key));
    }

    public static Dimension getScreenSize() {
        return AqualityServices.getApplication().getDriver().manage().window().getSize();
    }
}
