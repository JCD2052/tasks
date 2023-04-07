package org.jcd2052.pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public abstract class AbstractHomePage extends Form {
    private final AbstractHomePageNavigation navigation;

    protected AbstractHomePage(AbstractHomePageNavigation navigation) {
        super(By.xpath("//div[contains(@class, 'main-container')]"),
                "Home Page");
        this.navigation = navigation;
    }

    public AbstractHomePageNavigation getNavigation() {
        return navigation;
    }
}
