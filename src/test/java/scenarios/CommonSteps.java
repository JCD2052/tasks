package scenarios;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.BaseCarInfo;
import models.CarInfo;
import models.CarTrimInfo;
import models.pagemodels.SearchInfo;
import org.testng.Assert;
import pages.CarInfoPage;
import pages.CarsForSalePage;
import pages.ComparePage;
import pages.CompareResultPage;
import pages.HomePage;
import pages.ResearchPage;
import pages.SearchResultPage;
import pages.TrimInfoPage;

public class CommonSteps {
    protected final ComparePage comparePage = new ComparePage();
    private final CompareResultPage compareResultPage = new CompareResultPage();
    private final CarsForSalePage carsForSalePage = new CarsForSalePage();
    private final SearchResultPage searchResultPage = new SearchResultPage();
    protected Browser browser;
    protected final HomePage homePage = new HomePage();
    protected final ResearchPage researchPage = new ResearchPage();
    protected CarInfoPage carInfoPage = new CarInfoPage();
    protected TrimInfoPage trimInfoPage = new TrimInfoPage();
    protected final ScenarioContext scenarioContext = new ScenarioContext();

    @Before
    public void setup() {
        browser = AqualityServices.getBrowser();
    }

    @Given("Go To home page.")
    public void goToHomePage() {
        browser.goTo("http://www.cars.com/");
    }

    @Then("Check if I am on home page.")
    public void checkIfIAmOnHomePage() {
        Assert.assertTrue(homePage.waitForLoad(), "Home Page hasn't been loaded.");
    }

    @When("Go to header and select Research & reviews tab.")
    public void goToResearchReviewsTab() {
        homePage.getHeaderMenu().selectResearchPageFromMenu();
    }

    @After
    public void tearDown() {
        browser.quit();
    }

    @When("Select car info: <maker> <model> <year>' and click search. Store it as {string}")
    public void selectCarInfoMakerModelYearAndClickSearch(String maker, String model, String year,
                                                          String storeName) {
        BaseCarInfo baseCarInfo = new BaseCarInfo(maker, model, year);
        scenarioContext.setContext(storeName, baseCarInfo);
        researchPage.selectBaseCarInfo(baseCarInfo);
    }

