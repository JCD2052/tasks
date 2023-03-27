package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILink;
import models.CarInfo;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.WebElementsUtils;

import java.io.IOException;

public class ResearchPage extends BasePage {
    private final IButton btnSearch = getElementFactory()
            .getButton(By.xpath("//button[contains(@data-linkname, 'search')]"), "Search");
    private final ILink lnkTrimCompare = getElementFactory()
            .getLink(By.xpath("//a[contains(@data-linkname, 'trim-compare')]"), "Trim Compare");
    private final IComboBox sltMaker = getElementFactory().getComboBox(By.id("make-select"), "Maker");
    private final IComboBox sltModel = getElementFactory().getComboBox(By.id("model-select"), "Model");
    private final IComboBox sltYear = getElementFactory().getComboBox(By.id("year-select"), "Year");
    private final TrimContentPage trimContentPage;

    public ResearchPage() {
        super(By.id("by-search-tab"), "Research");
        this.trimContentPage = new TrimContentPage();
    }

    private CarInfo selectBaseCarInfo() {
        String maker = WebElementsUtils.selectAndGetRandomValueFromSelectMenu(sltMaker);
        String model = WebElementsUtils.selectAndGetRandomValueFromSelectMenu(sltModel);
        String year = WebElementsUtils.selectAndGetRandomValueFromSelectMenu(sltYear);
        btnSearch.click();
        lnkTrimCompare.click();
        return CarInfo.builder()
                .year(year)
                .model(model)
                .maker(maker)
                .build();
    }

    public CarInfo recieveInfo(int position) throws IOException {
        CarInfo carInfo = selectBaseCarInfo();
        Assert.assertTrue(trimContentPage.waitForLoad(), "Trim Info Page hasn't been loaded.");
        while (!trimContentPage.checkTrimAvailability()){
            getHeaderMenu().goToResearchPage();
            carInfo = selectBaseCarInfo();
        }
        carInfo.setTrimInfo(trimContentPage.getTrimData(position));
        return carInfo;
    }
}