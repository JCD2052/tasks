package org.example.screens.forms;

import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.Screen;
import org.example.screens.categories.Category;
import org.openqa.selenium.By;

import static io.appium.java_client.AppiumBy.accessibilityId;

public abstract class BaseForm extends Screen {
    protected final IButton dragHandler = getElementFactory()
            .getButton(accessibilityId("Drag handle"), "Drag Handler");
    protected static final String CATEGORY_BASE_LOCATOR_TEMPLATE =
            "//android.widget.TextView[@text ='%s']";

    protected BaseForm(By locator, String name) {
        super(locator, name);
    }

    public void performAction(Category category) {
        dragHandler.click();
        getCategoryElement(category).click();
    }

    private IButton getCategoryElement(Category category) {
        String locator = String.format(CATEGORY_BASE_LOCATOR_TEMPLATE, category.getLocatorValue());
        return getElementFactory().getButton(By.xpath(locator),
                category.getElementName());
    }
}
