package org.jcd2052.screens;

import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import org.jcd2052.models.ProjectInfo;
import org.openqa.selenium.By;

public class SearchScreen extends BaseProjectInfoScreen {
    private static final String SEARCH_INPUT_LOCATOR = "//android.widget.EditText[@resource-id='com.kickstarter.kickstarter:id/search_edit_text']";
    private static final String PROJECT_LOCATOR = "//android.widget.TextView[contains(@text,'%s')]//parent::android.widget.LinearLayout";
    private static final String PROJECT_NAME_LOCATOR = "//android.widget.TextView[@resource-id='com.kickstarter.kickstarter:id/project_name_text_view']";
    private static final String PROJECT_DEADLINE_LOCATOR = "//android.widget.TextView[@resource-id='com.kickstarter.kickstarter:id/search_result_deadline_countdown_text_view']";
    private static final String PROJECT_PERCENT_FUND_LOCATOR = "//android.widget.TextView[@resource-id='com.kickstarter.kickstarter:id/search_result_percent_funded_text_view']";
    private final ITextBox searchTextInput = getElementFactory()
            .getTextBox(By.xpath(SEARCH_INPUT_LOCATOR), "Search text input.");

    public SearchScreen() {
        super(By.xpath(SEARCH_INPUT_LOCATOR), "Search Screen");
    }

    public void searchProject(String projectName) {
        searchTextInput.click();
        searchTextInput.clearAndType(projectName);
    }

    public void selectProject(String projectName) {
        getSearchedElement(projectName).click();
    }

    public ProjectInfo getProjectInfo(String projectName) {
        return getProjectInfoFromElementCard(getSearchedElement(projectName));
    }

    @Override
    protected String getProjectNameLocator() {
        return PROJECT_NAME_LOCATOR;
    }

    @Override
    protected String getProjectDeadlineLocator() {
        return PROJECT_DEADLINE_LOCATOR;
    }

    @Override
    protected String getProjectPercentFundLocator() {
        return PROJECT_PERCENT_FUND_LOCATOR;
    }

    private IButton getSearchedElement(String projectName) {
        String locator = String.format(PROJECT_LOCATOR, projectName);
        return getElementFactory().getButton(By.xpath(locator), "Project card");
    }
}
