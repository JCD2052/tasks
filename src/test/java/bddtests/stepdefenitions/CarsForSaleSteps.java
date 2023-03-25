package bddtests.stepdefenitions;

import bddtests.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.BaseCarInfo;
import models.pagemodels.SearchInfo;
import org.testng.Assert;
import pages.CarInfoPage;
import pages.CarsForSalePage;
import pages.ResearchPage;
import pages.SearchResultPage;

public class CarsForSaleSteps {
    private final SearchResultPage searchResultPage = new SearchResultPage();
    private final CarsForSalePage carsForSalePage = new CarsForSalePage();
    private final CarInfoPage carInfoPage = new CarInfoPage();
    private final ScenarioContext scenarioContext = new ScenarioContext();
    private final ResearchPage researchPage = new ResearchPage();

    @When("I select car info: {string} {string} {string} and click search")
    public void selectCarInfoClickSearch(String maker, String model, String year) {
        BaseCarInfo baseCarInfo = new BaseCarInfo(maker, model, year);
        researchPage.selectBaseCarInfo(baseCarInfo);
    }

    @When("I save trim name on position '{int}' as {string}")
    public void saveTrimNameOnPosition(int trimPosition, String context) {
        String trimName = carInfoPage.getTrimNameByPosition(trimPosition);
        scenarioContext.setContext(context, trimName);
    }

    @And("I save trim price on position '{int}' as {string}")
    public void saveTrimPriceOnPosition(int trimPosition, String context) {
        int newCarPrice = carInfoPage.getTrimPrice(trimPosition);
        scenarioContext.setContext(context, newCarPrice);
    }

    @When("I go to header and select Cars for Sale tab")
    public void goToHeaderAndSelectCarsForSaleTab() {
        carInfoPage.getHeaderMenu().selectCarsForSalesPageFromMenu();
    }

    @Then("Check if I am on Cars for Sale page")
    public void checkIfIAmOnCarsForSalePage() {
        Assert.assertTrue(carsForSalePage.waitForLoad(),
                "Cars for Sale page hasn't been loaded.");
    }

    @When("I search for: {string}, {string}, {string}, {string}, {string}, {string}")
    public void searchForCar(String maker, String model, String newUsed,
                             String zipCode, String distance, String price) {
        SearchInfo searchInfo = new SearchInfo(maker, model, newUsed, zipCode, distance, price);
        carsForSalePage.search(searchInfo);
    }

    @Then("Check if I am on Search Result page")
    public void checkIfIAmOnSearchResultPage() {
        Assert.assertTrue(searchResultPage.waitForLoad(),
                "Search Result page hasn't been loaded.");
    }

    @And("From filter menu, I select year {string}")
    public void fromFilterMenuSelectYear(String year) {
        searchResultPage.getFilterMenu().selectMaxYear(year);
    }

    @And("From filter menu, I select trim stored as {string}")
    public void fromFilterMenuSelectTrim(String storedName) {
        String trimName = scenarioContext.getContext(storedName);
        searchResultPage.getFilterMenu().selectTrim(trimName);
    }

    @Then("Check if content is available")
    public void checkIfContentIsAvailable() {
        Assert.assertTrue(searchResultPage.isContentFound(), "No cards found.");
    }

    @When("I get first card, get its price and store it as {string}")
    public void getFirstCardGetItsPrice(String context) {
        int usedCarPrice = searchResultPage.getFirstCard().price();
        scenarioContext.setContext(context, usedCarPrice);
    }

    @Then("I check that {string} lower then {string}")
    public void checkThatUsedCarPriceLowerThenNewCarPrice(String userCarPriceContext,
                                                          String newCarPriceContext) {
        int newCarPrice = scenarioContext.getContext(newCarPriceContext);
        int usedCarPrice = scenarioContext.getContext(userCarPriceContext);
        Assert.assertTrue(usedCarPrice < newCarPrice,
                String.format("New car price %d should be greater than use car price %d",
                        newCarPrice, usedCarPrice));
    }
}
