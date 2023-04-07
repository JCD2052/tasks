package org.jcd2052.pages.mobile;

import aquality.selenium.elements.interfaces.IButton;
import org.jcd2052.pages.AbstractAboutUsPage;
import org.openqa.selenium.By;

import java.util.List;

public class MobileAboutUsPage extends AbstractAboutUsPage {
    private static final String NEXT_INFO_TEXT_DOT_LOCATOR =
            "//div[@class = 'about-gallery os-animation animated fadeInUp']//div[@class = 'owl-dot']";

    public String getAllTextFromInfo() {
        StringBuilder builder = new StringBuilder(getTextFromInfoWindow());
        getDotsButton().forEach(iButton -> {
            iButton.getJsActions().click();
            builder.append(getTextFromInfoWindow());
        });
        return builder.toString();
    }

    private List<IButton> getDotsButton() {
        return getElementFactory().findElements(By.xpath(NEXT_INFO_TEXT_DOT_LOCATOR),
                IButton.class);
    }
}