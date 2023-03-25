package bddtests.stepdefenitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CarInfoPage;
import pages.HomePage;
import pages.ResearchPage;

public class CommonSteps {
    private final HomePage homePage = new HomePage();
    private final ResearchPage researchPage = new ResearchPage();
    private final CarInfoPage carInfoPage = new CarInfoPage();

    @Then("Check if I am on home page")
    public void checkIfIAmOnHomePage() {
        Assert.assertTrue(homePage.waitForLoad(), "Home Page hasn't been loaded.");
    }

    @When("I go to header and select Research & reviews tab")
    public void goToResearchReviewsTab() {
        homePage.getHeaderMenu().selectResearchPageFromMenu();
    }

    @Then("Check if I am on reviews page")
    public void checkIfIAmInReviewsPage() {
        Assert.assertTrue(researchPage.waitForLoad(), "Research Page hasn't been loaded.");
    }

    @Then("Check if I am on car Info page")
    public void checkIfIAmOnCarInfoPage() {
        Assert.assertTrue(carInfoPage.waitForLoad(), "Car info Page hasn't been loaded");
    }
}
