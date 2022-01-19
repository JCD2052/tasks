package utils;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.IElement;
import org.openqa.selenium.By;
import java.util.List;
import java.util.stream.Collectors;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public class WebElementsUtils {
    public static String getTextFromSelectMenu(IComboBox selectMenu) {
        return selectMenu.getSelectedText();
    }

    public static <T extends IElement> List<String> geTextFromElements(By locator, Class<T> elementClass){
        return getElementFactory()
                .findElements(locator,elementClass)
                .stream()
                .map(IElement::getText)
                .collect(Collectors.toList());
    }
}