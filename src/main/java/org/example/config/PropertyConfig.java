package org.example.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public enum PropertyConfig {
    TEST_DATA_CONFIG(TestDataConfig.class);

    private final Class<? extends Config> configClass;

    PropertyConfig(Class<? extends Config> configClass) {
        this.configClass = configClass;
    }

    @SuppressWarnings("unchecked")
    public <T extends Config> T getConfig() {
        return (T) ConfigFactory.create(configClass, System.getProperties());
    }
}
