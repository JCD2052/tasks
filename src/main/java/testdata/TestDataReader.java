package testdata;

import utils.PropertiesReader;

import java.io.IOException;

public class TestDataReader {
    public static String read(String property) throws IOException {
        return PropertiesReader
                .read("src/main/java/testdata/test.data.properties")
                .getProperty(property);
    }
}