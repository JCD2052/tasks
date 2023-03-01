package org.example.screens;

import aquality.appium.mobile.elements.interfaces.ILabel;
import org.example.utils.StringUtils;
import org.openqa.selenium.By;

import java.time.Duration;

public class FileOpeningScreen extends BaseScreen {

    private final ILabel filenameLabel = getElementFactory()
            .getLabel(By.id("com.nextcloud.client:id/filename"), "Filename Label");
    private final ILabel progressBar = getElementFactory()
            .getLabel(By.id("com.nextcloud.client:id/progressBar2"), "Progress Bar");

    public FileOpeningScreen() {
        super(By.id("com.nextcloud.client:id/thumbnail"), "File loading screen");
    }

    public String getFilename() {
        return StringUtils.extractFilenameWithoutExtension(filenameLabel.getText());
    }

    public boolean waitUntilSpinnerDisappears(int timeout) {
        return progressBar.state().waitForNotExist(Duration.ofSeconds(timeout));
    }
}
