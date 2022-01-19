package pages;

import aquality.selenium.elements.interfaces.ILabel;
import models.CarInfo;
import models.CarTrimInfo;
import org.openqa.selenium.By;
import pages.menus.CarMenu;
import utils.StringUtils;
import utils.WebElementsUtils;

public class ResultPage extends BasePage {
    private final static String COLUMN_LOCATOR = "//td[%s]//p";
    private final static String ENGINE_LOCATOR = "//tr[@class = 'table-section engine-section']" +
            "//following-sibling::tr[contains(@class, 'count')][1]";
    private final static String TRANSMISSION_LOCATOR = "//section[contains(@class, 'transmissions')]" +
            "//tr[contains(@class, 'row')]";
    private final static String EDIT_BUTTON_LOCATOR = "//button[contains(@phx-value-vehicle_index, 'vehicle_%s')]";
    private final CarMenu carMenu;

    ResultPage() {
        super(By.xpath("//div[contains(@class, 'vehicle-cards')]"), "Compare Result Page");
        this.carMenu = new CarMenu();
    }

    public CarInfo getCarInfo(int position) {
        openEditForm(position);
        String maker = carMenu.getDataFromMakerSelector();
        String model = carMenu.getDataFromModelSelector();
        String year = carMenu.getDataFromYearSelector();
        String style = carMenu.getDataFromStyleSelector();
        carMenu.closeForm();
        String engine = getEngine(position);
        String transmission = getTransmission(position);
        return CarInfo.builder()
                .maker(maker)
                .model(model)
                .year(year)
                .trimInfo(CarTrimInfo.builder()
                        .style(style)
                        .transmission(transmission)
                        .engine(engine)
                        .build())
                .build();
    }

    private String getEngine(int position) {
        String columnLocator = String.format(COLUMN_LOCATOR, position);
        return WebElementsUtils
                .geTextFromElements(By.xpath(ENGINE_LOCATOR + columnLocator), ILabel.class)
                .stream()
                .map(text -> {
                    if (text.equals("-")) {
                        return null;
                    } else {
                        return StringUtils.removeSpaces(text);
                    }
                })
                .findFirst()
                .orElse(null);
    }

    private String getTransmission(int position) {
        String columnLocator = String.format("//td[%s]//p", position);
        return WebElementsUtils
                .geTextFromElements(By.xpath(TRANSMISSION_LOCATOR + columnLocator), ILabel.class)
                .stream()
                .findFirst()
                .orElse(null);
    }

    private void openEditForm(int position) {
        getElementFactory()
                .getButton(By.xpath(String.format(EDIT_BUTTON_LOCATOR, position)), "Edit Info")
                .click();
    }
}
