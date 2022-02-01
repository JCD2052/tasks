package config;

import utils.PropertyReader;

public class ConfigReader extends PropertyReader {

    @Override
    protected String propertyPath() {
        return "src/main/java/config/config.properties";
    }
}