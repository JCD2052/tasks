package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import pages.forms.FooterForm;
import pages.forms.HeaderForm;

public abstract class BasePage extends Form {
    private final FooterForm footerForm;
    private final HeaderForm headerForm;

    protected BasePage(By locator, String name) {
        super(locator, name);
        this.footerForm = new FooterForm();
        this.headerForm = new HeaderForm();
    }

    public boolean waitForLoad() {
        return this.state().waitForDisplayed();
    }

    public FooterForm getFooterMenu() {
        return footerForm;
    }

    public HeaderForm getHeaderMenu() {
        return headerForm;
    }
}