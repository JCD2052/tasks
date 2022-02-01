package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class PropertyReader {

    private static Properties read(String path) {
        try {
            InputStream input = new FileInputStream(path);
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public String get(String property) {
        return read(propertyPath()).getProperty(property);
    }

    protected abstract String propertyPath();
}