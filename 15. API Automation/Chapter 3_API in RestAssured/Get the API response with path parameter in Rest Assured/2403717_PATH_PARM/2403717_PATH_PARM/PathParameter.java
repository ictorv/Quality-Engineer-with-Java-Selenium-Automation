import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class PathParameter {

    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    // Define the base URI for the API
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";
    // Define the endpoint for getting a specific post by ID using a path parameter placeholder
    private static final String POSTS_ENDPOINT = "/posts/{id}";
    // Define the ID to be used in the request
    private static final int POST_ID = 5;

    @Test
    public void testGetPostById() {
        // Set the base URI for Rest Assured
        RestAssured.baseURI = BASE_URI;

        System.out.println("Attempting to access: " + RestAssured.baseURI + POSTS_ENDPOINT.replace("{id}", String.valueOf(POST_ID)));

        // Perform the GET request with the 'id' path parameter
        Response response = RestAssured.given()
                .pathParam("id", POST_ID) // Set the path parameter 'id'
                .when()
                .get(POSTS_ENDPOINT); // Send the GET request

        System.out.println("Response Status Code: " + response.getStatusCode());

        // Verify status code is 200 OK
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        System.out.println("Status Code Verification Passed: Expected 200, Actual " + response.getStatusCode());

        // Print the entire response body in the console
        String responseBody = response.getBody().asString();
        System.out.println("\nFull Response Body (JSON):");
        System.out.println(responseBody);

        // You can also parse specific values from the JSON response if needed, for example:
        // int id = response.jsonPath().getInt("id");
        // String title = response.jsonPath().getString("title");
        // String body = response.jsonPath().getString("body");
        // int userId = response.jsonPath().getInt("userId");
        //
        // System.out.println("\nParsed JSON Values:");
        // System.out.println("ID: " + id);
        // System.out.println("Title: " + title);
        // System.out.println("Body: " + body);
        // System.out.println("User ID: " + userId);
    }
}