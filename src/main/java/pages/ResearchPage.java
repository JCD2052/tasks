package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import models.BaseCarInfo;
import org.openqa.selenium.By;
import utils.WebElementsUtils;

public class ResearchPage extends BasePage {
    private final IButton btnSearch = getElementFactory()
            .getButton(By.xpath("//button[contains(@data-linkname, 'search')]"),
                    "Search");
    private final IComboBox sltMaker = getElementFactory().getComboBox(By.id("make-select"),
            "Maker");
    private final IComboBox sltModel = getElementFactory().getComboBox(By.id("model-select"),
            "Model");
    private final IComboBox sltYear = getElementFactory().getComboBox(By.id("year-select"),
            "Year");

    public ResearchPage() {
        super(By.id("by-search-tab"), "Research");
    }

    public void selectBaseCarInfo(BaseCarInfo baseCarInfo) {
        sltMaker.clickAndSelectByText(baseCarInfo.getMaker());
        sltModel.clickAndSelectByText(baseCarInfo.getModel());
        sltYear.clickAndSelectByText(baseCarInfo.getYear());
        btnSearch.click();
    }

    public String selectAndGetRandomValueFromYearSelector() {
        return WebElementsUtils.selectAndGetRandomValueFromSelectMenu(sltYear);
    }

    public String selectAndGetRandomValueFromModelSelector() {
        return WebElementsUtils.selectAndGetRandomValueFromSelectMenu(sltModel);
    }

    public String selectAndGetRandomValueFromMakerSelector() {
        return WebElementsUtils.selectAndGetRandomValueFromSelectMenu(sltMaker);
    }
}