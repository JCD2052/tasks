import email.BoxName;
import email.EmailService;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DownloadsPage;
import pages.LicensePage;
import pages.LoginPage;

import java.util.List;

public class EmailProductTest extends BaseTest {
    @Test
    @Parameters({"email", "password", "productName", "osName"})
    public void emailProductTest(String email, String password, String productName, String osName) {
        User user = User.builder()
                .email(email)
                .password(password)
                .productName(productName)
                .os(osName)
                .build();

        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.waitForLoad(), "Login Page hasn't been loaded.");

        loginPage.logIn(user);
        LicensePage licensePage = new LicensePage();
        Assert.assertTrue(licensePage.waitForLoad(), "License Page hasn't been loaded.");

        DownloadsPage downloadsPage = licensePage.getHeaderForm().goToDownloads();
        Assert.assertTrue(downloadsPage.waitForLoad(), "Downloads Page hasn't been loaded.");

        downloadsPage.selectOs(user);
        downloadsPage.clickEmailInfoAboutProduct(user);
        Assert.assertTrue(downloadsPage.checkIfOriginEmailIsCorrect(user), "Origin email is not correct.");

        downloadsPage.sendEmailWithProductInfo(user);
        List<String> emails = new EmailService().getTextBodiesFromEmail(user, BoxName.INBOX);
        Assert.assertTrue(EmailService.checkEmailForContent(emails, user), "Email with info hasn't been found");
    }
}
