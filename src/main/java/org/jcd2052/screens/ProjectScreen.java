package org.jcd2052.screens;

import aquality.appium.mobile.elements.interfaces.ILabel;
import org.openqa.selenium.By;

public class ProjectScreen extends BaseScreen {
    private final ILabel deadlineLabel = getElementFactory()
            .getLabel(By.xpath("//android.widget.TextView[@resource-id ='com.kickstarter.kickstarter:id/deadline_countdown_text_view']"),
                    "Deadline label");

    public ProjectScreen() {
        super(By.xpath("//android.widget.ImageView[@resource-id='com.kickstarter.kickstarter:id/video_project_photo']"),
                "Project Screen");
    }

    public String getProjectDeadline() {
        return deadlineLabel.getText();
    }
}
