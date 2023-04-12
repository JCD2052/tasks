package org.jcd2052.api.services;

import io.restassured.response.Response;
import org.jcd2052.api.endpoints.TestEndpoint;

import java.util.Map;

public class TestService extends BaseService {
    public TestService(String hostUrl) {
        super(hostUrl);
    }

    public Response getPlainText() {
        return super.createSpecification(TestEndpoint.PLAIN_TEXT).get();
    }

    public Response getJsonText(Map<String, Object> params) {
        return super.createSpecification(TestEndpoint.JSON_TEXT)
                .queryParams(params)
                .get();
    }

    public Response postJsonText(Object body, Map<String, Object> headers) {
        return super.createSpecification(TestEndpoint.MATCHING_REQUEST)
                .body(body)
                .headers(headers)
                .post();
    }

    public Response putToRedirect() {
        return super.createSpecification(TestEndpoint.MATCHING_URL_WITH_REDIRECT)
                .redirects().follow(true)
                .put();
    }
}
