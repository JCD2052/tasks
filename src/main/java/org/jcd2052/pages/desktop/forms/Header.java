package org.jcd2052.pages.desktop.forms;

import aquality.selenium.elements.interfaces.IButton;
import org.jcd2052.pages.HeaderTab;
import org.jcd2052.pages.AbstractHomePageNavigation;
import org.openqa.selenium.By;

public class Header extends AbstractHomePageNavigation {
    private static final String TAB_TEMPLATE = "//li//a[contains(text(), '%s')]";

    public void selectTab(HeaderTab headerTab) {
        getTabButton(headerTab).click();
    }

    private IButton getTabButton(HeaderTab tab) {
        return getElementFactory()
                .getButton(By.xpath(String.format(TAB_TEMPLATE, tab.getTabName())),
                        tab.getTabName());
    }
}
