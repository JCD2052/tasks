package org.jcd2052.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/main/java/org/jcd2052/testdata/testdata.properties"})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface TestDataConfig extends Config {
    @Key("baseUrl")
    String getBaseUrl();

    @Key("expectedNumbersInScene")
    int expectedNumbersInScene();

    @Key("tempPath")
    String getTempPath();

    @Key("red")
    int getRed();

    @Key("green")
    int getGreen();

    @Key("blue")
    int getBlue();

}
