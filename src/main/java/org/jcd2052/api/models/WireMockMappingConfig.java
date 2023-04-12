package org.jcd2052.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WireMockMappingConfig(
        @JsonProperty("response") GenericWireMockResponse wireMockResponse) {
}
