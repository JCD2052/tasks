package org.jcd2052.utils;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageUtils {
    private ImageUtils() {

    }

    public static List<Point> getPointsWithCertainRGBInImage(BufferedImage originImage,
                                                             Color rgbToFind) {
        int height = originImage.getHeight();
        int width = originImage.getWidth();
        List<Point> points = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixelRgb = originImage.getRGB(x, y);
                if (pixelRgb == rgbToFind.getRGB()) {
                    points.add(new Point(x, y));
                }
            }
        }
        return points;
    }

    public static BufferedImage getLosslessImage(BufferedImage image, String path) {
        ImageUtils.writeImage(image, ImageType.PNG, path);
        return ImageUtils.readImage(path);
    }

    public static BufferedImage readImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new IllegalStateException(String.format(
                    "Couldn't read an image on path: %s.%n %s", file.getPath(), e));
        }
    }

    public static BufferedImage readImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new IllegalStateException(String.format(
                    "Couldn't read an image on path: %s.%n %s", path, e));
        }
    }

    public static void writeImage(BufferedImage image, ImageType imageType, String path) {
        try {
            ImageIO.write(image, imageType.getType(), new File(path));
        } catch (IOException e) {
            throw new IllegalStateException(String
                    .format("Couldn't write an image with type: %s on path: %s.%n %s",
                            imageType.getType(), path, e));
        }
    }
}
