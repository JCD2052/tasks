//import aquality.selenium.browser.AqualityServices;
//import aquality.selenium.browser.Browser;
//import config.PropertyConfig;
//import config.TestDataConfig;
//import models.CarInfo;
//import models.SearchInfo;
//import org.testng.Assert;
//import pages.CarsForSalePage;
//import pages.ComparePage;
//import pages.HomePage;
//import pages.ResearchPage;
//import pages.CompareResultPage;
//import pages.SearchResultPage;
//
//public class Main {
//    private static final HomePage homePage = new HomePage();
//    private static final ResearchPage researchPage = new ResearchPage();
//    private static final ComparePage comparePage = new ComparePage();
//    private static final CompareResultPage COMPARE_RESULT_PAGE = new CompareResultPage();
//    private static final TestDataConfig testData = PropertyConfig.TEST_DATA_CONFIG.getConfig();
//
//    public static void main(String[] args) {
//        Browser browser = AqualityServices.getBrowser();
//        browser.goTo(testData.getBaseUrl());
//        int trimPosition = testData.getTrimPosition();
//
//        Assert.assertTrue(homePage.waitForLoad(), "Home Page hasn't been loaded.");
//
//        homePage.getHeaderMenu().selectResearchPageFromMenu();
//        Assert.assertTrue(researchPage.waitForLoad(), "Research Page hasn't been loaded.");
//
////        CarInfo firstCar = researchPage.receiveInfo(trimPosition);
//        System.out.println(firstCar);
//
//        researchPage.getHeaderMenu().selectCarsForSalesPageFromMenu();
//        CarsForSalePage carsForSalePage = new CarsForSalePage();
//        carsForSalePage.waitForLoad();
//        SearchInfo searchInfo = new SearchInfo(firstCar,
//                "Used", "10001", "20 miles", "No max price");
//        carsForSalePage.search(searchInfo);
//        SearchResultPage searchResultPage = new SearchResultPage();
//        searchResultPage.waitForLoad();
//        browser.quit();
//    }
//}
