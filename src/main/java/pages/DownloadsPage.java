package pages;

import model.UserProductInfo;
import org.openqa.selenium.By;
import pages.forms.SendEmailForm;
import utils.XpathRegex;

public class DownloadsPage extends MainPage {
    private final static String BASE_LOCATOR = "//div[contains(@data-at-selector, '%s')]//" +
            "text()[contains(" + XpathRegex.TO_LOWER_CASE + ", '%s')]" +
            "//parent::div";
    private final static String SEND_EMAIL_BUTTON_LOCATOR =
            "//ancestor::div[contains(@data-at-selector, 'downloadApplicationCard')]" +
                    "//button[contains(@data-at-selector, 'Email')]";
    private final SendEmailForm sendEmailForm;

    public DownloadsPage() {
        super(By.id("trialSoft"), "Downloads");
        this.sendEmailForm = new SendEmailForm();
    }

    public void selectOs(UserProductInfo userProductInfo) {
        getElementFactory()
                .getButton(By.xpath(String.format(BASE_LOCATOR, "os", userProductInfo.getOs().toLowerCase())),
                        "Choose OS")
                .click();
    }

    public void clickEmailInfoAboutProduct(UserProductInfo userProductInfo) {
        String productLocator = String.format(BASE_LOCATOR, "serviceName",
                userProductInfo.getProductName().toLowerCase());
        getElementFactory()
                .getButton(By.xpath(productLocator + SEND_EMAIL_BUTTON_LOCATOR),
                        "Product Info")
                .click();
    }

    public String getOriginEmail() {
        return sendEmailForm.getTextFromEmailField();
    }

    public void sendEmailWithProductInfo(UserProductInfo userProductInfo) {
        sendEmailForm.enterEmail(userProductInfo.getEmail());
        sendEmailForm.sendEmail();
    }
}
