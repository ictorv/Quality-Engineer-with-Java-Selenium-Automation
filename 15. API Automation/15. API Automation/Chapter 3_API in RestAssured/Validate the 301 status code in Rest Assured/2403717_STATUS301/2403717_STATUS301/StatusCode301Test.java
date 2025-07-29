import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class StatusCode301Test {

    // Static initializer block to disable SSL certificate validation for Rest Assured.
    // WARNING: This is generally NOT recommended for production environments as it bypasses
    // critical security checks. Use it only for testing purposes where you explicitly
    // trust the target server and understand the security implications.
    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    // Define the full API URL for the 301 status code endpoint
    private static final String API_URL = "https://the-internet.herokuapp.com/status_codes/301";

    @Test
    public void testStatusCode301() {
        System.out.println("Attempting to access: " + API_URL);

        // Perform the GET request
        // Rest Assured by default follows redirects. To verify a 301, we need to disable
        // automatic redirection.
        Response response = RestAssured.given()
                .redirects().follow(false) // Do not follow redirects
                .when()
                .get(API_URL); // Send the GET request

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body (if any): " + response.getBody().asString());
        System.out.println("Location Header (for 301 redirect): " + response.getHeader("Location"));


        // 4. Verify the status code is 301
        assertEquals(response.getStatusCode(), 301, "Expected status code 301 but got " + response.getStatusCode());
        System.out.println("Status Code Verification Passed: Expected 301, Actual " + response.getStatusCode());
    }
}