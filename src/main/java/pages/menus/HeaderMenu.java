package pages.menus;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import pages.ResearchPage;

public class HeaderMenu extends Form {
    private static final String FORM_ID = "//div[@id='mobile-menu-section']";
    private final ILink lnkResearch = getElementFactory()
            .getLink(By.xpath(FORM_ID + "//a[contains(@data-linkname, 'research')]"), "Research");

    public HeaderMenu() {
        super(By.xpath(FORM_ID), "Header");
    }

    public ResearchPage goToResearchPage() {
        lnkResearch.getJsActions().click();
        return new ResearchPage();
    }
}
