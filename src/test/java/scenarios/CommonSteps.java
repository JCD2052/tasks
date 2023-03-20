package scenarios;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import config.PropertyConfig;
import config.TestDataConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.BaseCarInfo;
import org.testng.Assert;
import pages.CarInfoPage;
import pages.HomePage;
import pages.ResearchPage;

public class CommonSteps {
    private final TestDataConfig testData = PropertyConfig.TEST_DATA_CONFIG.getConfig();
    protected Browser browser;
    protected final HomePage homePage = new HomePage();
    protected final ResearchPage researchPage = new ResearchPage();
    protected final CarInfoPage carInfoPage = new CarInfoPage();
    protected final ScenarioContext scenarioContext = new ScenarioContext();

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

    @When("Select car info: {string} {string} {string} and click search. Store it as {string}")
    public void selectCarInfoClickSearch(String maker, String model, String year,
                                                          String context) {
        BaseCarInfo baseCarInfo = new BaseCarInfo(maker, model, year);
        scenarioContext.setContext(context, baseCarInfo);
        researchPage.selectBaseCarInfo(baseCarInfo);
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
