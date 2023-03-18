package pages;

import aquality.selenium.elements.interfaces.IButton;
import pages.forms.AddCarToCompareCardForm;
import models.CarInfo;
import org.openqa.selenium.By;

public class ComparePage extends BasePage {
    private final IButton btnCompare = getElementFactory()
            .getButton(By.xpath("//div[contains(@class, 'submit')]//button"),
                    "Compare");

    public ComparePage() {
        super(By.xpath("//div[contains(@class, 'comparison-box')]"),
                "Compare Page");
    }

    public void startCarsComparison(CarInfo... cars) {
        for (int i = 0; i < cars.length; i++) {
            new AddCarToCompareCardForm(i + 1).addCarToCompare(cars[i]);
        }
        btnCompare.state().waitForClickable();
        btnCompare.click();
    }
}
