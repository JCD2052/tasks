package org.jcd2052.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/main/java/org/jcd2052/testdata/testdata.properties"})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface TestDataConfig extends Config {
    @Key("yearToFind")
    int yearToFind();

    @Key("firstTextInfo")
    String firstTextInfo();

    @Key("secondTextInfo")
    String secondTextInfo();
}