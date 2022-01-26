import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import pages.LoginPage;

public class Demo {
    public static void main(String[] args) {
        Browser browser = AqualityServices.getBrowser();
        try {
            browser.goTo("https://my.kaspersky.com/");
            LoginPage loginPage = new LoginPage();
            loginPage.waitForLoad();
            loginPage.logIn("", "");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            browser.quit();
        }
    }
}
