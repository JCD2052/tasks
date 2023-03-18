package pages.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FilterMenu extends Form {
    private static final String TRIM_CHECKBOX_LOCATOR = "//div[@id ='trim']//input[contains(@id, '%s')]";
    private static final String TRIM_LABEL_LOCATOR = "//following-sibling::label";
    private final IComboBox minYearSelectBox = getElementFactory()
            .getComboBox(By.id("year_year_min_select"), "Min year select box.");
    private final IComboBox maxYearSelectBox = getElementFactory()
            .getComboBox(By.id("year_year_max_select"), "Max year select box.");

    public FilterMenu() {
        super(By.xpath("//div[contains(@class, 'guided')]"), "Filter menu");
    }

    public void selectMinYear(String year) {
        minYearSelectBox.clickAndSelectByValue(year);
    }

    public void selectMaxYear(String year) {
        maxYearSelectBox.clickAndSelectByValue(year);
    }

    public void selectTrim(String trimName) {
        String locatorTrimName = String.format(TRIM_CHECKBOX_LOCATOR,
                trimName.replace(" ", "_").toLowerCase());
        ICheckBox trimCheckbox = getElementFactory()
                .findElements(By.xpath(locatorTrimName), ICheckBox.class)
                .stream()
                .filter(checkbox ->
                        checkbox.findChildElement(By.xpath(TRIM_LABEL_LOCATOR), ElementType.LABEL)
                                .getText().equals(trimName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Trim %s hasn't been found", trimName)));
        trimCheckbox.check();
    }
}
