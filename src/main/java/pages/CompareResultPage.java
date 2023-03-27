package pages;

import models.CarInfo;
import org.openqa.selenium.By;
import pages.forms.CompareCarCardForm;

public class CompareResultPage extends BasePage {
    public CompareResultPage() {
        super(By.xpath("//div[contains(@class, 'vehicle-cards')]"),
                "Compare Result Page");
    }

    public CarInfo getCarInfoByPosition(int position) {
        return new CompareCarCardForm(position).getCarInfo();
    }
}