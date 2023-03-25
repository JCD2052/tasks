package pages.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import models.BaseCarInfo;
import models.CarInfo;
import models.CarTrimInfo;
import org.openqa.selenium.By;
import utils.StringUtils;

public class CompareCarCardForm extends Form {
    private static final String CAR_NAME_LINK_LOCATOR_TEMPLATE = "(//a[@data-linkname = 'research-mmy'])[%d]";
    private static final String BASE_LOCATOR_TEMPLATE = "(//td[@data-qa = '%s-data'])[%d]//div[@class = 'data-point']";
    private static final String CAR_LINK = "https://www.cars.com/research/";
    private static final String ENGINE_INFO_TEMPLATE = "%s, %s";
    private final ILink carNameLink;
    private final ILabel driveTrainLabel;
    private final ILabel carSeatsLabel;
    private final ILabel engineLabel;
    private final ILabel horsePowersLabel;

    public CompareCarCardForm(int position) {
        super(By.xpath(String.format("(//div[contains(@class,'research-compare-details-card card-count')])[%d]", position)),
                "Compare Car Card form.");
        this.driveTrainLabel = createLabelByPosition("drivetrain", position);
        this.carSeatsLabel = createLabelByPosition("seat-count", position);
        this.engineLabel = createLabelByPosition("engine", position);
        this.horsePowersLabel = createLabelByPosition("horsepower", position);
        this.carNameLink = getElementFactory()
                .getLink(By.xpath(String.format(CAR_NAME_LINK_LOCATOR_TEMPLATE, position)),
                        "Car Name Link.");
    }

    public CarInfo getCarInfo() {
        String[] baseCarInfo = StringUtils.substringAfterOrReturnOrigin(getBaseCarInfo(), CAR_LINK)
                .split("-");
        String maker = baseCarInfo[0];
        String model = baseCarInfo[1];
        String year = baseCarInfo[2].replace("/", "");
        String engine = String.format(ENGINE_INFO_TEMPLATE, getHorsePower(), getEngine());
        String driveTrain = getDriveTrain();
        int seats = getSeatsCount();
        CarTrimInfo carTrimInfo = new CarTrimInfo(driveTrain, seats, engine);
        return new CarInfo(new BaseCarInfo(maker, model, year), carTrimInfo);
    }

    private String getBaseCarInfo() {
        return this.carNameLink.getHref();
    }

    private String getDriveTrain() {
        return this.driveTrainLabel.getText();
    }

    private String getHorsePower() {
        return this.horsePowersLabel.getText();
    }

    private String getEngine() {
        return this.engineLabel.getText();
    }

    private int getSeatsCount() {
        return StringUtils.stringToNumber(this.carSeatsLabel.getText());
    }

    private static ILabel createLabelByPosition(String name, int position) {
        return AqualityServices.getElementFactory()
                .getLabel(By.xpath(String.format(BASE_LOCATOR_TEMPLATE, name, position)),
                        name + " Label.");
    }
}