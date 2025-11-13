package com.example.framework.api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqResHttpbinTests {

    @BeforeClass
    public void setup() {
        // Use explicit bases – override via -Dbase.reqres / -Dbase.httpbin if needed
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void createUser_morpheus() {
        String base = System.getProperty("base.reqres", "https://reqres.in");
        given()
                .baseUri(base)
                .contentType(ContentType.JSON)
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .body("id", not(isEmptyOrNullString()))
                .body("createdAt", notNullValue());
    }

    @Test
    public void register_unsuccessful_missingPassword() {
        String base = System.getProperty("base.reqres", "https://reqres.in");
        given()
                .baseUri(base)
                .contentType(ContentType.JSON)
                .body("{\"email\":\"sydney@fife\"}")
                .when()
                .post("/api/register")
                .then()
                .statusCode(400)
                .body("error", anyOf(equalTo("Missing password"), containsStringIgnoringCase("password")));
    }

    @Test
    public void httpbin_headers_echo() {
        String base = System.getProperty("base.httpbin", "https://httpbin.org");
        given()
                .baseUri(base)
                .header("X-Test", "ci")
                .when()
                .get("/headers")
                .then()
                .statusCode(200)
                // Hyphenated keys must be quoted in JsonPath
                .body("headers.'X-Test'", equalTo("ci"))
                // Also assert we got a typical header to ensure shape
                .body("headers", hasKey("Host"));
    }
}
