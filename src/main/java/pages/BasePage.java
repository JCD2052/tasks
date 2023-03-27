package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import pages.menus.FooterMenu;
import pages.menus.HeaderMenu;

public abstract class BasePage extends Form {
    private final FooterMenu footerMenu;
    private final HeaderMenu headerMenu;

    protected BasePage(By locator, String name) {
        super(locator, name);
        this.footerMenu = new FooterMenu();
        this.headerMenu = new HeaderMenu();
    }

    public boolean waitForLoad() {
        return this.state().waitForDisplayed();
    }

    public FooterMenu getFooterMenu() {
        return footerMenu;
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }
}