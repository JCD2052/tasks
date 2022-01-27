package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;
import pages.BasePage;

public class CookieForm extends BasePage {
    private final IButton bntAcceptCookies = getElementFactory()
            .getButton(By.id("CybotCookiebotDialogBodyLevelButtonAccept"), "Accept Cookies");

    public CookieForm() {
        super(By.id("CybotCookiebotDialog"), "Cookie Form");
    }

    public void acceptCookies() {
        bntAcceptCookies.click();
    }
}