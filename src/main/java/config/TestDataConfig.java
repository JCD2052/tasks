package config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/main/java/testdata/test.data.properties"})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface TestDataConfig extends Config {
    @Key("baseUrl")
    String getBaseUrl();

    @Key("trimPosition")
    int getTrimPosition();

    @Key("noEngineInfo")
    String getNoEngineInfo();
}
