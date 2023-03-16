package pages.forms;

import org.openqa.selenium.By;
import pages.BasePage;

public class LeftMenu extends BasePage {
    private static final String LEFT_MENU_LOCATOR = "//nav[@class='side_bar_nav']";
    private final QuickLogin quickLogin;

    public LeftMenu() {
        super(By.xpath(LEFT_MENU_LOCATOR), "Left Menu");
        this.quickLogin = new QuickLogin();
    }

    public void selectMenuItem(LeftMenuItems item) {
        item.getButton().click();
    }

    public boolean isLogIn(){
        return !quickLogin.state().isDisplayed();
    }
}
