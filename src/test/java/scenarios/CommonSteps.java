//package scenarios;
//
//import aquality.selenium.browser.AqualityServices;
//import aquality.selenium.browser.Browser;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import models.CarInfo;
//import org.testng.Assert;
//import pages.HomePage;
//import pages.ResearchPage;
//
//import java.io.IOException;
//
//public class CommonSteps {
//    protected Browser browser;
//    protected final HomePage homePage = new HomePage();
//    protected final ResearchPage researchPage = new ResearchPage();
//    protected final ScenarioContext scenarioContext = new ScenarioContext();
//
//    @Before
//    public void setup() {
//        browser = AqualityServices.getBrowser();
//    }
//
//    @Given("Go To home page.")
//    public void goToHomePage() {
//        browser.goTo("http://www.cars.com/");
//    }
//
//    @Then("Check if I am on home page.")
//    public void checkIfIAmOnHomePage() {
//        Assert.assertTrue(homePage.waitForLoad(), "Home Page hasn't been loaded.");
//    }
//
//    @When("Go to header and select Research & reviews tab.")
//    public void goToResearchReviewsTab() {
//        homePage.getHeaderMenu().selectResearchPageFromMenu();
//    }
//
//    @And("Select trim {int} for some car and store info about it with name {string}.")
//    public void selectTrimForSomeCarAndStoreInfoAboutIt(int position, String name)
//            throws IOException {
//        CarInfo carInfo = researchPage.receiveInfo(position);
//        scenarioContext.setContext(name, carInfo);
//    }
//
//    @After
//    public void tearDown() {
//        browser.quit();
//    }
//}
