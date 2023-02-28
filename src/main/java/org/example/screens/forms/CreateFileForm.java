package org.example.screens.forms;

import aquality.appium.mobile.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

public class CreateFileForm extends BaseForm {
    private final ITextBox filenameTextInput = getElementFactory()
            .getTextBox(By.id("com.nextcloud.client:id/filename"), "Text filename input");

    public CreateFileForm() {
        super("Create file form");
    }

    public void createFile(String fileName) {
        filenameTextInput.clearAndType(fileName);
        rightChoice.click();
    }
}
