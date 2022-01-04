package com.andersonlira.sentry9.extension.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class Sentry9ExtensionResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/sentry9-extension")
                .then()
                .statusCode(200)
                .body(is("Hello sentry9-extension"));
    }
}
