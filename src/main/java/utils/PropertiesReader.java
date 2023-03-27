package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public static Properties read(String path) throws IOException {
        InputStream input = new FileInputStream(path);
        Properties properties = new Properties();
        properties.load(input);
        return properties;
    }
}