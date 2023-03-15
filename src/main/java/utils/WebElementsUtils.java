package utils;

import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.IElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public class WebElementsUtils {
    private WebElementsUtils() {

    }

    public static String getTextFromSelectMenu(IComboBox selectMenu) {
        return selectMenu.getSelectedText();
    }

    public static <T extends IElement> List<String> geTextFromElements(By locator,
                                                                       Class<T> elementClass) {
        return getElementFactory()
                .findElements(locator, elementClass)
                .stream()
                .map(IElement::getText)
                .collect(Collectors.toList());
    }

    public static String selectAndGetRandomValueFromSelectMenu(IComboBox selectMenu) {
        String value = getRandomValueFromSelector(selectMenu);
        selectMenu.selectByValue(value);
        return WebElementsUtils.getTextFromSelectMenu(selectMenu);
    }

    public static String getRandomValueFromSelector(IComboBox selectMenu) {
        List<String> values = selectMenu.getValues();
        int randomValue = RandomUtils.getRandomIntInRange(1, values.size());
        return values.get(randomValue);
    }
}