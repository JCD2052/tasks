import aquality.selenium.browser.AqualityServices;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.FeedPage;
import pages.LogIn;
import pages.MyProfile;
import pages.forms.LeftMenuItems;
import testdata.TestDataReader;
import utils.WebDriverUtils;

public abstract class BaseTest {
    protected MyProfile myProfilePage;

    @BeforeMethod
    protected void setup() {
        WebDriverUtils.goToPage(new ConfigReader().get("base_url"));
        LogIn logInPage = new LogIn();
        Assert.assertTrue(logInPage.isPageLoaded(),
                "Login Page is not opened");

        logInPage.logIn(new TestDataReader().get("username_user1"),
                new TestDataReader().get("password_user1"));
        FeedPage feedPage = new FeedPage();
        Assert.assertTrue(feedPage.isPageLoaded(),
                "Feed Page is not opened.");

        feedPage.goToLeftMenu().selectMenuItem(LeftMenuItems.MY_PROFILE);
        myProfilePage = new MyProfile();
        Assert.assertTrue(myProfilePage.isPageLoaded(),
                "My Page is not opened.");
    }

    @AfterMethod
    protected void teardown() {
        AqualityServices.getBrowser().quit();
    }
}