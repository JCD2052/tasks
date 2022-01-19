package pages;

import aquality.selenium.elements.interfaces.ILabel;
import models.CarTrimInfo;
import org.openqa.selenium.By;
import testdata.TestDataReader;
import utils.StringUtils;
import utils.WebElementsUtils;

import java.io.IOException;
import java.util.List;

class TrimContentPage extends BasePage {
    private final static String BASE_TRIM_LOCATOR = "//div[contains(@id, 'panel%s')]";
    private final static String TABLE_CONTENT_LOCATOR = "//tr[contains(@class, 'content')]";
    private final static String BUTTON_LOCATOR = "//preceding-sibling::h2//button";

    TrimContentPage() {
        super(By.xpath("//div[contains(@class, 'trim-summary')]"), "Trim Content");
    }

    public boolean checkTrimAvailability() {
        return !getElementFactory()
                .findElements(By.xpath("//div[@class = 'sds-accordion']"), ILabel.class)
                .isEmpty();
    }

    public CarTrimInfo getTrimData(int position) throws IOException {
        String locator = String.format(BASE_TRIM_LOCATOR, position);
        getElementFactory().getButton(By.xpath(locator + BUTTON_LOCATOR), "Show").getJsActions().click();
        List<String> cells = WebElementsUtils
                .geTextFromElements(By.xpath(locator + TABLE_CONTENT_LOCATOR + "//td"), ILabel.class);
        int columnSize = getElementFactory()
                .findElements(By.xpath(locator + TABLE_CONTENT_LOCATOR + "[1]//td"), ILabel.class)
                .size();
        String style = cells.get(TableIndexes.STYLE);
        String engine = cells.get(TableIndexes.ENGINE * columnSize)
                .equals(TestDataReader.read("NO_ENGINE_INFO")) ?
                null : StringUtils.removeSpaces(cells.get(TableIndexes.ENGINE * columnSize));
        String transmission = cells.get(TableIndexes.TRANSMISSION * columnSize);
        return new CarTrimInfo(style, engine, transmission);
    }

    private static class TableIndexes {
        private final static int STYLE = 0;
        private final static int ENGINE = 3;
        private final static int TRANSMISSION = 4;
    }
}