package pages.forms;

import org.openqa.selenium.By;
import pages.BasePage;

public class QuickLogin extends BasePage {

    public QuickLogin() {
        super(By.id("quick_login"), "Quick Login");
    }
}
