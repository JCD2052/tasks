import org.jcd2052.config.PropertyConfig;
import org.jcd2052.config.TestDataConfig;
import org.jcd2052.utils.ImageUtils;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

public class RoomStylerTestSteps {
    private static final TestDataConfig testData = PropertyConfig.TEST_DATA_CONFIG.getConfig();
    private static final Color PRODUCT_BORDERS_COLOR = new Color(testData.getRed(),
            testData.getGreen(), testData.getBlue());

    public static BufferedImage getProductImage(BufferedImage screenImage) {
        return ImageUtils.getLosslessImage(getRawProductSubImage(screenImage),
                testData.getTempPath());
    }

    private static BufferedImage getRawProductSubImage(BufferedImage screenImage) {
        List<Point> pointList = ImageUtils.getPointsWithCertainRGBInImage(screenImage,
                PRODUCT_BORDERS_COLOR);
        Point firstEntryPoint = pointList.stream().findFirst().orElseThrow();
        Point lastEntryPoint = pointList.stream().reduce((first, second) -> second).orElseThrow();
        int width = lastEntryPoint.x - firstEntryPoint.x;
        int height = lastEntryPoint.y - firstEntryPoint.y;
        return screenImage.getSubimage(firstEntryPoint.x, firstEntryPoint.y, width, height);
    }
}
