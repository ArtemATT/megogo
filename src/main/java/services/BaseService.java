package services;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import utils.ConfigLoader;

public abstract class BaseService {

    private final String path;

    protected BaseService(String path) {
        this.path = path;
    }

    protected RequestSpecification getSpecification() {
        return RestAssured.given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .when()
                .baseUri(ConfigLoader.getBaseUrl())
                .basePath(path);
    }
}
