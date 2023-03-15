package pages.forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FooterForm extends Form {
    private static final String FORM_ID = "//footer[@id='global-footer']";
    private final ILink lnkCompare = getElementFactory()
            .getLink(By.xpath(FORM_ID + "//a[contains(@data-linkname, 'compare')]"),
                    "Compare");

    public FooterForm() {
        super(By.xpath(FORM_ID), "Footer");
    }

    public void selectCompare() {
        lnkCompare.getJsActions().click();
    }
}
