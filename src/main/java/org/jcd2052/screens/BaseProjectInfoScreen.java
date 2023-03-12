package org.jcd2052.screens;

import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IElement;
import org.jcd2052.models.ProjectInfo;
import org.jcd2052.utils.StringUtils;
import org.openqa.selenium.By;

abstract class BaseProjectInfoScreen extends BaseScreen {
    protected BaseProjectInfoScreen(By locator, String name) {
        super(locator, name);
    }

    protected abstract String getProjectNameLocator();

    protected abstract String getProjectDeadlineLocator();

    protected abstract String getProjectPercentFundLocator();

    protected ProjectInfo getProjectInfoFromElementCard(IElement element) {
        String percent = element.findChildElement(By.xpath(getProjectPercentFundLocator()),
                ElementType.LABEL).getText();
        String deadline = element.findChildElement(By.xpath(getProjectDeadlineLocator()),
                ElementType.LABEL).getText();
        String projectName = StringUtils.substringBeforeOrReturnOrigin(
                element.findChildElement(By.xpath(getProjectNameLocator()), ElementType.LABEL)
                        .getText(), ".");
        return new ProjectInfo(deadline, percent, projectName);
    }
}
