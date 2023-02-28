package org.example.utils;

import aquality.appium.mobile.application.AqualityServices;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ControlUtils {
    private ControlUtils() {
    }

    public static void pressKeyOnKeyboard(AndroidKey key) {
        ((AndroidDriver) AqualityServices.getApplication().getDriver()).pressKey(new KeyEvent(key));
    }

    //swipeToReload (from some element to down of app)
}
