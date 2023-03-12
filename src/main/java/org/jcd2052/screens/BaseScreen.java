package org.jcd2052.screens;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

abstract class BaseScreen extends Screen {
    protected BaseScreen(By locator, String name) {
        super(locator, name);
    }

    public boolean waitForLoading() {
        return this.state().waitForDisplayed();
    }
}
