package org.example.scenario.impl;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CoreDsl;
import org.example.constant.BusinessProcess;
import org.example.controller.ProductController;
import org.example.scenario.BusinessProcessService;

import java.time.Duration;

public class GetAllProductsBusinessProcessServiceImpl implements BusinessProcessService {

    @Override
    public String getBusinessProcessName() {
        return BusinessProcess.BP1.getBusinessProcessFullName();
    }

    @Override
    public ChainBuilder getScenario(Duration thinkTimeDuration) {
        return CoreDsl
                .exec(ProductController.getAllProducts())
                .pause(thinkTimeDuration);
    }
}
