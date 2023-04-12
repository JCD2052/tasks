package org.jcd2052.api.endpoints;

public enum TestEndpoint implements IEndpoint {
    PLAIN_TEXT("/plaintext/mapping1"),
    JSON_TEXT("/jsontext/mapping2"),
    MATCHING_REQUEST("/jsontext/mapping3"),
    MATCHING_URL_WITH_REDIRECT("/*");

    private final String endpoint;

    TestEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }
}
