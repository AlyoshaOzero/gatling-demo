package org.example.constant;

import org.example.util.ConfigReader;

import java.time.Duration;

public final class TestConfiguration {

    // Think Times
    public static final Duration DEFAULT_THINK_TIME = Duration.parse(ConfigReader.INSTANCE.getProperty("default.think.time"));
    public static final Duration STRESS_TEST_THINK_TIME = Duration.parse(ConfigReader.INSTANCE.getProperty("stress.test.think.time"));

    // Ramp Up Times
    public static final Duration DEFAULT_RAMP_UP_TIME = Duration.parse(ConfigReader.INSTANCE.getProperty("default.ramp.up.time"));
    public static final Duration STRESS_TEST_RAMP_UP_TIME = Duration.parse(ConfigReader.INSTANCE.getProperty("stress.test.ramp.up.time"));

    // Test Durations
    public static final Duration LOAD_TEST_DURATION = Duration.parse(ConfigReader.INSTANCE.getProperty("load.test.duration"))
            .plus(DEFAULT_RAMP_UP_TIME);
    public static final Duration STRESS_TEST_DURATION = Duration.parse(ConfigReader.INSTANCE.getProperty("stress.test.duration"))
            .plus(STRESS_TEST_RAMP_UP_TIME);
    public static final Duration ENDURANCE_TEST_DURATION = Duration.parse(ConfigReader.INSTANCE.getProperty("endurance.test.duration"))
            .plus(DEFAULT_RAMP_UP_TIME);

    // Breakpoint Test
    public static final int MAX_VIRTUAL_USERS = Integer.parseInt(ConfigReader.INSTANCE.getProperty("maximum.virtual.users.count"));
    public static final int VIRTUAL_USER_RAMP_UP_RATE = Integer.parseInt(ConfigReader.INSTANCE.getProperty("virtual.users.ramp.up.rate"));
}
