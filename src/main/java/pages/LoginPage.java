package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import model.UserProductInfo;
import org.openqa.selenium.By;
import pages.forms.CookieForm;

public class LoginPage extends MainPage {
    private final ITextBox textEmail = getElementFactory()
            .getTextBox(By.xpath("//input[contains(@data-at-selector, 'email') " +
                    "and contains(@data-at-selector, 'Input')]"), "Email");
    private final ITextBox textPassword = getElementFactory()
            .getTextBox(By.xpath("//input[contains(@data-at-selector, 'password') " +
                    "and contains(@data-at-selector, 'Input')]"), "Password");
    private final IButton btnLogin = getElementFactory()
            .getButton(By.xpath("//kl-button[contains(@atselector, 'welcome') " +
                            "and contains(@atselector, 'SignInBtn')]//button"),
                    "Sign In");
    private final CookieForm cookieForm;


    public LoginPage() {
        super(By.xpath("//kl-welcome[contains(@class, 'welcome-main a-flex-column')]"), "Login");
        this.cookieForm = new CookieForm();
    }

    public void logIn(UserProductInfo userProductInfo) {
        if (cookieForm.waitForLoad()) {
            cookieForm.acceptCookies();
        }
        textEmail.clearAndType(userProductInfo.getEmail());
        textPassword.clearAndType(userProductInfo.getPassword());
        btnLogin.click();
    }
}
