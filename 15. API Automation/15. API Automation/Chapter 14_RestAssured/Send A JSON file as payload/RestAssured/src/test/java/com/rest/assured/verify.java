package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class verify {

    @Test
    public void postJsonFromFileAndVerifyStatusCode() throws IOException {
        // Disable SSL certificate validation for testing
        RestAssured.useRelaxedHTTPSValidation();

        // Read JSON file from project directory
        String filePath = "src/main/resources/samsung.json"; // Adjust path if needed
        String jsonBody = new String(Files.readAllBytes(Paths.get(filePath)));

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
