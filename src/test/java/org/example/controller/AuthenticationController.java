package org.example.controller;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.http.HttpDsl;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.example.constant.BaseConstant;
import org.example.constant.EnvironmentConstant;
import org.example.util.AuthenticationRequestBodyGenerator;

public final class AuthenticationController {

    public static ActionBuilder getAuthentication() {
        return HttpDsl
                .http(BaseConstant.GET_AUTH_TOKEN_API_NAME)
                .post(EnvironmentConstant.BASE_URL + BaseConstant.AUTHORIZATION_ENDPOINT)
                .body(
                        CoreDsl
                                .StringBody(AuthenticationRequestBodyGenerator.generateAuthenticationRequestBody())
                )
                .check(
                        HttpDsl
                                .status()
                                .is(HttpResponseStatus.OK.code())
                                .saveAs(BaseConstant.RESPONSE_STATUS_CODE))
                .check(
                        CoreDsl
                                .jsonPath("$.accessToken")
                                .notNull()
                )
                .check(
                        CoreDsl
                                .jsonPath("$.accessToken")
                                .saveAs(BaseConstant.AUTH_TOKEN_VARIABLE)
                )
                .silent();
    }
}
