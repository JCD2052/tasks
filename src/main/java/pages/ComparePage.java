package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import models.CarInfo;
import org.openqa.selenium.By;
import pages.menus.CarMenu;

public class ComparePage extends BasePage {
    private static final String BASE_LOCATOR = "//div[contains(@class, 'comparison-box')]";
    private static final String BASE_ADD_CAR_LOCATOR = "//div[contains(@class, 'card%s')]//a";
    private final IButton btnCompare = getElementFactory()
            .getButton(By.xpath(BASE_LOCATOR + "//button"), "Compare");
    private final CarMenu carForm;

    public ComparePage() {
        super(By.xpath(BASE_LOCATOR), "Compare Page");
        this.carForm = new CarMenu();
    }

    public ResultPage startCarsComparison(CarInfo... cars) {
        for (int i = 0; i < cars.length; i++) {
            addCarToCompare(i+1, cars[i]);
        }
        clickToCompare();
        return new ResultPage();
    }

    private void addCarToCompare(int position, CarInfo carInfo) {
        ILink lnkAddCard = getElementFactory()
                .getLink(By.xpath(String.format(BASE_ADD_CAR_LOCATOR, position)), "Car Card " + position);
        lnkAddCard.state().waitForClickable();
        lnkAddCard.getMouseActions().doubleClick();
        carForm.addCar(carInfo);
    }

    private void clickToCompare() {
        btnCompare.click();
    }
}
