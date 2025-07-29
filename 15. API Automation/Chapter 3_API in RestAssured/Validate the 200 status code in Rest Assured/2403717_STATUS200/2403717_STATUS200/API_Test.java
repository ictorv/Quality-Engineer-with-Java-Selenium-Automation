import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class API_Test {
    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    // Define the base URI for the API
    private static final String BASE_URI = "https://postman-echo.com";
    // Define the endpoint for basic authentication
    private static final String BASIC_AUTH_ENDPOINT = "/basic-auth";
    // Define the username for basic authentication
    private static final String USERNAME = "postman";
    // Define the password for basic authentication
    private static final String PASSWORD = "password";

    @Test
    public void testBasicAuthenticationAndResponse() {
        // Set the base URI for Rest Assured
        RestAssured.baseURI = BASE_URI;

        System.out.println("Attempting to access: " + RestAssured.baseURI + BASIC_AUTH_ENDPOINT);
        System.out.println("Using basic auth with username: " + USERNAME + " and password: " + PASSWORD);

        // Perform the GET request with basic authentication
        Response response = RestAssured.given()
                .auth().basic(USERNAME, PASSWORD) // Apply basic authentication
                .when()
                .get(BASIC_AUTH_ENDPOINT); // Send the GET request

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // 5. Verify the status code is 200
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        System.out.println("Status Code Verification Passed: Expected 200, Actual " + response.getStatusCode());

        // 6. Verify the response body contains "authenticated": true
        // This checks if the authentication was successful as indicated by the API's response
        assertTrue(response.jsonPath().getBoolean("authenticated"), "Expected 'authenticated' to be true in the response.");
        System.out.println("Response Body Verification Passed: 'authenticated' is true.");
    }
}