package org.jcd2052.api.endpoints;

public enum AdminEndpoint implements IEndpoint {
    MAPPINGS("__admin/mappings"),
    IMPORT_MAPPINGS("__admin/mappings/import"),
    REQUESTS("__admin/requests");

    private final String endpoint;

    AdminEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }
}
