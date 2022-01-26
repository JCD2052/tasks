package pages;

import org.openqa.selenium.By;
import pages.forms.HeaderForm;

public abstract class MainPage extends BasePage {

    protected MainPage(By locator, String name) {
        super(locator, name);
    }

    public HeaderForm getHeaderForm() {
        return new HeaderForm();
    }
}
