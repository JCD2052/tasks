package org.jcd2052.pages.mobile.forms;

import aquality.selenium.elements.interfaces.IButton;
import org.jcd2052.pages.AbstractHomePageNavigation;
import org.jcd2052.pages.HeaderTab;
import org.openqa.selenium.By;

public class HomePageMenu extends AbstractHomePageNavigation {
    private static final String TAB_TEMPLATE = "//div[@class = 'nav-title']//a[contains(text(), '%s')]";
    private static final String EXPANDED_ATTRIBUTE = "aria-expanded";
    private static final By menuLocator = By.xpath("//div[contains(@class, 'menu-box')]/a");
    private final IButton menuButton = getElementFactory().getButton(menuLocator,
            "Menu Button");

    @Override
    public void selectTab(HeaderTab tab) {
        if (!isMenuOpened()) {
            menuButton.click();
        }
        getTabButton(tab).click();
    }

    private IButton getTabButton(HeaderTab tab) {
        return getElementFactory()
                .getButton(By.xpath(String.format(TAB_TEMPLATE, tab.getTabName())),
                        tab.getTabName());
    }

    private boolean isMenuOpened() {
        return Boolean.parseBoolean(menuButton.getAttribute(EXPANDED_ATTRIBUTE));
    }
}