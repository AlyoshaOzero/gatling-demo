package org.example.util;

import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public final class HttpProtocol {

    public static HttpProtocolBuilder getGenericHttpProtocol() {
        return HttpDsl
                .http
                .header("Cache-Control", "no-cache")
                .acceptHeader("application/json")
                .disableCaching()
                .disableAutoReferer()
                .disableFollowRedirect()
                .contentTypeHeader("application/json")
                .userAgentHeader(ConfigReader.INSTANCE.getProperty("default.user.agent"));
    }
}
