package org.jcd2052.pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class GreetForm extends Form {
    private final IButton closePopupButton = getElementFactory().getButton(By.xpath(
                    "//a[contains(@class, 'close') and(contains(@class, 'title'))]"),
            "Close popup button");

    public GreetForm() {
        super(By.id("popin-content"), "Greet Form");
    }

    public void closePopupButton() {
        closePopupButton.click();
    }

    public boolean isFormClosed() {
        return this.state().waitForNotExist();
    }
}
