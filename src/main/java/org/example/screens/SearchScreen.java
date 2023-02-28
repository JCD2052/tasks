package org.example.screens;

import aquality.appium.mobile.elements.interfaces.IButton;
import org.openqa.selenium.By;

public class SearchScreen extends BaseScreen {
    private static final String SEARCH_CONTENT_LOCATOR =
            "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.nextcloud.client:id/list_root']//android.widget.TextView[contains(@text, '%s')]";

    public SearchScreen() {
        super(By.id("com.nextcloud.client:id/search_close_btn"), "Search Screen");
    }

    public boolean isFileSearched(String filename) {
        String locator = String.format(SEARCH_CONTENT_LOCATOR, filename);
        IButton searchElement = getElementFactory()
                .getButton(By.xpath(locator), "Search element");
        return searchElement.state().waitForExist();
    }
}
