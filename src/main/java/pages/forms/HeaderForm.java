package pages.forms;

import aquality.selenium.elements.interfaces.ILink;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.DownloadsPage;

public class HeaderForm extends BasePage {
    private final ILink lnkDownloads = getElementFactory()
            .getLink(By.xpath("//a[contains(@data-at-menu, 'Downloads')]"), "Downloads");

    public HeaderForm() {
        super(By.id("site-header"), "Header");
    }

    public DownloadsPage goToDownloads() {
        lnkDownloads.click();
        return new DownloadsPage();
    }
}