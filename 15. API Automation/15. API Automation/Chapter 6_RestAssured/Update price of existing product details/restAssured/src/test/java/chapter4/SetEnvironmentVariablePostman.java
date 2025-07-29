package chapter4;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SetEnvironmentVariablePostman {

    @Test
    public void createUserAndValidateResponse() {
        RestAssured.useRelaxedHTTPSValidation();

        // Simulate environment variable (normally loaded from config)
        String token = System.getenv("GOREST_TOKEN");
        if (token == null || token.isEmpty()) {
            token = "148462805e457d36d9e4e6ea46addfc0ae8eb6b0b1727f57fd8e582502549192"; // fallback
        }

        // Dynamic email to avoid duplication
        String email = "john.doe" + System.currentTimeMillis() + "@example.com";

        // Create user
        Response response = RestAssured
            .given()
                .baseUri("https://gorest.co.in/public/v2")
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"John Doe\", \"gender\": \"male\", \"email\": \"" + email + "\", \"status\": \"active\" }")
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .extract().response();

        // Capture ID and validate response
        int userId = response.jsonPath().getInt("id");
        System.out.println("USER ID: " + userId);

        Assert.assertEquals(response.jsonPath().getString("name"), "John Doe");
        Assert.assertEquals(response.jsonPath().getString("status"), "active");
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(userId > 0, "User ID should be a positive integer");
    }
}
