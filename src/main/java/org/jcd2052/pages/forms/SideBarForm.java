package org.jcd2052.pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SideBarForm extends Form {
    private static final String SIDEBAR_TAB_TEMPLATE = "//a[@title = '%s']";
    private final IButton hideBarButton = getElementFactory()
            .getButton(By.xpath("//*[@id=\"sidebar\"]/div[1]/ul/li[7]"),
                    "Button");

    public SideBarForm() {
        super(By.id("sidebar"), "Side-bar Form.");
    }

    public void selectTab(ISideBarTab sideBarTab) {
        String sideBarTabName = sideBarTab.getTabName();
        String locator = String.format(SIDEBAR_TAB_TEMPLATE, sideBarTabName);
        getElementFactory()
                .getButton(By.xpath(locator), "SideBar button " + sideBarTabName)
                .click();
    }

    public boolean isSidebarHidden() {
        return this.getFormLabel().getAttribute("class").contains("minimized");
    }

    public void hideSideBar() {
        hideBarButton.click();
    }
}
