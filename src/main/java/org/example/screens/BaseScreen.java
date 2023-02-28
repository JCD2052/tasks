package org.example.screens;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class BaseScreen extends Screen {
    protected BaseScreen(By locator, String name) {
        super(locator, name);
    }

    public boolean waitForPageLoading() {
        return this.state().waitForDisplayed();
    }
}
