import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class StatusCode500 {


    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    // Define the full API URL for the 500 status code endpoint
    private static final String API_URL = "https://the-internet.herokuapp.com/status_codes/500";

    @Test
    public void testStatusCode500() {
        System.out.println("Attempting to access: " + API_URL);

        // Perform the GET request
        Response response = RestAssured.given()
                .when()
                .get(API_URL); // Send the GET request

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // 4. Verify the status code is 500
        assertEquals(response.getStatusCode(), 500, "Expected status code 500 but got " + response.getStatusCode());
        System.out.println("Status Code Verification Passed: Expected 500, Actual " + response.getStatusCode());
    }
}