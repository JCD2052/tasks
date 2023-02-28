package org.example.screens.forms;

import aquality.appium.mobile.elements.interfaces.IButton;
import org.example.screens.BaseScreen;
import org.openqa.selenium.By;

public abstract class BaseForm extends BaseScreen {
    protected final IButton rightChoice = getElementFactory().getButton(By.id("android:id/button1"),
            "Right choice");

    protected BaseForm(String name) {
        super(By.id("com.nextcloud.client:id/action_bar_root"), name);
    }
}
