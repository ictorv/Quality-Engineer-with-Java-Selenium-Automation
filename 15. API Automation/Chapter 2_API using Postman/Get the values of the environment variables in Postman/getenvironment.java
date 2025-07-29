package Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class getenvironment {

    // Base URI for the GoRest API
    private static final String BASE_URI = "https://gorest.co.in/public/v2/";

    // IMPORTANT: Replace with your actual Bearer Token from gorest.co.in
    // You can generate a token by signing up on gorest.co.in and going to your profile.
    private static final String API_TOKEN = "dda6319747afe83d50ab69ad1e739a9acd979dfe8374faa24b613d1bb1b80d41"; // <<<--- REPLACE THIS

    // Variable to store the dynamically extracted user ID
    private int extractedUserId;

    @BeforeClass
    public void setup() {
        // Set the base URI for all requests in this class
        RestAssured.baseURI = BASE_URI;
    }

    @Test(priority = 1, description = "Step 4: Get an ID from a list of users to simulate environment variable extraction")
    public void getUserIdFromUsersList() {
        System.out.println("Executing Step 4: Getting a user ID from the list of users...");

        // Make a GET request to the users endpoint to get a list of users
        Response response = given()
                .header("Authorization", "Bearer " + API_TOKEN) // Apply bearer token
                .when()
                .get("users"); // Endpoint to get a list of users

        // Verify status code is 200 OK
        response.then().statusCode(200);

        // Extract the ID of the first user from the response
        // This simulates getting an ID from a "pre-script" or "environment variable" setup
        // In a real scenario, you might have a dedicated endpoint to create a user and get its ID.
        extractedUserId = response.jsonPath().getInt("[0].id");

        System.out.println("Extracted User ID: " + extractedUserId);
        System.out.println("Successfully extracted user ID for subsequent test.");
    }

    @Test(priority = 2, description = "Steps 3, 5, 6, 7: Create GET user API request, apply auth, verify status and body")
    public void getUserApiAndVerifyResponse() {
        // Ensure an ID was extracted from the previous test
        if (extractedUserId == 0) {
            System.err.println("No user ID was extracted. Please ensure 'getUserIdFromUsersList' test ran successfully.");
            return; // Skip this test if no ID is available
        }

        System.out.println("\nExecuting Steps 3, 5, 6, 7: Getting specific user details for ID: " + extractedUserId);

        // Step 3: Create a new request for GET user API
        // Step 5: Use auth type as bearer token and paste the token
        Response response = given()
                .header("Authorization", "Bearer " + API_TOKEN) // Apply bearer token
                .when()
                .get("users/" + extractedUserId); // Use the dynamically extracted ID

        // Step 6: Click on the send button and verify if the http status code is 200
        System.out.println("Verifying HTTP Status Code is 200...");
        response.then()
                .statusCode(200); // Assert that the status code is 200 OK
        System.out.println("HTTP Status Code is 200. Verification successful.");


        // Step 7: Verify if the response body
        System.out.println("Verifying Response Body content...");
        response.then()
                .body("id", equalTo(extractedUserId)) // Verify the ID matches the one requested
                .body("name", notNullValue())         // Verify name is not null
                .body("email", notNullValue())        // Verify email is not null
                .body("gender", anyOf(equalTo("male"), equalTo("female"))) // Verify gender is male or female
                .body("status", anyOf(equalTo("active"), equalTo("inactive"))); // Verify status is active or inactive

        System.out.println("Response Body verification successful.");
        System.out.println("Full response body:\n" + response.asString());
    }

}
