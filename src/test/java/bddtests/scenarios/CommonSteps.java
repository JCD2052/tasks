package bddtests.scenarios;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import config.PropertyConfig;
import config.TestDataConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CarInfoPage;
import pages.HomePage;
import pages.ResearchPage;

public class CommonSteps {
    private final TestDataConfig testData = PropertyConfig.TEST_DATA_CONFIG.getConfig();
    private Browser browser;
    private final HomePage homePage = new HomePage();
    private final ResearchPage researchPage = new ResearchPage();
    private final CarInfoPage carInfoPage = new CarInfoPage();

    @Given("Go to home page")
    public void goToHomePage() {
        browser.goTo(testData.getBaseUrl());
    }

    @Then("Check if I am on home page.")
    public void checkIfIAmOnHomePage() {
        Assert.assertTrue(homePage.waitForLoad(), "Home Page hasn't been loaded.");
    }

    @When("Go to header and select Research & reviews tab.")
    public void goToResearchReviewsTab() {
        homePage.getHeaderMenu().selectResearchPageFromMenu();
    }

    @Then("Check if I am on reviews page.")
    public void checkIfIAmInReviewsPage() {
        Assert.assertTrue(researchPage.waitForLoad(), "Research Page hasn't been loaded.");
    }

    @Then("Check if I am on car Info page.")
    public void checkIfIAmOnCarInfoPage() {
        Assert.assertTrue(carInfoPage.waitForLoad(), "Car info Page hasn't been loaded");
    }

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
