package org.jcd2052.pages.forms;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.jcd2052.models.ProductDimension;
import org.jcd2052.utils.StringUtils;
import org.openqa.selenium.By;

public class ProductInfoForm extends Form {
    private static final String FORM_LOCATOR = "//div[contains(@class, 'component empty')]";
    private static final String HEIGHT_OPEN_TAG = "H";
    private static final String WIDTH_OPEN_TAG = "W";
    private static final String LENGTH_OPEN_TAG = "D";
    private static final String LENGTH_CLOSE_TAG = "m";
    private final ILabel productDimensionsLabel = getElementFactory()
            .getLabel(By.xpath(FORM_LOCATOR + "//p[@class = 'dimensions']"),
                    "Product dimensions label.");

    public ProductInfoForm() {
        super(By.xpath(FORM_LOCATOR), "Product Info Form.");
    }

    public ProductDimension getProductDimensions() {
        String dimensions = getProductDimensionsText();
        double height = StringUtils.getDoubleFromString(
                StringUtils.substringBetweenOrReturnOriginal(dimensions, HEIGHT_OPEN_TAG,
                        WIDTH_OPEN_TAG));
        double width = StringUtils.getDoubleFromString(
                StringUtils.substringBetweenOrReturnOriginal(dimensions, WIDTH_OPEN_TAG,
                        LENGTH_OPEN_TAG));
        double length = StringUtils.getDoubleFromString(
                StringUtils.substringBetweenOrReturnOriginal(dimensions, LENGTH_OPEN_TAG,
                        LENGTH_CLOSE_TAG));
        return new ProductDimension(height, width, length);
    }

    private String getProductDimensionsText() {
        return productDimensionsLabel.getText();
    }
}