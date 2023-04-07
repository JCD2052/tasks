package org.jcd2052.pages;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractAboutUsPage extends Form {
    private static final String YEAR_BUTTON_TEMPLATE = "//div[@class = 'year']//span[text()='%d']";
    private static final String INFO_LOCATOR = "//div[@class ='round-bg ']";
    private final IButton nextYearButton = getElementFactory().getButton(
            By.xpath("//div[@class = 'story-timeline']//div[@class = 'owl-next']"),
            "Next Year Button.");

    protected AbstractAboutUsPage() {
        super(By.xpath("//div[@class = 'story-timeline']"),
                "About Us Page.");
    }

    public abstract String getAllTextFromInfo();

    public void selectYear(int year) {
        IButton yearButton = getYearButton(year);
        while (!yearButton.state().isDisplayed()) {
            nextYearButton.click();
        }
        yearButton.getJsActions().scrollIntoView();
        yearButton.getJsActions().click();
    }

    protected IButton getYearButton(int year) {
        return getElementFactory()
                .getButton(By.xpath(String.format(YEAR_BUTTON_TEMPLATE, year)),
                        year + " year button", ElementState.EXISTS_IN_ANY_STATE);
    }

    protected String getTextFromInfoWindow() {
        return getInfoLabels().stream()
                .map(ILabel::getText)
                .filter(text -> !text.isEmpty() || !text.isBlank())
                .collect(Collectors.joining("\n"));
    }

    protected List<ILabel> getInfoLabels() {
        return getElementFactory().findElements(By.xpath(INFO_LOCATOR), ILabel.class,
                ElementState.EXISTS_IN_ANY_STATE);
    }
}