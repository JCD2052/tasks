package org.jcd2052.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/main/java/org/jcd2052/testdata/baseconfig.properties"})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface BaseConfig extends Config {
    @Key("baseUrl")
    String baseUrl();
}
