package bddtests.hooks;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import config.PropertyConfig;
import config.TestDataConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class StepHooks {
    private Browser browser;
    private final TestDataConfig testData = PropertyConfig.TEST_DATA_CONFIG.getConfig();

    @Before
    public void setup() {
        browser = AqualityServices.getBrowser();
        browser.goTo(testData.getBaseUrl());
    }

    @After
    public void teardown() {
        browser.quit();
    }
}