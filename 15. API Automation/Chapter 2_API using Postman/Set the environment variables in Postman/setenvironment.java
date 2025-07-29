package Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class setenvironment {

    // Base URI for the API
    private static final String BASE_URI = "https://gorest.co.in/public/v2/";

    // IMPORTANT: Replace this with your actual access token generated from gorest.co.in
    // Follow steps 2-3 from your scenario to get this token.
    private static final String ACCESS_TOKEN = "dda6319747afe83d50ab69ad1e739a9acd979dfe8374faa24b613d1bb1b80d41";

    // Global variable to store the created user ID, similar to Postman's global variable
    private static int createdUserId;

    private RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        // Set the base URI for all requests
        RestAssured.baseURI = BASE_URI;

        // IMPORTANT: For local testing environments where SSL certificate issues might occur,
        // you can bypass SSL validation. DO NOT use this in production environments.
        RestAssured.useRelaxedHTTPSValidation();

        // Initialize RequestSpecification with common headers and authentication
        requestSpec = RestAssured.given()
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON); // Accept JSON responses
    }

    @Test(priority = 1, description = "Automate Create User API and capture ID")
    public void testCreateUserAPI() {
        // Step 5 & 7: Define the endpoint and the request body
        String usersEndpoint = "/users";

        // Create a dynamic request body using a Map
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Test User " + System.currentTimeMillis()); // Unique name
        requestBody.put("gender", "male");
        requestBody.put("email", "test.user." + System.currentTimeMillis() + "@example.com"); // Unique email
        requestBody.put("status", "active");

        System.out.println("Sending POST request to: " + RestAssured.baseURI + usersEndpoint);
        System.out.println("Request Body: " + requestBody);

        // Step 9: Send the POST request and get the response
        Response response = requestSpec
                .body(requestBody)
                .post(usersEndpoint);

        // Print the response for debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Step 9: Verify if the http status code is 201 (Created)
        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 for user creation");

        // Step 10: Verify the response body and capture the ID
        // Verify essential fields in the response
        Assert.assertNotNull(response.jsonPath().getString("id"), "User ID should not be null");
        Assert.assertEquals(response.jsonPath().getString("name"), requestBody.get("name"), "Name in response should match request");
        Assert.assertEquals(response.jsonPath().getString("email"), requestBody.get("email"), "Email in response should match request");
        Assert.assertEquals(response.jsonPath().getString("gender"), requestBody.get("gender"), "Gender in response should match request");
        Assert.assertEquals(response.jsonPath().getString("status"), requestBody.get("status"), "Status in response should match request");

        // Step 8: Capture the ID in a global variable
        createdUserId = response.jsonPath().getInt("id");
        System.out.println("Captured User ID: " + createdUserId);
    }

    @Test(priority = 2, description = "Verify the created user using GET API (Optional, but good practice)")
    public void testGetUserAPI() {
        // This test depends on the successful creation of a user in the previous test
        // It uses the captured createdUserId
        Assert.assertTrue(createdUserId > 0, "User ID must be captured from previous test");

        String getUserEndpoint = "/users/" + createdUserId;

        System.out.println("Sending GET request to: " + RestAssured.baseURI + getUserEndpoint);

        Response response = requestSpec
                .get(getUserEndpoint);

        System.out.println("Response Status Code (GET): " + response.getStatusCode());
        System.out.println("Response Body (GET): " + response.getBody().asString());

        // Verify status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for getting user");

        // Verify that the retrieved user's ID matches the created ID
        Assert.assertEquals(response.jsonPath().getInt("id"), createdUserId, "Retrieved user ID should match created user ID");
        // You can add more assertions here to verify other fields of the retrieved user
    }
}