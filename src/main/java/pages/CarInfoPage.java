package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import org.openqa.selenium.By;
import utils.StringUtils;

import java.util.List;

public class CarInfoPage extends BasePage {
    private static final String TRIM_LIST_LOCATOR = "//ul[contains(@class,'trim')]//li//a";
    private static final String TRIM_PRICE_LOCATOR = "//following-sibling::p";

    public CarInfoPage() {
        super(By.xpath("//div[@class  = 'header-container']"), "Car Page");
    }

    public boolean checkTrimAvailability() {
        return !getAllTrimLinks().isEmpty();
    }

    public void selectTrimByPosition(int position) {
        getTrimByPosition(position).click();
    }

    public int getTrimPrice(int position) {
        String priceString = getTrimByPosition(position)
                .findChildElement(By.xpath(TRIM_PRICE_LOCATOR), ILabel.class)
                .getText();
        return StringUtils.extractNumbersFromStringAndReturnInt(priceString);
    }

    public String getTrimNameByPosition(int position) {
        return getTrimByPosition(position).getText();
    }

    private ILink getTrimByPosition(int position) {
        return getAllTrimLinks().get(position - 1);
    }

    private List<ILink> getAllTrimLinks() {
        return getElementFactory().findElements(By.xpath(TRIM_LIST_LOCATOR), ElementType.LINK);
    }
}