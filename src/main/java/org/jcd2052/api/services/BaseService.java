package org.jcd2052.api.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.jcd2052.api.endpoints.IEndpoint;

import static io.restassured.RestAssured.given;

class BaseService {
    private final String hostUrl;

    protected BaseService(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    protected RequestSpecification createSpecification(IEndpoint endpoint) {
        return given().spec(new RequestSpecBuilder()
                .setBaseUri(hostUrl)
                .setBasePath(endpoint.getEndpoint())
                .build());
    }
}
