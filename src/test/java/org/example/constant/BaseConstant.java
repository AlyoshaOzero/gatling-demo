package org.example.constant;

public final class BaseConstant {

    // API Names
    public static final String GET_AUTH_TOKEN_API_NAME = "[POST] Get Authentication Token";

    // API Endpoints
    public static final String AUTHORIZATION_ENDPOINT = "/auth/login";

    // Authentication
    public static final String AUTH_HEADER_NAME = "Authorization";
    public static final String AUTH_TOKEN_VARIABLE = "token";
    public static final String AUTH_TOKEN_PLACEHOLDER = String.format("Bearer #{%s}", AUTH_TOKEN_VARIABLE);

    // Session variable names
    public static final String RESPONSE_STATUS_CODE = "responseStatusCode";
}
