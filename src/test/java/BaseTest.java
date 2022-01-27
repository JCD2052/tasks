import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.*;

public abstract class BaseTest {

    @BeforeMethod
    protected void setup() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo("https://my.kaspersky.com/");
    }

    @AfterMethod
    protected void teardown() {
        AqualityServices.getBrowser().quit();
    }
}
