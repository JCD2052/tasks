package scenarios;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarInfo;
import org.testng.Assert;
import pages.ComparePage;
import pages.CompareResultPage;

public class CompareTrimsSteps extends CommonSteps {
    private final ComparePage comparePage = new ComparePage();
    private final CompareResultPage resultPage = new CompareResultPage();

    @When("Go to footer menu and go to Compare page.")
    public void goToFooterMenuAndGoToComparePage() {
        researchPage.getFooterMenu().selectCompare();
    }

    @When("Start to compare {string} and {string}.")
    public void startToCompareStoredCars(String firstCarName, String secondCarName) {
        CarInfo carInfo1 = (CarInfo) scenarioContext.getContext(firstCarName); //get from context.
        CarInfo carInfo2 = (CarInfo) scenarioContext.getContext(secondCarName);//get from context.
        comparePage.startCarsComparison(carInfo1, carInfo2);
    }

    @When("From this \\(research) page go to header and select Research & reviews tab.")
    public void fromResearchPageGoToHeaderAndSelectResearchReviewsTab() {
        researchPage.getHeaderMenu().selectResearchPageFromMenu();
    }

    @When("Store info about car with position {int} as {string}.")
    public void storeInfoAboutCarWithPosition(int position, String name) {
        CarInfo receivedCar = resultPage.getCarInfoByPosition(position);
        scenarioContext.setContext(name, receivedCar);
    }

    @Then("Check whether {string} info from Research Page matches with {string} info from Compare Page.")
    public void checkWhetherInfoFromResearchPageMatchesWithInfoFromComparePage(String car1,
                                                                               String car2) {
        CarInfo receivedFistCar = (CarInfo) scenarioContext.getContext(car1); //get from context
        CarInfo firstCar = (CarInfo) scenarioContext.getContext(car2); //get from context.
        Assert.assertEquals(firstCar, receivedFistCar,
                "Car Info from Research page doesn't match with Car Info from compare page");
    }

    @Then("Check if I am on reviews page.")
    public void checkIfIAmInReviewsPage() {
        Assert.assertTrue(researchPage.waitForLoad(), "Research Page hasn't been loaded.");
    }

    @Then("Check if I am on compare page.")
    public void checkIfIAmOnComparePage() {
        Assert.assertTrue(comparePage.waitForLoad(), "Compare Page hasn't been loaded.");
    }
}
