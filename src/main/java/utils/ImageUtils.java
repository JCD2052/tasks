package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageUtils {
    public static boolean checkImages(String photoUrl, String filePath) {
        try {
            BufferedImage imgFromNet = ImageIO.read(new URL(photoUrl));
            BufferedImage imgFromHome = ImageIO.read(new File(filePath));
            return imgFromHome.equals(imgFromNet);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
