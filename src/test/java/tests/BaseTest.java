package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import config.PropertyConfig;
import config.TestDataConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public abstract class BaseTest {
    protected Browser browser;
    protected final TestDataConfig testData = PropertyConfig.TEST_DATA_CONFIG.getConfig();

    @BeforeMethod
    protected void setup() {
        browser = AqualityServices.getBrowser();
        browser.goTo(testData.getBaseUrl());
    }

    @AfterMethod
    protected void teardown() {
        browser.quit();
    }
}