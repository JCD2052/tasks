package org.jcd2052.pages.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.jcd2052.utils.StringUtils;
import org.openqa.selenium.By;

public class SceneForm extends Form {
    private static final String FORM_LOCATOR = "//div[@class = 'scene']";
    private static final String LABEL_LOCATOR_TEMPLATE = FORM_LOCATOR + "//b[@class = '%sCount']";
    private final ILabel windowsCountLabel = createCountLabel("windows");
    private final ILabel doorsCountLabel = createCountLabel("doors");
    private final ILabel wallsCountLabel = createCountLabel("walls");
    private final ILabel productsCountLabel = createCountLabel("components");
    private final ILabel materialsCountLabel = createCountLabel("materials");

    public SceneForm() {
        super(By.xpath(FORM_LOCATOR), "Scene Form.");
    }

    public int getElementsCountInScene() {
        return StringUtils.sumOfIntegersFromStrings(getDoorsCountText(), getWallsCountText(),
                getProductsCountText(), getWindowsCountText(), getMaterialsCountText());
    }

    private String getWallsCountText() {
        return wallsCountLabel.getText();
    }

    private String getDoorsCountText() {
        return doorsCountLabel.getText();
    }

    private String getWindowsCountText() {
        return windowsCountLabel.getText();
    }

    private String getProductsCountText() {
        return productsCountLabel.getText();
    }

    private String getMaterialsCountText() {
        return materialsCountLabel.getText();
    }

    private static ILabel createCountLabel(String name) {
        return AqualityServices.getElementFactory()
                .getLabel(By.xpath(String.format(LABEL_LOCATOR_TEMPLATE, name)),
                        name + " + Label.");
    }
}
