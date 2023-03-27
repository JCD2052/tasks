package pages.forms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class FilterMenu extends Form {
    private static final String TRIM_CHECKBOX_LOCATOR = "//div[@id ='trim']//label[contains(@for, '%s')]";
    private final IComboBox maxYearSelectBox = getElementFactory()
            .getComboBox(By.id("year_year_max_select"), "Max year select box.");

    public FilterMenu() {
        super(By.xpath("//div[contains(@class, 'guided')]"), "Filter menu");
    }


    public void selectMaxYear(String year) {
        maxYearSelectBox.state().waitForClickable();
        maxYearSelectBox.clickAndSelectByValue(year);
    }

    public void selectTrim(String trimName) {
        String locatorTrimName = String.format(TRIM_CHECKBOX_LOCATOR,
                trimName.replace(" ", "_").toLowerCase());
        ILabel trimCheckbox = getElementFactory()
                .findElements(By.xpath(locatorTrimName), ILabel.class,
                        ElementState.EXISTS_IN_ANY_STATE)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Trim %s hasn't been found", trimName)));
        trimCheckbox.getJsActions().scrollIntoView();
        trimCheckbox.click();
    }
}
