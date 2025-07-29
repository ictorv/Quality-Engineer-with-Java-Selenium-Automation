import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class GetUsersPageDoubleQuery {

    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    // Define the base URI for the API
    private static final String BASE_URI = "https://reqres.in";
    // Define the endpoint for getting users
    private static final String USERS_ENDPOINT = "/api/users";

    @Test
    public void testGetUsersWithPageAndIdParameters() {
        // Set the base URI for Rest Assured
        RestAssured.baseURI = BASE_URI;

        System.out.println("Attempting to access: " + RestAssured.baseURI + USERS_ENDPOINT + "?page=2&id=5");

        // Perform the GET request with both 'page' and 'id' query parameters
        Response response = RestAssured.given()
                .queryParam("page", 2) // Set the query parameter page=2
                .queryParam("id", 5)   // Set the query parameter id=5
                .when()
                .get(USERS_ENDPOINT); // Send the GET request

        System.out.println("Response Status Code: " + response.getStatusCode());

        // Verify status code is 200 OK
        // Note: The reqres.in API for /api/users with both page and id might return
        // an empty data array if the ID is not found on that specific page,
        // but the overall request will still be 200 OK if the endpoint is valid.
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        System.out.println("Status Code Verification Passed: Expected 200, Actual " + response.getStatusCode());

        // Print the entire response body in the console
        String responseBody = response.getBody().asString();
        System.out.println("\nFull Response Body (JSON):");
        System.out.println(responseBody);

        // You can also parse specific values if needed, for example:
        // String emailOfUser = response.jsonPath().getString("data[0].email");
        // System.out.println("Email of user: " + emailOfUser);
        // Note: If the combination of page and ID doesn't yield a specific user,
        // the 'data' array might be empty or contain other users from the page.
        // For a specific user by ID, typically the endpoint would be /api/users/{id}.
        // When using /api/users with both page and id, the 'id' parameter might filter
        // results on that page, or it might be ignored depending on API implementation.
        // In reqres.in, querying /api/users?page=2&id=5 will return the data for user ID 5
        // if user ID 5 is present on page 2. If not, it will return the standard page 2 data.
        // To strictly get user ID 5, it's better to use https://reqres.in/api/users/5 directly.
    }
}