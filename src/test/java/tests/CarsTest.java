package tests;

import models.BaseCarInfo;
import models.CarInfo;
import models.pagemodels.SearchInfo;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CarInfoPage;
import pages.CarsForSalePage;
import pages.ComparePage;
import pages.HomePage;
import pages.ResearchPage;
import pages.CompareResultPage;
import pages.SearchResultPage;
import tests.steps.CarTestsSteps;

public class CarsTest extends BaseTest {
    private final HomePage homePage = new HomePage();
    private final ResearchPage researchPage = new ResearchPage();
    private final ComparePage comparePage = new ComparePage();
    private final CompareResultPage compareResultPage = new CompareResultPage();
    private final CarInfoPage carInfoPage = new CarInfoPage();

    @Test
    public void compareRandomCarsTrimTest() {
        int trimPosition = testData.getTrimPosition();

        Assert.assertTrue(homePage.waitForLoad(),
                "Home Page hasn't been loaded.");

        homePage.getHeaderMenu().selectResearchPageFromMenu();
        Assert.assertTrue(researchPage.waitForLoad(),
                "Research Page hasn't been loaded.");

        CarInfo firstCar = CarTestsSteps.getRandomCarInfoUntilAvailable(trimPosition);

        browser.goTo(testData.getBaseUrl());
        Assert.assertTrue(homePage.waitForLoad(),
                "Home Page hasn't been loaded.");

        homePage.getHeaderMenu().selectResearchPageFromMenu();
        Assert.assertTrue(researchPage.waitForLoad(),
                "Research Page hasn't been loaded.");

        CarInfo secondCar = CarTestsSteps.getRandomCarInfoUntilAvailable(trimPosition);
        researchPage.getFooterMenu().selectCompare();
        Assert.assertTrue(comparePage.waitForLoad(),
                "Compare Page hasn't been loaded.");

        comparePage.startCarsComparison(firstCar, secondCar);

        Assert.assertTrue(compareResultPage.waitForLoad(),
                "Result Page hasn't been loaded.");

        CarInfo receivedFistCar = compareResultPage.getCarInfoByPosition(1);
        CarInfo receivedSecondCar = compareResultPage.getCarInfoByPosition(2);

        CarTestsSteps.assertCarTrimInfo(firstCar.trimInfo(), receivedFistCar.trimInfo());
        CarTestsSteps.assertCarTrimInfo(secondCar.trimInfo(), receivedSecondCar.trimInfo());
    }

    @Test
    public void compareCarPrice() {
        int trimPosition = testData.getTrimPosition();

        Assert.assertTrue(homePage.waitForLoad(),
                "Home Page hasn't been loaded.");

        homePage.getHeaderMenu().selectResearchPageFromMenu();
        Assert.assertTrue(researchPage.waitForLoad(),
                "Research Page hasn't been loaded.");

        BaseCarInfo baseCarInfo = new BaseCarInfo("Volkswagen", "Passat", "2016");
        researchPage.selectBaseCarInfo(baseCarInfo);
        Assert.assertTrue(carInfoPage.waitForLoad(), "Car info Page hasn't been loaded");
        String trimName = carInfoPage.getTrimNameByPosition(trimPosition);
        int newCarPrice = carInfoPage.getTrimPrice(trimPosition);
        carInfoPage.getHeaderMenu().selectCarsForSalesPageFromMenu();

        CarsForSalePage carsForSalePage = new CarsForSalePage();
        Assert.assertTrue(carsForSalePage.waitForLoad(),
                "Cars for sale page hasn't been loaded");
        SearchInfo searchInfo = new SearchInfo(baseCarInfo.maker(), baseCarInfo.model(),
                "Used", "10001", "20 miles", "No max price");
        carsForSalePage.search(searchInfo);
        SearchResultPage searchResultPage = new SearchResultPage();
        Assert.assertTrue(searchResultPage.waitForLoad(),
                "Search Result Page hasn't been loaded");

        searchResultPage.getFilterMenu().selectMaxYear(baseCarInfo.year());
        searchResultPage.getFilterMenu().selectTrim(trimName);

        Assert.assertTrue(searchResultPage.isContentFound(), "No cards found.");
        int usedCarPrice = searchResultPage.getFirstCard().price();
        Assert.assertTrue(usedCarPrice < newCarPrice,
                String.format("New car price %d should be greater than use car price %d",
                        newCarPrice, usedCarPrice));
    }
}