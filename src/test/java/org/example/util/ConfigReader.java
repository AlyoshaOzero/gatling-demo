package org.example.util;

import java.io.IOException;
import java.util.Properties;

public enum ConfigReader {

    INSTANCE;

    private final Properties properties;

    ConfigReader() {
        this.properties = new Properties();
        this.loadProperties();
    }

    public boolean containsKey(String key) {
        return this.properties.containsKey(key);
    }

    public String getProperty(String key) {
        return this.getProperty(key, null);
    }

    public String getProperty(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    /* Private helpers */
    private void loadProperties() {
        var configFilePath = "/application.properties";
        try(var input = this.getClass().getResourceAsStream(configFilePath)) {
            if (input == null) {
                throw new IllegalArgumentException(String.format("Configuration file '%s' has not been found.", configFilePath));
            }
            this.properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to load properties file: '%s'", configFilePath), e);
        }
    }
}
