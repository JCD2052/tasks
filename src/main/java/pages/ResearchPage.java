package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILink;
import models.CarInfo;
import org.openqa.selenium.By;
import utils.RandomUtils;
import utils.WebElementsUtils;

import java.io.IOException;
import java.util.List;

public class ResearchPage extends BasePage {
    private final IButton btnSearch = getElementFactory()
            .getButton(By.xpath("//button[contains(@data-linkname, 'search')]"), "Search");
    private final ILink lnkTrimCompare = getElementFactory()
            .getLink(By.xpath("//a[contains(@data-linkname, 'trim-compare')]"), "Trim Compare");
    private final IComboBox sltMaker = getElementFactory().getComboBox(By.id("make-select"), "Maker");
    private final IComboBox sltModel = getElementFactory().getComboBox(By.id("model-select"), "Model");
    private final IComboBox sltYear = getElementFactory().getComboBox(By.id("year-select"), "Year");
    private final TrimContentPage trimContentPage;
    private String maker;
    private String model;
    private String year;


    public ResearchPage() {
        super(By.id("by-search-tab"), "Research");
        this.trimContentPage = new TrimContentPage();
    }

    private String selectAndGetRandomValueFromSelectMenu(IComboBox selectMenu) {
        List<String> values = selectMenu.getValues();
        int randomValue = RandomUtils.getRandomIntInRange(1, values.size());
        selectMenu.selectByValue(values.get(randomValue));
        return WebElementsUtils.getTextFromSelectMenu(selectMenu);
    }

    private void selectCarInfo() {
        maker = selectAndGetRandomValueFromSelectMenu(sltMaker);
        model = selectAndGetRandomValueFromSelectMenu(sltModel);
        year = selectAndGetRandomValueFromSelectMenu(sltYear);
        btnSearch.click();
        lnkTrimCompare.click();
    }

    private boolean hasTrims() {
        return trimContentPage.checkTrimAvailability();
    }

    public CarInfo recieveInfo(int position) throws IOException {
        while (true) {
            selectCarInfo();
            if (!hasTrims()) {
                getHeaderMenu().goToResearchPage();
            } else {
                return CarInfo
                        .builder()
                        .year(year)
                        .model(model)
                        .maker(maker)
                        .trimInfo(trimContentPage.getTrimData(position))
                        .build();
            }
        }
    }
}