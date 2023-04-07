package org.jcd2052;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.jcd2052.config.BaseConfig;
import org.jcd2052.config.PropertyConfig;
import org.jcd2052.pages.AbstractAboutUsPage;
import org.jcd2052.pages.AbstractHomePage;
import org.jcd2052.utiils.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected AbstractHomePage homePage;
    protected AbstractAboutUsPage aboutUsPage;
    private final BaseConfig baseConfig = PropertyConfig.BASE_CONFIG.getConfig();

    protected Browser browser;

    @BeforeMethod(alwaysRun = true)
    protected void setUp() {
        browser = AqualityServices.getBrowser();
        browser.maximize();

        homePage = PageFactory.getHomePage();
        aboutUsPage = PageFactory.getAboutUsPage();

        browser.goTo(baseConfig.baseUrl());
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        browser.quit();
    }
}
