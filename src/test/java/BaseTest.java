import aquality.appium.mobile.application.AqualityServices;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    @BeforeTest
    public void setup() {
        AqualityServices.getApplication();
    }

    @AfterTest
    public void teardown() {
        AqualityServices.getApplication().quit();
    }
}
