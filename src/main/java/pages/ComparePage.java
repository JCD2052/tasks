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
    public final CarMenu carForm;
    public final ResultPage resultPage;

    public ComparePage() {
        super(By.xpath(BASE_LOCATOR), "Compare Page");
        this.carForm = new CarMenu();
        this.resultPage = new ResultPage();
    }

    public void compareCars(CarInfo firstCar, CarInfo secondCar) {
        addCarToCompare(1, firstCar)
                .addCarToCompare(2, secondCar)
                .clickToCompare();
    }

    private ComparePage addCarToCompare(int position, CarInfo carInfo) {
        ILink lnkAddCard = getElementFactory()
                .getLink(By.xpath(String.format(BASE_ADD_CAR_LOCATOR, position)), "First Car Card");
        lnkAddCard.state().waitForClickable();
        lnkAddCard.getMouseActions().doubleClick();
        carForm.addCar(carInfo.getMaker(), carInfo.getModel(), carInfo.getYear(),
                carInfo.getTrimInfo().getStyle());
        return this;
    }

    private void clickToCompare() {
        btnCompare.click();
    }
}
