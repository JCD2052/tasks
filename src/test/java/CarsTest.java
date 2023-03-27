import models.CarInfo;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ComparePage;
import pages.HomePage;
import pages.ResearchPage;
import pages.ResultPage;
import testdata.TestDataReader;
import utils.StringUtils;

import java.io.IOException;

public class CarsTest extends BaseTest {

    @Test(invocationCount = 5)
    public void carsTest() throws IOException {
        int trimPosition = StringUtils.stringToNumber(TestDataReader.read("GET_TRIM_POSITION"));
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.waitForLoad(), "Home Page hasn't been loaded.");

        ResearchPage researchPage = homePage.getHeaderMenu().goToResearchPage();
        Assert.assertTrue(researchPage.waitForLoad(),"Research Page hasn't been loaded.");

        CarInfo firstCar = researchPage.recieveInfo(trimPosition);
        browser.goTo(TestDataReader.read("BASE_URL"));
        homePage = new HomePage();
        Assert.assertTrue(homePage.waitForLoad(), "Home Page hasn't been loaded.");

        researchPage = homePage.getHeaderMenu().goToResearchPage();
        Assert.assertTrue(researchPage.waitForLoad(),"Research Page hasn't been loaded.");

        CarInfo secondCar = researchPage.recieveInfo(trimPosition);
        ComparePage comparePage = researchPage.getFooterMenu().goToComparePage();
        Assert.assertTrue(comparePage.waitForLoad(), "Compare Page hasn't been loaded.");

        ResultPage resultPage = comparePage.startCarsComparison(firstCar, secondCar);
        Assert.assertTrue(resultPage.waitForLoad(), "Result Page hasn't been loaded.");

        CarInfo receivedFistCar = resultPage.getCarInfo(1);
        CarInfo receivedSecondCar = resultPage.getCarInfo(2);
        Assert.assertEquals(firstCar, receivedFistCar, "First Car Info doesn't match with Research Info");

        Assert.assertEquals(secondCar, receivedSecondCar, "Second Car Info doesn't match with Research Info");
    }
}