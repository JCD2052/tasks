package org.jcd2052.screens;

import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IElement;
import org.jcd2052.models.ProjectInfo;
import org.jcd2052.utils.StringUtils;
import org.openqa.selenium.By;

abstract class BaseProjectInfoScreen extends BaseScreen {
    private final String projectNameLocator;
    private final String projectDeadlineLocator;
    private final String projectPercentLocator;

    protected BaseProjectInfoScreen(By locator, String name, String projectNameLocator,
                                    String projectDeadlineLocator, String projectPercentLocator) {
        super(locator, name);
        this.projectNameLocator = projectNameLocator;
        this.projectDeadlineLocator = projectDeadlineLocator;
        this.projectPercentLocator = projectPercentLocator;
    }

    protected ProjectInfo getProjectInfoFromElementCard(IElement element) {
        String percent = element.findChildElement(By.xpath(projectPercentLocator),
                ElementType.LABEL).getText();
        String deadline = element.findChildElement(By.xpath(projectDeadlineLocator),
                ElementType.LABEL).getText();
        String projectName = StringUtils.substringBeforeOrReturnOrigin(
                element.findChildElement(By.xpath(projectNameLocator), ElementType.LABEL)
                        .getText(), ".");
        return new ProjectInfo(deadline, percent, projectName);
    }
}
