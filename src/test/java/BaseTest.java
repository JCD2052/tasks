import aquality.appium.mobile.application.AqualityServices;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {
    @BeforeTest
    protected void setup() {
        AqualityServices.getApplication();
    }

    @AfterTest
    protected void teardown() {
        AqualityServices.getApplication().quit();
    }
}
