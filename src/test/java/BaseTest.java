import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import testdata.TestDataReader;

public abstract class BaseTest {

    @BeforeClass
    protected void setup() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(TestDataReader.getProperty("BASE_URL"));
    }

    @AfterClass
    protected void teardown() {
        AqualityServices.getBrowser().quit();
    }
}
