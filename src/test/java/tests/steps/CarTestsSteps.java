package tests.steps;

import models.CarInfo;
import models.CarTrimInfo;
import models.SelectInfo;
import org.testng.Assert;
import pages.CarInfoPage;
import pages.ResearchPage;
import pages.TrimInfoPage;

import java.util.Optional;

public class CarTestsSteps {
    private static final ResearchPage researchPage = new ResearchPage();
    private static final CarInfoPage carInfoPage = new CarInfoPage();

    public static CarInfo getRandomCarInfoUntilAvailable(int trimPosition) {
        SelectInfo randomSelectInfo = getRandomSelectInfo();
        Optional<CarTrimInfo> carTrimInfo = getTrimInfoAsOptional(randomSelectInfo, trimPosition);
        while (!carTrimInfo.isPresent()) {
            carInfoPage.getHeaderMenu().selectResearchPageFromMenu();
            randomSelectInfo = getRandomSelectInfo();
            carTrimInfo = getTrimInfoAsOptional(randomSelectInfo, trimPosition);
        }
        return new CarInfo(randomSelectInfo.getMaker(), randomSelectInfo.getModel(),
                randomSelectInfo.getYear(), carTrimInfo.get());
    }

    public static CarTrimInfo getCarTrimInfoByPosition(int position) {
        carInfoPage.selectTrimByPosition(position);
        TrimInfoPage trimInfoPage = new TrimInfoPage();
        return new CarTrimInfo(trimInfoPage.getDriveTrainType(),
                trimInfoPage.getSeatsCount(), trimInfoPage.getEngineInfo());
    }

    public static void assertCarTrimInfo(CarTrimInfo firstTrimInfo, CarTrimInfo secondTrimInfo) {
        Assert.assertEquals(firstTrimInfo.getDrivetrainType(), secondTrimInfo.getDrivetrainType(),
                "Drivetrains are not matched.");
        Assert.assertEquals(firstTrimInfo.getEngine(), secondTrimInfo.getEngine(),
                "Engines are not matched.");
        Assert.assertEquals(firstTrimInfo.getSeatsCount(), secondTrimInfo.getSeatsCount(),
                "Seats count are not matched.");
    }

    private static Optional<CarTrimInfo> getTrimInfoAsOptional(SelectInfo selectInfo, int position) {
        researchPage.selectBaseCarInfo(selectInfo);
        if (!carInfoPage.checkTrimAvailability()) {
            return Optional.empty();
        }
        CarTrimInfo carTrimInfo = getCarTrimInfoByPosition(position);
        return Optional.of(carTrimInfo);
    }

    private static SelectInfo getRandomSelectInfo() {
        String maker = researchPage.selectAndGetRandomValueFromMakerSelector();
        String model = researchPage.selectAndGetRandomValueFromModelSelector();
        String year = researchPage.selectAndGetRandomValueFromYearSelector();
        return new SelectInfo(maker, model, year);
    }
}
