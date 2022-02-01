package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public enum LeftMenuItems {
    MY_PROFILE("//li[contains(@id, 'pr')]");
    private final String route;

    LeftMenuItems(String route) {
        this.route = route;
    }

    public IButton getButton() {
        return getElementFactory()
                .getButton(By.xpath(route), this.name());
    }
}
