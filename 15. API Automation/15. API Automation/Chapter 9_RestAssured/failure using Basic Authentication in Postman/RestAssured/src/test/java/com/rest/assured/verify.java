package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class verify {

    @Test
    public void testBasicAuth() {
        String uri = "https://postman-echo.com/basic-auth";
        RestAssured.useRelaxedHTTPSValidation();

        Response response = RestAssured
                .given()
                .auth()
                .basic("postman", "password")
                .when()
                .get(uri)
                .then()
                .extract()
                .response();

        // Verify status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        // Verify response body contains authenticated: true
        boolean isAuthenticated = response.jsonPath().getBoolean("authenticated");
        Assert.assertTrue(isAuthenticated, "Authentication should be successful");

        System.out.println("Response: " + response.asPrettyString());
    }
}
