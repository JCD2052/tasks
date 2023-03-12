package org.jcd2052.utils;

import aquality.appium.mobile.application.AqualityServices;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public class ScreenUtils {

    private ScreenUtils() {
    }

    public static Point getScreenSize() {
        Dimension screenDimension = AqualityServices.getApplication().getDriver()
                .manage().window().getSize();
        return new Point(screenDimension.getWidth() - 1, screenDimension.getHeight() - 1);
    }

    public static void swipe(Point startPoint, Point endPoint) {
        AqualityServices.getTouchActions().swipe(startPoint, endPoint);
    }
}
