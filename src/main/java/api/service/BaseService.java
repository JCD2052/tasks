package api.service;

import api.routes.APIMethods;
import api.routes.Endpoints;
import config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import testdata.TestDataReader;
import utils.Convertor;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public abstract class BaseService {

    protected <O, T> T getBaseRequest(APIMethods apiMethod, O object, Class<T> classType) {
        return given()
                .spec(new RequestSpecBuilder()
                        .setBaseUri(ConfigReader.get("base_api_url"))
                        .setBasePath(getBasePath().getRoute() + "." + apiMethod.getApiMethod())
                        .addQueryParam("access_token", TestDataReader.get("token"))
                        .addQueryParam("v", ConfigReader.get("api_version"))
                        .setContentType(ContentType.JSON)
                        .addParams(checkRequestObject(object))
                        .build())
                .get()
                .jsonPath()
                .getObject(Endpoints.RESPONSE_ROUTE.getRoute(), classType);
    }

    private static <O> HashMap<String, ?> checkRequestObject(O object) {
        return (object == null) ? new HashMap<>() : Convertor.convertToHashMap(object);
    }

    protected abstract Endpoints getBasePath();
}
