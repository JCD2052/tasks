package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;
import pages.BasePage;

public class SendEmailForm extends BasePage {
    private final IButton btnSendEmail = getElementFactory()
            .getButton(By.xpath("//button[contains(@data-at-selector, 'installer')]"), "Send Email");
    private final ITextBox txtEmail = getElementFactory()
            .getTextBox(By.xpath("//input[contains(@data-at-selector, 'email')]"), "Email");

    public SendEmailForm() {
        super(By.xpath("//div[contains(@class, 'modal') and contains(@class, 'body')]"), "Send Email Form");
    }

    public String getTextFromEmailField() {
        return txtEmail.getValue();
    }

    public void enterEmail(String email) {
        txtEmail.clearAndType(email);
    }

    public void sendEmail() {
        btnSendEmail.click();
    }
}
