package config;

import utils.PropertyReader;

import java.util.Properties;

public class ConfigReader {

    public static Properties getPropertiesContent() {
        return PropertyReader.read("src/main/java/config/email.host.config.properties");
    }

    public static String getProperty(String property) {
        return getPropertiesContent().getProperty(property);
    }
}
