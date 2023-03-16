package pages;

import org.openqa.selenium.By;
import pages.forms.Header;
import pages.forms.LeftMenu;

public abstract class MainPage extends BasePage {
    private final Header header;
    private final LeftMenu leftMenu;

    protected MainPage(By locator, String name) {
        super(locator, name);
        this.header = new Header();
        this.leftMenu = new LeftMenu();
    }

    public Header goToHeader() {
        return header;
    }

    public LeftMenu goToLeftMenu() {
        return leftMenu;
    }
}
