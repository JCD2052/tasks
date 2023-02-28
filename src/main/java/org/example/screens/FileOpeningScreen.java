package org.example.screens;

import aquality.appium.mobile.elements.interfaces.ILabel;
import org.openqa.selenium.By;

public class FileOpeningScreen extends BaseScreen {

    private final ILabel filenameLabel = getElementFactory()
            .getLabel(By.id("com.nextcloud.client:id/filename"), "Filename Label");
    private final ILabel progressBar = getElementFactory()
            .getLabel(By.id("com.nextcloud.client:id/progressBar2"), "Progress Bar");

    public FileOpeningScreen() {
        super(By.id("com.nextcloud.client:id/thumbnail"), "File loading screen");
    }

    public boolean isFilenameMatched(String filename) {
        return filenameLabel.getText().contains(filename);
    }

    public boolean waitUntilSpinnerDisappears() {
        return progressBar.state().waitForNotExist();
    }
}