    @Then("Check if I am on car Info page.")
    public void checkIfIAmOnCarInfoPage() {
        Assert.assertTrue(carInfoPage.waitForLoad(), "Car info Page hasn't been loaded");
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
    public void storeInfoAboutCarAsFirstCar(String storeName) {
        BaseCarInfo baseCarInfo = (BaseCarInfo) scenarioContext.getContext(storeName);
        CarTrimInfo carTrimInfo = new CarTrimInfo(trimInfoPage.getDriveTrainType(),
                trimInfoPage.getSeatsCount(), trimInfoPage.getEngineInfo());
        scenarioContext.setContext(storeName, new CarInfo(baseCarInfo, carTrimInfo));
    }

    @When("Go to compare page from footer.")
    public void goToComparePageFromFooter() {
        goToHomePage();
        homePage.getFooterMenu().selectCompare();
    }

    @Then("Check if am on Compare page.")
    public void checkIfAmOnComparePage() {
        Assert.assertTrue(comparePage.waitForLoad(), "Compare page hasn't been loaded.");
    }

    @When("Fill fields with {string} and {string} and click compare.")
    public void fillFieldsWithFirstCarAndSecondCarAndClickCompare(String firstCarStoreName,
                                                                  String secondCarStoreName) {
        CarInfo firstCar = (CarInfo) scenarioContext.getContext(firstCarStoreName);
        CarInfo secondCar = (CarInfo) scenarioContext.getContext(secondCarStoreName);
        comparePage.startCarsComparison(firstCar, secondCar);

    }

    @Then("Check if I am on Compare Result page.")
    public void checkIfIAmOnCompareResultPage() {
        Assert.assertTrue(compareResultPage.waitForLoad(),
                "Compare Result page hasn't been loaded");
    }

    @When("Take {int} car and store it as {string}.")
    public void takeCarAndStoreItAsFirstCarFromCompare(int position, String storeName) {
        CarInfo carInfo = compareResultPage.getCarInfoByPosition(position);
        scenarioContext.setContext(storeName, carInfo);
    }

    @Then("Check if trim info {string} and {string} are matched.")
    public void checkIfTrimInfosAreMatched(String firstCarStoredName, String secondCarStoredName) {
        CarTrimInfo firstTrimInfo = ((CarInfo) scenarioContext.getContext(firstCarStoredName))
                .getTrimInfo();
        CarTrimInfo secondTrimInfo = ((CarInfo) scenarioContext.getContext(secondCarStoredName))
                .getTrimInfo();

        Assert.assertEquals(firstTrimInfo.getDrivetrainType(), secondTrimInfo.getDrivetrainType(),
                "Drivetrains are not matched.");
        Assert.assertEquals(firstTrimInfo.getEngine(), secondTrimInfo.getEngine(),
                "Engines are not matched.");
        Assert.assertEquals(firstTrimInfo.getSeatsCount(), secondTrimInfo.getSeatsCount(),
                "Seats count are not matched.");
    }

    @When("Save trim name on position {int} as {string}.")
    public void saveTrimNameOnPosition(int trimPosition, String storeName) {
        String trimName = carInfoPage.getTrimNameByPosition(trimPosition);
        scenarioContext.setContext(storeName, trimName);
    }

    @And("Save trim price on position {int} as {string}.")
    public void saveTrimPriceOnPositionAsNewCarPrice(int trimPosition, String storeName) {
        int newCarPrice = carInfoPage.getTrimPrice(trimPosition);
        scenarioContext.setContext(storeName, newCarPrice);
    }

    @When("Go to header and select Cars for Sale tab.")
    public void goToHeaderAndSelectCarsForSaleTab() {
        goToHomePage();
        homePage.getHeaderMenu().selectCarsForSalesPageFromMenu();
    }

    @Then("Check if I am on Cars for Sale page.")
    public void checkIfIAmOnCarsForSalePage() {
        Assert.assertTrue(carsForSalePage.waitForLoad(),
                "Cars for Sale page hasn't been loaded.");
    }

    @Then("Check if I am on Search Result page.")
    public void checkIfIAmOnSearchResultPage() {
        Assert.assertTrue(searchResultPage.waitForLoad(),
                "Search Result page hasn't been loaded.");
    }

    @And("From filter menu, select year {string}.")
    public void fromFilterMenuSelectYear(String year) {
        searchResultPage.getFilterMenu().selectMaxYear(year);
    }

    @And("From filter menu, select trim stored as {string}.")
    public void fromFilterMenuSelectTrimStoredAsTrimName(String storedName) {
        String trimName = (String) scenarioContext.getContext(storedName);
        searchResultPage.getFilterMenu().selectTrim(trimName);
    }

    @Then("Check if content is available.")
    public void checkIfContentIsAvailable() {
        Assert.assertTrue(searchResultPage.isContentFound(), "No cards found.");
    }

    @When("Get first card, get its price and store it as {string}.")
    public void getFirstCardGetItsPriceAndStoreItAsUsedCarPrice(String storeName) {
        int usedCarPrice = searchResultPage.getFirstCard().getPrice();
        scenarioContext.setContext(storeName, usedCarPrice);
    }

    @Then("Check that {string} lower then {string}.")
    public void checkThatUsedCarPriceLowerThenNewCarPrice(String userCarPriceStoreName,
                                                          String newCarPriceStoreName) {
        int newCarPrice = (int) scenarioContext.getContext(newCarPriceStoreName);
        int usedCarPrice = (int) scenarioContext.getContext(userCarPriceStoreName);
        Assert.assertTrue(usedCarPrice < newCarPrice,
                String.format("New car price %d should be greater than use car price %d",
                        newCarPrice, usedCarPrice));
    }

    @When("Search for: {string}, {string}, {string}, {string}, {string}, {string}.")
    public void searchForCar(String maker, String model, String newUsed,
                             String zipCode, String distance, String price) {
        SearchInfo searchInfo = new SearchInfo(maker, model, newUsed, zipCode, distance, price);
        carsForSalePage.search(searchInfo);
    }
}
