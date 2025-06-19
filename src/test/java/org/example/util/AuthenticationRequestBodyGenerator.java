package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class AuthenticationRequestBodyGenerator {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String generateAuthenticationRequestBody() {
        var jsonObject = MAPPER.createObjectNode();
        jsonObject.put("username", "emilys");
        jsonObject.put("password", "emilyspass");
        jsonObject.put("expiresInMins", 120);
        try {
            return MAPPER
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to create JSON.", e);
        }
    }
}
