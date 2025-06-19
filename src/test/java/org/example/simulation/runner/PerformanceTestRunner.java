package org.example.simulation.runner;

import io.gatling.javaapi.core.Simulation;
import org.example.constant.BusinessProcess;
import org.example.constant.PerformanceTestType;
import org.example.constant.TestConfiguration;
import org.example.scenario.BusinessProcessService;
import org.example.scenario.impl.GetAllProductsBusinessProcessServiceImpl;
import org.example.simulation.BusinessProcessSimulation;
import org.example.util.ConfigReader;
import org.example.util.HttpProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.example.constant.BusinessProcess.BP1;
import static org.example.constant.PerformanceTestType.LOAD;

public final class PerformanceTestRunner extends Simulation {

    private static final Logger LOG = LoggerFactory.getLogger(PerformanceTestRunner.class);

    public PerformanceTestRunner() {
        final String businessProcessString = System.getProperty("bp", BP1.name());
        final String testTypeString = System.getProperty("testType", LOAD.name());

        var businessProcess = BusinessProcess.valueOf(businessProcessString);
        var performanceTestType = PerformanceTestType.valueOf(testTypeString);

        LOG.debug("Starting the {} test for the business process: {}", testTypeString, businessProcess.getBusinessProcessFullName());

        var testScenario = this.selectTest(businessProcess, performanceTestType);
        this.setUp(
                testScenario
                        .getPopulationBuilder()
                        .protocols(HttpProtocol.getGenericHttpProtocol())
        );
    }

    /* Private methods */
    private BusinessProcessSimulation selectTest(BusinessProcess businessProcess, PerformanceTestType performanceTestType) {
        int virtualUserCount = Integer.parseInt(ConfigReader.INSTANCE.getProperty(businessProcess.name() + ".virtual.users.count", "1"));

        var service = switch (businessProcess) {
            case BP1 -> new GetAllProductsBusinessProcessServiceImpl();
            default ->
                    throw new RuntimeException("Business process '" + businessProcess.name() + "' has not been implemented.");
        };
        return this.createTestScenario(virtualUserCount, service, performanceTestType);
    }

    private BusinessProcessSimulation createTestScenario(int virtualUserCount,
                                                         BusinessProcessService service,
                                                         PerformanceTestType performanceTestType) {
        try {
            return switch (performanceTestType) {
                case LOAD -> new BusinessProcessSimulation(
                        virtualUserCount,
                        TestConfiguration.DEFAULT_RAMP_UP_TIME,
                        TestConfiguration.DEFAULT_THINK_TIME,
                        TestConfiguration.LOAD_TEST_DURATION,
                        false,
                        service
                );
                case STRESS -> new BusinessProcessSimulation(
                        virtualUserCount,
                        TestConfiguration.STRESS_TEST_RAMP_UP_TIME,
                        TestConfiguration.STRESS_TEST_THINK_TIME,
                        TestConfiguration.STRESS_TEST_DURATION,
                        false,
                        service
                );
                case ENDURANCE -> new BusinessProcessSimulation(
                        virtualUserCount,
                        TestConfiguration.DEFAULT_RAMP_UP_TIME,
                        TestConfiguration.DEFAULT_THINK_TIME,
                        TestConfiguration.ENDURANCE_TEST_DURATION,
                        false,
                        service
                );
                case BREAKPOINT -> new BusinessProcessSimulation(
                        TestConfiguration.MAX_VIRTUAL_USERS,
                        Duration.ofSeconds(TestConfiguration.MAX_VIRTUAL_USERS / TestConfiguration.VIRTUAL_USER_RAMP_UP_RATE),
                        TestConfiguration.DEFAULT_THINK_TIME,
                        Duration.ofSeconds(0L),
                        true,
                        service
                );
                default ->
                        throw new RuntimeException("Performance test type '" + performanceTestType.name() + "' has not been implemented.");
            };
        } catch (Exception e) {
            throw new RuntimeException("Failed to create the performance test scenario");
        }
    }
}