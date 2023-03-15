package pages;

import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.By;
//import pages.menus.FilterMenu;

import java.util.List;

public class SearchResultPage extends BasePage {
    private static final String VEHICLE_CARD_LOCATOR = "//div[contains(@id, 'vehicle-card')]";
//    private final FilterMenu filterMenu = new FilterMenu();

    public SearchResultPage() {
        super(By.id("vehicle-cards-container"), "Search Results Page");
    }

//    public FilterMenu getFilterMenu() {
//        return filterMenu;
//    }

    private List<ILabel> getVehicleCards() {
        return getElementFactory().findElements(By.xpath(VEHICLE_CARD_LOCATOR), ILabel.class);
    }
}
