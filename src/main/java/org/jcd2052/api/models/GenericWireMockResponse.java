package org.jcd2052.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GenericWireMockResponse(int status, Map<String, Object> headers, String body) {
}
