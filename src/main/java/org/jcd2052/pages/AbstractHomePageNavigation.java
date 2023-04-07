package org.jcd2052.pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public abstract class AbstractHomePageNavigation extends Form {
    protected AbstractHomePageNavigation() {
        super(By.id("header"), "Top navigation");
    }

    public abstract void selectTab(HeaderTab tab);
}