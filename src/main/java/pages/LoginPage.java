package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

//TODO ALL LOCATORS HAVE @data-at-selector TAG. MB NEED CREATE SEPARATE CLASS FOR STRING FORMAT AND USE THIS LOCATOR.

public class LoginPage extends BasePage {
    private final static String BASE_INPUT_FIELDS_LOCATOR = "//input[contains(@data-at-selector, '%s') " +
            "and contains(@data-at-selector, 'Input')]";
    private final ITextBox textEmail = getElementFactory()
            .getTextBox(By.xpath(String.format(BASE_INPUT_FIELDS_LOCATOR, "email")), "Email");
    private final ITextBox textPassword = getElementFactory()
            .getTextBox(By.xpath(String.format(BASE_INPUT_FIELDS_LOCATOR, "password")), "Password");
    private final IButton btnLogin = getElementFactory()
            .getButton(By.xpath("//kl-button[contains(@atselector, 'welcome') " +
                            "and contains(@atselector, 'SignInBtn')]//button"),
                    "Sign In");

    public LoginPage() {
        super(By.xpath("//kl-welcome[contains(@class, 'welcome-main a-flex-column')]"), "Login");
    }

    public void logIn(String email, String password) {
        textEmail.clearAndType(email);
        textPassword.clearAndType(password);
        btnLogin.click();
    }
}
