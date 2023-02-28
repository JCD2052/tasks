package org.example.screens;

import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.Screen;
import org.example.screens.forms.AddUploadFileForm;
import org.openqa.selenium.By;

import static io.appium.java_client.AppiumBy.accessibilityId;

public class MainScreen extends Screen {
    //mb can add custom element.
    private final ILabel spinner = getElementFactory()
            .getLabel(By.xpath("//android.widget.LinearLayout[@content-desc='Listed layout']//android.widget.ImageView"),
                    "Loading spinner");
    private final IButton addFileButton = getElementFactory()
            .getButton(ADD_OR_UPLOAD_LOCATOR, "Add or upload button");
    private final ITextBox searchTextBox = getElementFactory()
            .getTextBox(By.id("com.nextcloud.client:id/search_text"), "Search Text box");
    private final AddUploadFileForm addUploadFileForm;
    private static final By ADD_OR_UPLOAD_LOCATOR = accessibilityId("Add or upload");
    private static final String FILE_BASE_LOCATOR_TEMPLATE = "//android.widget.LinearLayout[@resource-id = 'com.nextcloud.client:id/ListItemLayout']//android.widget.TextView[contains(@text, '%s')]";
    private static final String CONTEXT_MENU_BASE_LOCATOR = "//parent::android.widget.LinearLayout/parent::android.widget.LinearLayout//android.widget.ImageView[@resource-id = 'com.nextcloud.client:id/overflow_menu']";

    public MainScreen() {
        super(ADD_OR_UPLOAD_LOCATOR, "Main Page");
        this.addUploadFileForm = new AddUploadFileForm();
    }

    public void searchForContent(String searchValue) {
        searchTextBox.click();
        searchTextBox.clearAndType(searchValue);
    }

    public boolean waitUntilSpinnerGone() {
        return spinner.state().waitForNotExist();
    }

    public boolean isFileExits(String filename) {
        String locator = String.format(FILE_BASE_LOCATOR_TEMPLATE, filename);
        return getElementFactory().getButton(By.xpath(locator), "Filename element")
                .state().isExist();
    }

    public void openContextMenu(String filename) {
        String locator = String.format(FILE_BASE_LOCATOR_TEMPLATE, filename) + CONTEXT_MENU_BASE_LOCATOR;
        IButton contextButton = getElementFactory()
                .getButton(By.xpath(locator), "Filename context menu button");
        contextButton.click();
    }

    public void clickAddUploadDocument() {
        addFileButton.click();
    }

    public AddUploadFileForm getAddUploadFileForm() {
        return addUploadFileForm;
    }
}
