package pages.menus;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.forms.Form;
import models.CarInfo;
import org.openqa.selenium.By;
import utils.WebElementsUtils;

public class CarMenu extends Form {
    private final IButton btnClose = getElementFactory()
            .getButton(By.xpath("//div[contains(@phx-click, 'close') and contains(@phx-click, 'modal')]"),
                    "Close");
    private final IComboBox sltMaker = getElementFactory().getComboBox(By.id("vehicle_selection_make"), "Maker");
    private final IComboBox sltModel = getElementFactory().getComboBox(By.id("vehicle_selection_model"), "Model;");
    private final IComboBox sltYear = getElementFactory().getComboBox(By.id("vehicle_selection_year"), "Year");
    private final IComboBox sltStyle = getElementFactory().getComboBox(By.id("vehicle_selection_trim"), "Trim");
    private final IButton btnSubmit = getElementFactory()
            .getButton(By.xpath("//button[contains(@type, 'submit')]"), "Add car");

    public CarMenu() {
        super(By.xpath("//div[contains(@class, 'research-search')]"), "Add Car Form");
    }

    public void addCar(CarInfo carInfo) {
        sltMaker.clickAndSelectByText(carInfo.getMaker());
        sltModel.state().waitForClickable();
        sltModel.clickAndSelectByText(carInfo.getModel());
        sltModel.state().waitForClickable();
        sltYear.clickAndSelectByText(carInfo.getYear());
        sltStyle.state().waitForClickable();
        sltStyle.clickAndSelectByText(carInfo.getTrimInfo().getStyle());
        btnSubmit.state().waitForClickable();
        btnSubmit.click();
    }

    public void closeForm() {
        btnClose.click();
    }

    public String getDataFromModelSelector() {
        return WebElementsUtils.getTextFromSelectMenu(sltModel);
    }

    public String getDataFromMakerSelector() {
        return WebElementsUtils.getTextFromSelectMenu(sltMaker);
    }

    public String getDataFromYearSelector() {
        return WebElementsUtils.getTextFromSelectMenu(sltYear);
    }

    public String getDataFromStyleSelector() {
        return WebElementsUtils.getTextFromSelectMenu(sltStyle);
    }
}
