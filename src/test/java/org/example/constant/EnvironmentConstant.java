package org.example.constant;

import org.example.util.ConfigReader;

public final class EnvironmentConstant {

    private static final String PROD = "PROD";
    private static final String QA = "QA";
    private static final String TEST_ENV = System.getProperty("env", PROD);

    public static final String BASE_URL = getBaseUrl();

    private static String getBaseUrl() {
        return switch (TEST_ENV) {
            case PROD -> ConfigReader.INSTANCE.getProperty("prod.base.url");
            case QA -> ConfigReader.INSTANCE.getProperty("qa.base.url");
            default ->
                    throw new IllegalArgumentException("Unknown environment '" + TEST_ENV + "' for performance test.");
        };
    }
}
