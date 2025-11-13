package com.example.framework.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static com.example.framework.api.specs.SpecFactory.spec;

public class RestClient {
    public Response get(String path){ return given().spec(spec()).when().get(path).then().extract().response(); }
    public Response postJson(String path, Object body){
        return given().spec(spec()).contentType(ContentType.JSON).body(body)
                .when().post(path).then().extract().response();
    }
}
