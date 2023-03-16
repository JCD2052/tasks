package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

public class LogIn extends BasePage {
    private final static String LOGIN_BASE_LOCATOR = "//div[contains(@class, 'login') and contains(@class, 'index')]";
    private final ITextBox txbUsername = getElementFactory()
            .getTextBox(By.xpath(LOGIN_BASE_LOCATOR + "//input[contains(@name, 'email')]"),
                    "Username");
    private final ITextBox txbPassword = getElementFactory()
            .getTextBox(By.xpath(LOGIN_BASE_LOCATOR + "//input[contains(@name, 'pass')]"),
                    "Password");
    private final IButton btnLogin = getElementFactory()
            .getButton(By.xpath(LOGIN_BASE_LOCATOR + "//button[contains(@id, 'login') and contains(@id, 'index')]"),
                    "Login");

    public LogIn() {
        super(By.xpath(LOGIN_BASE_LOCATOR), "LogIn");
    }

    public void logIn(String username, String password) {
        txbUsername.clearAndType(username);
        txbPassword.clearAndType(password);
        btnLogin.click();
    }
}
