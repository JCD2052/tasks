package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public abstract class BasePage extends Form {
    protected BasePage(By locator, String name) {
        super(locator, name);
    }

    public boolean waitForLoad() {
        return this.state().waitForDisplayed();
    }
}