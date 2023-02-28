package org.example.screens;

import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public class TextEditorScreen extends Screen {
    private final ITextBox editorTextBox = getElementFactory()
            .getTextBox(By.className("android.widget.EditText"), "Editor Text box");

    private final IButton closeEditorButton = getElementFactory()
            .getButton(By.xpath("//android.view.View[contains(@resource-id, 'menu-bar')]/descendant::android.widget.Button[last()]"),
                    "Close editor button");

    public TextEditorScreen() {
        super(By.xpath("//android.view.View[@resource-id = 'direct-editor']"),
                "Text Editor Screen");
    }

    public void enterText(String text) {
        editorTextBox.click();
        editorTextBox.sendKeys(text);
    }

    public String getTextFromEditor() {
        return editorTextBox.getText();
    }

    public void closeEditor() {
        closeEditorButton.click();
    }
}
