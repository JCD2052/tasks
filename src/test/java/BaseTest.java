import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import testdata.TestDataReader;

import java.io.IOException;

public abstract class BaseTest {
    protected Browser browser;

    @BeforeMethod
    protected void setup() throws IOException {
        browser = AqualityServices.getBrowser();
        browser.goTo(TestDataReader.read("BASE_URL"));
    }

    @AfterMethod
    protected void teardown() {
        browser.quit();
    }
}
