package pages;

import aquality.selenium.elements.interfaces.IButton;
import model.User;
import org.openqa.selenium.By;
import pages.forms.SendEmailForm;
import utils.XpathRegex;

public class DownloadsPage extends MainPage {
    private final static String BASE_OS_CHOOSE_LOCATOR = "//div[contains(@data-at-selector, 'osName')]//" +
            "text()[contains(" + XpathRegex.TO_LOWER_CASE + ", '%s')]" +
            "//parent::div";
    private final IButton btnNextPage = getElementFactory()
            .getButton(By.xpath("//div[contains(@data-at-selector, 'next')]//div"), "Next Page");
    private final static String PRODUCT_NAME_LOCATOR =
            "//div[contains(@data-at-selector , 'serviceName')]//" +
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

    public void selectOs(User user) {
        getElementFactory()
                .getButton(By.xpath(String.format(BASE_OS_CHOOSE_LOCATOR, user.getOs().toLowerCase())),
                        "Choose OS")
                .click();
    }

    public void clickEmailInfoAboutProduct(User user) {
        String productLocator = String.format(PRODUCT_NAME_LOCATOR, user.getProductName().toLowerCase());
//        ILabel productInfo = getElementFactory()
//                .getLabel(By.xpath(productLocator),
//                        "Product Name");
//        while (!productInfo.state().isDisplayed()) {
//            btnNextPage.click();
//        }
        getElementFactory()
                .getButton(By.xpath(productLocator + SEND_EMAIL_BUTTON_LOCATOR),
                        "Product Info")
                .click();
    }

    public boolean checkIfOriginEmailIsCorrect(User user) {
        return sendEmailForm.getTextFromEmailField().equals(user.getEmail());
    }

    public void sendEmailWithProductInfo(User user) {
        sendEmailForm.enterEmail(user.getEmail());
//        sendEmailForm.sendEmail();
    }


}
