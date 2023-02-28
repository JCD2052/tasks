package org.example.screens.forms;

import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public class CreateFileForm extends Screen {
    private final IButton createButton = getElementFactory().getButton(By.id("android:id/button1"),
            "Cancel Button");
    private final ITextBox filenameTextInput = getElementFactory()
            .getTextBox(By.id("com.nextcloud.client:id/filename"), "Text filename input");
    private final ILabel errorLabel = getElementFactory()
            .getLabel(By.id("com.nextcloud.client:id/textinput_error"), "Error label");

    public CreateFileForm() {
        super(By.id("com.nextcloud.client:id/alertTitle"), "Create file form");
    }

    public void createFile(String fileName) {
        filenameTextInput.clearAndType(fileName);
        if (!errorLabel.state().isExist()) {
            createButton.click();
        } else {
            throw new IllegalStateException(String.format("Filename with %s exits", fileName));
        }
    }
}
