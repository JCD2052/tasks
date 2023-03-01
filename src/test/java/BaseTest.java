import aquality.appium.mobile.application.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    @BeforeMethod
    protected void setup() {
        AqualityServices.getApplication();
    }

    @AfterMethod
    protected void teardown() {
        AqualityServices.getApplication().quit();
    }
}
