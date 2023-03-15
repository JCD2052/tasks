package tests;

import models.CarInfo;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ComparePage;
import pages.HomePage;
import pages.ResearchPage;
import pages.CompareResultPage;
import tests.steps.CarTestsSteps;

public class CarsTest extends BaseTest {
    private final HomePage homePage = new HomePage();
    private final ResearchPage researchPage = new ResearchPage();
    private final ComparePage comparePage = new ComparePage();
    private final CompareResultPage compareResultPage = new CompareResultPage();

    @Test
    public void carsTest() {
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
}