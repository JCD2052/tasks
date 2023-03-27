package org.jcd2052.screens;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import org.jcd2052.models.ProjectInfo;
import org.jcd2052.utils.ScreenUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import java.util.List;

public class MainScreen extends BaseProjectInfoScreen {
    private static final String PROJECT_CARD_LOCATOR = "//android.widget.RelativeLayout[@resource-id='com.kickstarter.kickstarter:id/project_card_view_group']";
    private static final String PROJECT_DEADLINE_LOCATOR = "//android.widget.TextView[@resource-id='com.kickstarter.kickstarter:id/deadline_countdown']";
    private static final String PROJECT_PERCENT_FUND_LOCATOR = "//android.widget.TextView[@resource-id='com.kickstarter.kickstarter:id/percent']";
    private static final String PROJECT_NAME_LOCATOR = "//android.widget.TextView[@resource-id='com.kickstarter.kickstarter:id/name_and_blurb_text_view']";
    private final IButton searchButton = getElementFactory()
            .getButton(By.xpath("//android.widget.ImageButton[@content-desc='Search']"),
                    "Search Button");

    public MainScreen() {
        super(By.xpath("//android.widget.ImageView[@content-desc='Kickstarter']"),
                "Main Screen",
                PROJECT_NAME_LOCATOR, PROJECT_DEADLINE_LOCATOR, PROJECT_PERCENT_FUND_LOCATOR);
    }

    public void clickToSearch() {
        searchButton.click();
    }

    public ProjectInfo getProjectInfo(int position) {
        ILabel projectCard = getProjectCardsOnScreen().get(position - 1);
        return getProjectInfoFromElementCard(projectCard);
    }

    public boolean waitForProjectsLoading() {
        return getElementFactory().getLabel(By.xpath(PROJECT_CARD_LOCATOR), "Project card")
                .state().waitForDisplayed();
    }

    public void swipeToTheNextTab() {
        int elementsCenterHeight = getFirstProject().getCenter().getY();
        Point startPoint = new Point(ScreenUtils.getScreenSize().getX(),
                elementsCenterHeight);
        ScreenUtils.swipeToEdgeOfScreen(startPoint, SwipeDirection.LEFT);
    }

    public void swipeDown() {
        Point startPoint = getFirstProject().getCenter();
        ScreenUtils.swipeToEdgeOfScreen(startPoint, SwipeDirection.DOWN);
    }

    public void swipeToShowSearch() {
        Point screenSize = ScreenUtils.getScreenSize();
        Point centreOfScreen = new Point(screenSize.getX() / 2, screenSize.getY() / 2);
        int endPointY = centreOfScreen.getY() + (centreOfScreen.getY() / 4);
        Point endPoint = new Point(centreOfScreen.getX(), endPointY);
        ScreenUtils.swipe(centreOfScreen, endPoint);
    }

    private List<ILabel> getProjectCardsOnScreen() {
        return getElementFactory().findElements(By.xpath(PROJECT_CARD_LOCATOR), ElementType.LABEL);
    }

    private ILabel getFirstProject() {
        return getProjectCardsOnScreen()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Need here custom exception"));
    }
}