package tests.steps;

import models.BaseCarInfo;
import models.CarInfo;
import models.CarTrimInfo;
import org.testng.Assert;
import pages.CarInfoPage;
import pages.ResearchPage;
import pages.TrimInfoPage;

import java.util.Optional;

public class CarTestsSteps {
    private static final ResearchPage researchPage = new ResearchPage();
    private static final CarInfoPage carInfoPage = new CarInfoPage();

    public static CarInfo getRandomCarInfoUntilAvailable(int trimPosition) {
        BaseCarInfo randomBaseInfo = getRandomBaseInfo();
        Optional<CarTrimInfo> carTrimInfo = getTrimInfoAsOptional(randomBaseInfo, trimPosition);
        while (carTrimInfo.isEmpty()) {
            carInfoPage.getHeaderMenu().selectResearchPageFromMenu();
            randomBaseInfo = getRandomBaseInfo();
            carTrimInfo = getTrimInfoAsOptional(randomBaseInfo, trimPosition);
        }
        return new CarInfo(randomBaseInfo, carTrimInfo.get());
    }

    public static CarTrimInfo getCarTrimInfoByPosition(int position) {
        carInfoPage.selectTrimByPosition(position);
        TrimInfoPage trimInfoPage = new TrimInfoPage();
        return new CarTrimInfo(trimInfoPage.getDriveTrainType(),
                trimInfoPage.getSeatsCount(), trimInfoPage.getEngineInfo());
    }

    public static void assertCarTrimInfo(CarTrimInfo firstTrimInfo, CarTrimInfo secondTrimInfo) {
        Assert.assertEquals(firstTrimInfo.drivetrainType(), secondTrimInfo.drivetrainType(),
                "Drivetrains are not matched.");
        Assert.assertEquals(firstTrimInfo.engine(), secondTrimInfo.engine(),
                "Engines are not matched.");
        Assert.assertEquals(firstTrimInfo.seatsCount(), secondTrimInfo.seatsCount(),
                "Seats count are not matched.");
    }

    private static Optional<CarTrimInfo> getTrimInfoAsOptional(BaseCarInfo baseCarInfo,
                                                               int position) {
        researchPage.selectBaseCarInfo(baseCarInfo);
        if (!carInfoPage.checkTrimAvailability()) {
            return Optional.empty();
        }
        CarTrimInfo carTrimInfo = getCarTrimInfoByPosition(position);
        return Optional.of(carTrimInfo);
    }

    private static BaseCarInfo getRandomBaseInfo() {
        String maker = researchPage.selectAndGetRandomValueFromMakerSelector();
        String model = researchPage.selectAndGetRandomValueFromModelSelector();
        String year = researchPage.selectAndGetRandomValueFromYearSelector();
        return new BaseCarInfo(maker, model, year);
    }
}
