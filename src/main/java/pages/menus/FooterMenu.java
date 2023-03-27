package pages.menus;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import pages.ComparePage;

public class FooterMenu extends Form {
    private final static String FORM_ID = "//footer[@id='global-footer']";
    private final ILink lnkCompare = getElementFactory()
            .getLink(By.xpath(FORM_ID + "//a[contains(@data-linkname, 'compare')]"), "Compare");

    public FooterMenu() {
        super(By.xpath(FORM_ID), "Footer");
    }

    public ComparePage goToComparePage() {
        lnkCompare.getJsActions().click();
        return new ComparePage();
    }
}
