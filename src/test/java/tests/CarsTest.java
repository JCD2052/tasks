package tests;

import models.CarInfo;
import models.SearchInfo;
import models.SelectInfo;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CarInfoPage;
import pages.CarsForSalePage;
import pages.ComparePage;
import pages.HomePage;
import pages.ResearchPage;
import pages.CompareResultPage;
import pages.SearchResultPage;
import pages.TrimInfoPage;
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

        CarTestsSteps.assertCarTrimInfo(firstCar.getTrimInfo(), receivedFistCar.getTrimInfo());
        CarTestsSteps.assertCarTrimInfo(secondCar.getTrimInfo(), receivedSecondCar.getTrimInfo());
    }

    @Test
    public void compareCarPrice() {
        int trimPosition = testData.getTrimPosition();

        Assert.assertTrue(homePage.waitForLoad(),
                "Home Page hasn't been loaded.");

        homePage.getHeaderMenu().selectResearchPageFromMenu();
        Assert.assertTrue(researchPage.waitForLoad(),
                "Research Page hasn't been loaded.");

        SelectInfo selectInfo = new SelectInfo("", "", "");
        researchPage.selectBaseCarInfo(selectInfo);
        Assert.assertTrue(carInfoPage.waitForLoad(), "Car info Page hasn't been loaded");
        String trimName = carInfoPage.getTrimNameByPosition(trimPosition);
        int trimPrice = carInfoPage.getTrimPrice(trimPosition);
        carInfoPage.selectTrimByPosition(trimPosition);
        TrimInfoPage trimInfoPage = new TrimInfoPage();
        Assert.assertTrue(trimInfoPage.waitForLoad(), "Trim info Page hasn't been loaded");
        researchPage.getHeaderMenu().selectCarsForSalesPageFromMenu();

        CarsForSalePage carsForSalePage = new CarsForSalePage();
        Assert.assertTrue(carsForSalePage.waitForLoad(),
                "Cars for sale page hasn't been loaded");
        SearchInfo searchInfo = new SearchInfo("", "", "",
                "", "", "");
        carsForSalePage.search(searchInfo);
        SearchResultPage searchResultPage = new SearchResultPage();
        Assert.assertTrue(searchResultPage.waitForLoad(),
                "Search Result Page hasn't been loaded");

        searchResultPage.getFilterMenu().selectMaxYear(selectInfo.getYear());
        searchResultPage.getFilterMenu().selectMinYear(selectInfo.getYear());
        searchResultPage.getFilterMenu().selectTrim(trimName);

        Assert.assertTrue(searchResultPage.isContentFound(), "No cards found.");
        int carPrice = searchResultPage.getFirstCard().getPrice();
        Assert.assertEquals(carPrice, trimPrice, "Prices are not matched.");
    }
}