package pages.forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HeaderForm extends Form {
    private static final String HEADER_LOCATOR = "//header";
    private final ILink researchTabLink = getElementFactory()
            .getLink(By.xpath(HEADER_LOCATOR + "//a[contains(@data-linkname, 'research')]"),
                    "Research");
    private final ILink carsForSaleTabLink = getElementFactory()
            .getLink(By.xpath(HEADER_LOCATOR + "//a[contains(@data-linkname, 'buy')]"),
                    "Cars for sale");

    public HeaderForm() {
        super(By.xpath(HEADER_LOCATOR), "Header");
    }

    public void selectResearchPageFromMenu() {
        researchTabLink.getJsActions().click();
    }

    public void selectCarsForSalesPageFromMenu() {
        carsForSaleTabLink.getJsActions().click();
    }
}
