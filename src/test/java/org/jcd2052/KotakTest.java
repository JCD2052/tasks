package org.jcd2052;

import org.jcd2052.config.PropertyConfig;
import org.jcd2052.config.TestDataConfig;
import org.jcd2052.pages.HeaderTab;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KotakTest extends BaseTest {
    private final TestDataConfig testDataConfig = PropertyConfig.TEST_DATA_CONFIG.getConfig();

    @Test
    public void desktopTest() {
        Assert.assertTrue(homePage.state().waitForDisplayed(),
                "Home page hasn't been loaded.");

        homePage.getNavigation().selectTab(HeaderTab.ABOUT_US);

        Assert.assertTrue(aboutUsPage.state().waitForDisplayed(),
                "Home page hasn't been loaded.");

        aboutUsPage.selectYear(testDataConfig.yearToFind());
        String textFromInfo = aboutUsPage.getAllTextFromInfo();
        Assert.assertTrue(textFromInfo.contains(testDataConfig.firstTextInfo()),
                String.format("Text from Info Window %s: doesn't contain text: %s",
                        textFromInfo, testDataConfig.firstTextInfo()));

        Assert.assertTrue(textFromInfo.contains(testDataConfig.secondTextInfo()),
                String.format("Text from Info Window %s: doesn't contain text: %s",
                        textFromInfo, testDataConfig.secondTextInfo()));
    }
}
