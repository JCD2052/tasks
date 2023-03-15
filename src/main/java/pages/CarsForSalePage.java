package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ITextBox;
import models.SearchInfo;
import org.openqa.selenium.By;

public class CarsForSalePage extends BasePage {
    private final IComboBox newUsedSelectBox = getElementFactory()
            .getComboBox(By.id("make-model-search-stocktype"), "Select for New/Used.");
    private final IComboBox makerSelectBox = getElementFactory()
            .getComboBox(By.id("makes"), "Select for a car Maker.");
    private final IComboBox modelSelectBox = getElementFactory()
            .getComboBox(By.id("models"), "Select for a car model.");
    private final IComboBox priceSelectBox = getElementFactory()
            .getComboBox(By.id("make-model-max-price"), "Select for price.");
    private final IComboBox distanceSelectBox = getElementFactory()
            .getComboBox(By.id("make-model-maximum-distance"), "Select for the distance.");
    private final ITextBox zipCodeTextInput = getElementFactory()
            .getTextBox(By.id("make-model-zip"), "Text Input for Zip code.");
    private final IButton searchButton = getElementFactory()
            .getButton(By.xpath("//button[contains(@data-linkname, 'search-used-make')]"),
                    "Search button.");

    public CarsForSalePage() {
        super(By.id("search-widget"), "Cars for Sale Page.");
    }

    public void search(SearchInfo searchInfo) {
        newUsedSelectBox.clickAndSelectByText(searchInfo.getNewUsed());
        makerSelectBox.clickAndSelectByText(searchInfo.getCarInfo().getMaker());
        modelSelectBox.clickAndSelectByText(searchInfo.getCarInfo().getModel());
        priceSelectBox.clickAndSelectByText(searchInfo.getPrice());
        distanceSelectBox.clickAndSelectByText(searchInfo.getDistance());
        zipCodeTextInput.clearAndType(searchInfo.getZipCode());
        searchButton.click();
    }
}
