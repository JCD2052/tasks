package testdata;

import utils.PropertyReader;

public class TestDataReader {
    public static String get(String property) {
        return PropertyReader
                .read("src/main/java/testdata/testdata.properties")
                .getProperty(property);
    }
}
