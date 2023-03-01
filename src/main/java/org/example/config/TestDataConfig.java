package org.example.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/java/testdata/testdata.properties"})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface TestDataConfig extends Config {
    @Key("filename")
    String getFilename();

    @Key("fileText")
    String getFileText();

    @Key("timeoutForSearch")
    int getTimeoutForSearch();

    @Key("timeoutForOpening")
    int getTimeoutForOpening();

    @Key("randomLength")
    int getRandomLength();

}
