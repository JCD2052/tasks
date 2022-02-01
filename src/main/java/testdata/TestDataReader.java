package testdata;

import utils.PropertyReader;

public class TestDataReader extends PropertyReader {

    @Override
    protected String propertyPath() {
        return "src/main/java/testdata/testdata.properties";
    }
}
