package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class verify {

    // Replace with your actual Bearer Token from GoRest
    private static final String BEARER_TOKEN = "d9134cbe0b1ce70252167ff16ee7e41b05da60c46eeba2f233fdc3e46b725691";

    @Test
    public void createUser() {
        // Initialize Faker
        Faker faker = new Faker();

        // Create sample request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", faker.name().fullName());
        requestBody.put("email", faker.internet().emailAddress());
        requestBody.put("gender", "male"); // or "female"
        requestBody.put("status", "active");

        // Send POST request
        RestAssured.useRelaxedHTTPSValidation();

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("https://gorest.co.in/public/v2/users");

        // Print response
        System.out.println("Response Body:");
        System.out.println(response.getBody().asPrettyString());

        // Verify status code
        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");

        // Verify response body values
        Assert.assertEquals(response.jsonPath().getString("name"), requestBody.get("name"));
        Assert.assertEquals(response.jsonPath().getString("email"), requestBody.get("email"));
        Assert.assertEquals(response.jsonPath().getString("gender"), requestBody.get("gender"));
        Assert.assertEquals(response.jsonPath().getString("status"), requestBody.get("status"));
    }
}
