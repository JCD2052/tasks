package api.service;

import api.routes.APIMethods;
import config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import testdata.TestDataReader;
import utils.Convertor;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public abstract class BaseService {
    private final static String RESPONSE_ROUTE = "response";

    protected <O, T> T getBaseRequest(APIMethods apiMethod, O object, Class<T> classType) {
        return given()
                .spec(new RequestSpecBuilder()
                        .setBaseUri(ConfigReader.get("base_api_url"))
                        .setBasePath(apiMethod.getApiMethod())
                        .addQueryParam("access_token", TestDataReader.get("token"))
                        .addQueryParam("v", ConfigReader.get("api_version"))
                        .setContentType(ContentType.JSON)
                        .addParams(checkRequestObject(object))
                        .build())
                .get()
                .jsonPath()
                .getObject(RESPONSE_ROUTE, classType);
    }

    private static <O> HashMap<String, ?> checkRequestObject(O object) {
        return (object == null) ? new HashMap<>() : Convertor.convertToHashMap(object);
    }
}
