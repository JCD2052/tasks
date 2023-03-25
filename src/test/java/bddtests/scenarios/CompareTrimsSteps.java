package bddtests.scenarios;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.BaseCarInfo;
import models.CarInfo;
import models.CarTrimInfo;
import org.testng.Assert;
import pages.CarInfoPage;
import pages.ComparePage;
import pages.CompareResultPage;
import pages.ResearchPage;
import pages.TrimInfoPage;

public class CompareTrimsSteps {
    private final CompareResultPage compareResultPage = new CompareResultPage();
    private final TrimInfoPage trimInfoPage = new TrimInfoPage();
    private final ComparePage comparePage = new ComparePage();
    private final CarInfoPage carInfoPage = new CarInfoPage();
    private final ScenarioContext scenarioContext = new ScenarioContext();
    private final ResearchPage researchPage = new ResearchPage();

    @When("Select car info: {string} {string} {string} and click search. Store it as {string}")
    public void selectCarInfoClickSearchAndStoreContext(String maker, String model, String year,
                                                        String context) {
        BaseCarInfo baseCarInfo = new BaseCarInfo(maker, model, year);
        scenarioContext.setContext(context, baseCarInfo);
        researchPage.selectBaseCarInfo(baseCarInfo);
    }

    @When("Select trim {int}.")
    public void selectTrimInt(int trimPosition) {
        carInfoPage.selectTrimByPosition(trimPosition);
    }

    @Then("Check if I am on Trim info Page.")
    public void checkIfIAmOnTrimInfoPage() {
        Assert.assertTrue(trimInfoPage.waitForLoad(), "Page hasn't been loaded");
    }

    @When("Store info about car as {string}.")
    public void storeInfoAboutCar(String context) {
        BaseCarInfo baseCarInfo = scenarioContext.getContext(context);
        CarTrimInfo carTrimInfo = new CarTrimInfo(trimInfoPage.getDriveTrainType(),
                trimInfoPage.getSeatsCount(), trimInfoPage.getEngineInfo());
        scenarioContext.setContext(context, new CarInfo(baseCarInfo, carTrimInfo));
    }

    @When("Go to compare page from footer.")
    public void goToComparePageFromFooter() {
        trimInfoPage.getFooterMenu().selectCompare();
    }

    @Then("Check if am on Compare page.")
    public void checkIfAmOnComparePage() {
        Assert.assertTrue(comparePage.waitForLoad(), "Compare page hasn't been loaded.");
    }

    @When("Fill fields with {string} and {string} and click compare.")
    public void fillFieldsAndStartCompare(String firstCarStoreName, String secondCarStoreName) {
        CarInfo firstCar = scenarioContext.getContext(firstCarStoreName);
        CarInfo secondCar = scenarioContext.getContext(secondCarStoreName);
        comparePage.startCarsComparison(firstCar, secondCar);
    }

    @Then("Check if I am on Compare Result page.")
    public void checkIfIAmOnCompareResultPage() {
        Assert.assertTrue(compareResultPage.waitForLoad(),
                "Compare Result page hasn't been loaded");
    }

    @When("Take {int} car and store it as {string}.")
    public void takeCarInfoAndStoreToCompare(int position, String context) {
        CarInfo carInfo = compareResultPage.getCarInfoByPosition(position);
        scenarioContext.setContext(context, carInfo);
    }

    @Then("Check if trim info {string} and {string} are matched.")
    public void checkIfTrimInfosAreMatched(String firstCarContext, String secondCarContext) {
        CarTrimInfo firstTrimInfo = ((CarInfo) scenarioContext.getContext(firstCarContext))
                .trimInfo();
        CarTrimInfo secondTrimInfo = ((CarInfo) scenarioContext.getContext(secondCarContext))
                .trimInfo();

        Assert.assertEquals(firstTrimInfo.drivetrainType(), secondTrimInfo.drivetrainType(),
                "Drivetrains are not matched.");
        Assert.assertEquals(firstTrimInfo.engine(), secondTrimInfo.engine(),
                "Engines are not matched.");
        Assert.assertEquals(firstTrimInfo.seatsCount(), secondTrimInfo.seatsCount(),
                "Seats count are not matched.");
    }
}
