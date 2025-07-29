import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals; // Changed from assertTrue to assertEquals

public class GetAPI {

    // Static initializer block to disable SSL certificate validation for Rest Assured.
    // WARNING: This is generally NOT recommended for production environments as it bypasses
    // critical security checks. Use it only for testing purposes where you explicitly
    // trust the target server and understand the security implications.
    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    // Define the base URI for the API
    // Updated to the new API base URL
    private static final String BASE_URI = "https://simple-tool-rental-api.glitch.me";
    // Define the endpoint for the status check
    // Updated to the new API endpoint
    private static final String STATUS_ENDPOINT = "/status";

    @Test
    public void testGetStatusEndpoint() { // Method name updated to reflect new test
        // Set the base URI for Rest Assured
        RestAssured.baseURI = BASE_URI;

        System.out.println("Attempting to send GET request to: " + RestAssured.baseURI + STATUS_ENDPOINT);

        // Perform the GET request
        Response response = RestAssured.given()
                .when()
                .get(STATUS_ENDPOINT); // Send the GET request

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Verify the response.
        // For the /status endpoint, we expect a 200 OK status code.
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200,
                   "Expected status code 200 for GET /status, but got " + statusCode);
        System.out.println("Status Code Verification Passed: Expected 200, Actual " + statusCode);
    }
}