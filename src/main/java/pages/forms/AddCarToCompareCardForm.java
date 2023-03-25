package pages.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.forms.Form;
import models.CarInfo;
import org.openqa.selenium.By;

public class AddCarToCompareCardForm extends Form {
    private static final String SELECTOR_TEMPLATE = "(//select[@id = 'vehicle_picker_%s']) [%d]";
    private final IComboBox makerSelectBox;
    private final IComboBox modelSelectBox;
    private final IComboBox yearSelectBox;

    public AddCarToCompareCardForm(int position) {
        super(By.xpath(String.format("(//div[@class = 'mmy-form'])[%d]", position)),
                "Car Card");
        this.makerSelectBox = createComboBoxByPosition("make", position);
        this.modelSelectBox = createComboBoxByPosition("model", position);
        this.yearSelectBox = createComboBoxByPosition("year", position);
    }

    public void addCarToCompare(CarInfo carInfo) {
        makerSelectBox.state().waitForClickable();
        makerSelectBox.clickAndSelectByText(carInfo.baseCarInfo().maker());
        modelSelectBox.state().waitForClickable();
        modelSelectBox.clickAndSelectByText(carInfo.baseCarInfo().model());
        yearSelectBox.state().waitForClickable();
        yearSelectBox.clickAndSelectByText( carInfo.baseCarInfo().year());
    }

    private static IComboBox createComboBoxByPosition(String name, int position) {
        return AqualityServices.getElementFactory()
                .getComboBox(By.xpath(String.format(SELECTOR_TEMPLATE, name, position)),
                        name + " Select box");
    }
}
