package org.example.screens;

import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public class FileCreationScreen extends Screen {

    private final ILabel filenameLabel = getElementFactory()
            .getLabel(By.id("com.nextcloud.client:id/filename"), "Filename Label");
    private final ILabel progressBar = getElementFactory()
            .getLabel(By.id("com.nextcloud.client:id/progressBar2"), "Progress Bar");

    public FileCreationScreen() {
        super(By.id("com.nextcloud.client:id/thumbnail"), "File loading screen");
    }

    public boolean isFilenameMatched(String filename) {
        return filenameLabel.getText().equalsIgnoreCase(filename);      // need to match without file extension
    }

    public boolean waitUntilSpinnerDisappears() {
        return progressBar.state().waitForNotExist();
    }
}
