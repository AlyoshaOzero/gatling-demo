package org.example.util;


import io.gatling.javaapi.core.Session;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.example.constant.BaseConstant;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ExecutionUtil {

    private static int status5xxCount = 0;
    private static int currentTotalRequestCount = 0;

    public static Session exitApplicationIf5xx(Session session) {
        currentTotalRequestCount += 1;
        var currentTotalRequestCount3Percentile = new BigDecimal(ConfigReader.INSTANCE.getProperty("5xx.response.threshold.percentage", "3"))
                .divide(BigDecimal.valueOf(100L), 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(currentTotalRequestCount));
        if (session.getInt(BaseConstant.RESPONSE_STATUS_CODE) >= HttpResponseStatus.INTERNAL_SERVER_ERROR.code()) {
            status5xxCount += 1;
//            If the count of 5xx responses (status5xxCount) exceeds configured percentage of the total number of requests
//            (currentTotalRequestCount), it exits the program
            if (BigDecimal.valueOf(status5xxCount).compareTo(currentTotalRequestCount3Percentile) >= 0) {
                System.exit(0);
            }
        }
        return session;
    }
}
