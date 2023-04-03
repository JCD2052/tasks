package org.jcd2052.pages.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.jcd2052.utils.RandomUtils;
import org.openqa.selenium.By;

import java.util.List;

public class FacetCategoriesForm extends Form {
    private static final String SIDE_BAR_CATEGORY_TEMPLATE = "//a[text() = '%s']";
    private static final String ITEMS_LOCATOR =
            "//div[@id = 'view-search']//ul[@class = 'items']//li";

    public FacetCategoriesForm() {
        super(By.xpath("//div[@class = 'facet-category']//ul[@class = 'selected']"),
                "Facet Categories Form");
    }

    public void selectCategory(ISideBarTab sideBarChoice) {
        String sideBarTabName = sideBarChoice.getTabName();
        String locator = String.format(SIDE_BAR_CATEGORY_TEMPLATE, sideBarTabName);
        getElementFactory()
                .getButton(By.xpath(locator), "SideBar button " + sideBarTabName)
                .click();
    }

    public IButton getRandomProduct() {
        return RandomUtils.getRandomElementFromList(getItems());
    }

    public boolean waitUntilListLoaded() {
        return getElementFactory()
                .getLabel(By.xpath(ITEMS_LOCATOR), "Products List")
                .state()
                .waitForDisplayed();
    }

    private List<IButton> getItems() {
        return getElementFactory()
                .findElements(By.xpath(ITEMS_LOCATOR), ElementType.BUTTON);
    }
}
