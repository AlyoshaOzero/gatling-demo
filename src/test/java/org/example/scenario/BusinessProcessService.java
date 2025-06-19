package org.example.scenario;

import io.gatling.javaapi.core.ChainBuilder;

import java.time.Duration;

public interface BusinessProcessService {

    String getBusinessProcessName();

    ChainBuilder getScenario(Duration thinkTimeDuration);

    default ChainBuilder getScenario() {
        return this.getScenario(Duration.ZERO);
    }
}
