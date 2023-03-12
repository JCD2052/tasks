package org.jcd2052.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/java/testdata/testdata.properties"})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface TestDataConfig extends Config {
    @Key("filename")
    int getPosition();
}