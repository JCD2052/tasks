import aquality.selenium.browser.AqualityServices;
import email.BoxName;
import email.EmailService;
import model.UserProductInfo;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DownloadsPage;
import pages.LicensePage;
import pages.LoginPage;

public class EmailProductTest extends BaseTest {
    @Test
    @Parameters({"email", "password", "productName", "osName"})
    public void sendEmailAboutProductTest(String email, String password, String productName, String osName) {
        UserProductInfo userProductInfo = UserProductInfo.builder()
                .email(email)
                .password(password)
                .productName(productName)
                .os(osName)
                .build();
        Assert.assertNotNull(userProductInfo, "No info about Product.");

        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.waitForLoad(), "Login Page hasn't been loaded.");

        loginPage.logIn(userProductInfo);
        LicensePage licensePage = new LicensePage();
        Assert.assertTrue(licensePage.waitForLoad(), "License Page hasn't been loaded.");

        DownloadsPage downloadsPage = licensePage.getHeaderForm().goToDownloads();
        Assert.assertTrue(downloadsPage.waitForLoad(), "Downloads Page hasn't been loaded.");

        downloadsPage.selectOs(userProductInfo);
        downloadsPage.clickEmailInfoAboutProduct(userProductInfo);
        Assert.assertEquals(downloadsPage.getOriginEmail(),
                "Origin email is not correct.");

        downloadsPage.sendEmailWithProductInfo(userProductInfo);

        Assert.assertTrue(AqualityServices.getConditionalWait().waitFor(() ->
                        EmailService.checkEmailsForContent(
                                new EmailService().getTextBodiesFromEmail(BoxName.INBOX), userProductInfo),
                "Email with info hasn't been found"));
    }
}