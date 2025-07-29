import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class GetUsersPageTest {

    // Static initializer block to disable SSL certificate validation for Rest Assured.
    // WARNING: This is generally NOT recommended for production environments as it bypasses
    // critical security checks. Use it only for testing purposes where you explicitly
    // trust the target server and understand the security implications.
    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    // Define the base URI for the API
    private static final String BASE_URI = "https://reqres.in";
    // Define the endpoint for getting users
    private static final String USERS_ENDPOINT = "/api/users";

    @Test
    public void testGetUsersWithPageParameter() {
        // Set the base URI for Rest Assured
        RestAssured.baseURI = BASE_URI;

        System.out.println("Attempting to access: " + RestAssured.baseURI + USERS_ENDPOINT + "?page=2");

        // Perform the GET request with the 'page' query parameter
        Response response = RestAssured.given()
                .queryParam("page", 2) // Set the query parameter page=2
                .when()
                .get(USERS_ENDPOINT); // Send the GET request

        System.out.println("Response Status Code: " + response.getStatusCode());

        // Verify status code is 200 OK
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        System.out.println("Status Code Verification Passed: Expected 200, Actual " + response.getStatusCode());

        // Print the entire response body in the console
        String responseBody = response.getBody().asString();
        System.out.println("\nFull Response Body (JSON):");
        System.out.println(responseBody);

        // You can also parse specific values if needed, for example:
        // int totalPages = response.jsonPath().getInt("total_pages");
        // System.out.println("Total Pages: " + totalPages);
        // String emailOfFirstUserOnPage = response.jsonPath().getString("data[0].email");
        // System.out.println("Email of first user on page 2: " + emailOfFirstUserOnPage);
    }
}
