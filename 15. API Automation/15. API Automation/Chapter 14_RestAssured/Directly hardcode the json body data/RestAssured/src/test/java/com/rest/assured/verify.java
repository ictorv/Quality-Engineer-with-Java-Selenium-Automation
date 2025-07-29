package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class verify {

    @Test
    public void postJsonAndVerifyStatusCode() {
        // Disable SSL certificate validation (for testing only)
        RestAssured.useRelaxedHTTPSValidation();

        // Hardcoded JSON body as a String
        String jsonBody = "{\n" +
                "    \"name\": \"Apple MacBook Pro 16\",\n" +
                "    \"data\": {\n" +
                "       \"year\": 2019,\n" +
                "       \"price\": 1849.99,\n" +
                "       \"CPU model\": \"Intel Core i9\",\n" +
                "       \"Hard disk size\": \"1 TB\"\n" +
                "    }\n" +
                "}";

        // Send POST request
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .post("https://api.restful-api.dev/objects");

        // Verify status code
        assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        // Print full response
        System.out.println("Response:");
        System.out.println(response.getBody().asPrettyString());
    }
}
