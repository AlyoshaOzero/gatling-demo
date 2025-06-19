package org.example.simulation;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import org.example.controller.AuthenticationController;
import org.example.scenario.BusinessProcessService;

import java.time.Duration;

public final class BusinessProcessSimulation extends Simulation {

    private final int virtualUsersCount;
    private final Duration rampUpTime;
    private final Duration thinkTime;
    private final Duration testDuration;
    private final boolean isBreakpointTest;
    private final BusinessProcessService strategy;

    public BusinessProcessSimulation(int virtualUsersCount,
                                     Duration rampUpTime,
                                     Duration testDuration,
                                     boolean isBreakpointTest,
                                     BusinessProcessService service) {
        this(virtualUsersCount, rampUpTime, null, testDuration, isBreakpointTest, service);
    }

    public BusinessProcessSimulation(int virtualUsersCount,
                                     Duration rampUpTime,
                                     Duration thinkTime,
                                     Duration testDuration,
                                     boolean isBreakpointTest,
                                     BusinessProcessService service) {
        this.virtualUsersCount = virtualUsersCount;
        this.rampUpTime = rampUpTime;
        this.thinkTime = thinkTime;
        this.testDuration = testDuration;
        this.isBreakpointTest = isBreakpointTest;
        this.strategy = service;
    }

    public PopulationBuilder getPopulationBuilder() {
        var scenarioBuilder = CoreDsl.scenario(this.strategy.getBusinessProcessName())
                .exec(AuthenticationController.getAuthentication())
                .exitHereIfFailed()
                .pause(Duration.ofSeconds(3L))
                .during(this.testDuration)
                .on(this.strategy.getScenario(this.thinkTime));

        if (this.isBreakpointTest) {
            return scenarioBuilder
                    .injectClosed(
                            CoreDsl.rampConcurrentUsers(0)
                                    .to(this.virtualUsersCount)
                                    .during(this.rampUpTime)
                    );
        } else {
            return scenarioBuilder
                    .injectOpen(
                            CoreDsl.rampUsers(this.virtualUsersCount)
                                    .during(this.rampUpTime)
                    );
        }
    }
}
