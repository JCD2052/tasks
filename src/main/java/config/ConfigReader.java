package config;

import utils.PropertyReader;

public class ConfigReader {
    public static String get(String property) {
        return PropertyReader
                .read("src/main/java/config/config.properties")
                .getProperty(property);
    }
}