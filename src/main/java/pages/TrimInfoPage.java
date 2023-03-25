package pages;

import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.By;
import utils.StringUtils;

public class TrimInfoPage extends BasePage {
    private static final String BASE_TEMPLATE_FOR_LOCATOR = "//label[contains(@role, 'presentation') and(contains(text(), '%s'))]";
    private static final String HORSE_POWER_ABBREVIATION = "hp";
    private static final String HORSE_POWER_ABBREVIATION_WITH_DECIMAL = ".0" + HORSE_POWER_ABBREVIATION;

    private final ILabel drivetrainType = getElementFactory()
            .getLabel(By.xpath(String.format(BASE_TEMPLATE_FOR_LOCATOR, "Drive")),
                    "Door count label");
    private final ILabel seatsCountLabel = getElementFactory()
            .getLabel(By.xpath(String.format(BASE_TEMPLATE_FOR_LOCATOR, "seats")),
                    "Seats count label.");
    private final ILabel engineLabel = getElementFactory()
            .getLabel(By.xpath(String.format(BASE_TEMPLATE_FOR_LOCATOR, "-liter")),
                    "Engine label.");

    public TrimInfoPage() {
        super(By.xpath("//div[@class = 'vehicle-display']"), "Trim Info Page.");
    }

    public String getDriveTrainType() {
        return drivetrainType.getText();
    }

    public int getSeatsCount() {
        return StringUtils.extractNumbersFromStringAndReturnInt(seatsCountLabel.getText());
    }

    public String getEngineInfo() {
        return engineLabel.getText()
                .replace(HORSE_POWER_ABBREVIATION_WITH_DECIMAL, HORSE_POWER_ABBREVIATION)
                .replace(" (", "(");
    }
}