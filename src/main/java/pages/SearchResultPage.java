package pages;

import aquality.selenium.elements.interfaces.ILabel;
import models.pagemodels.CarCard;
import org.openqa.selenium.By;
import pages.forms.FilterMenu;
import utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultPage extends BasePage {
    private static final String VEHICLE_CARD_LOCATOR = "//div[@id='vehicle-cards-container']//div[contains(@id, 'vehicle-card')]";
    private static final String VEHICLE_DETAILS_LOCATOR = "//div[@class='vehicle-details']";
    private static final String CAR_PRICE_LOCATOR = "//span[@class = 'primary-price']";
    private final FilterMenu filterMenu = new FilterMenu();

    public SearchResultPage() {
        super(By.id("vehicle-cards-container"), "Search Results Page");
    }

    public FilterMenu getFilterMenu() {
        return filterMenu;
    }

    public boolean isContentFound() {
        return !getVehicleCards().isEmpty();
    }

    public CarCard getFirstCard() {
        return getVehicleCards()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(""));
    }

    private List<CarCard> getVehicleCards() {
        return getElementFactory()
                .findElements(By.xpath(VEHICLE_CARD_LOCATOR), ILabel.class)
                .stream()
                .map(iLabel -> {
                    String priceString = iLabel.findChildElement(
                            By.xpath(VEHICLE_DETAILS_LOCATOR + CAR_PRICE_LOCATOR),
                            ILabel.class).getText();
                    return new CarCard(StringUtils.extractNumbersFromStringAndReturnInt(priceString));
                })
                .collect(Collectors.toList());
    }
}
