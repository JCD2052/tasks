package org.jcd2052.utils;

import aquality.appium.mobile.actions.SwipeDirection;
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

    public static void swipeToEdgeOfScreen(Point startPoint, SwipeDirection swipeDirection) {
        Point endPoint;
        switch (swipeDirection) {
            case UP:
                endPoint = new Point(startPoint.getX(), getScreenSize().getY());
                break;
            case DOWN:
                endPoint = new Point(startPoint.getX(), 0);
                break;
            case LEFT:
                endPoint = new Point(0, startPoint.getY());
                break;
            case RIGHT:
                endPoint = new Point(getScreenSize().getX(), startPoint.getY());
                break;
            default:
                endPoint = new Point(0, 0);
                break;
        }
        swipe(startPoint, endPoint);
    }
}
