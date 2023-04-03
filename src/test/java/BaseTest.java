
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
//import config.PropertyConfig;
//import config.TestDataConfig;
import org.jcd2052.config.PropertyConfig;
import org.jcd2052.config.TestDataConfig;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected Browser browser;
    protected final Screen screen = new Screen();
    protected final TestDataConfig testData = PropertyConfig.TEST_DATA_CONFIG.getConfig();

    @BeforeMethod
    protected void setup() {
        browser = AqualityServices.getBrowser();
        browser.goTo(testData.getBaseUrl());
        browser.maximize();
    }

    @AfterMethod
    protected void teardown() {
        browser.quit();
    }
}
