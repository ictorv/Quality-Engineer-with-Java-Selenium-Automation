import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateBookingTest {

    public static void main(String[] args) {
        // Step 1: Get the Bearer token from previous test (replace with actual token)
        String token = "your_token_here"; // You can retrieve this dynamically if needed

        // Step 2: Read request body from input file
        String requestBody = "";
        try {
            requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/bookingRequest.json")));
        } catch (Exception e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        // Step 3: Send POST request
        Response response = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .auth().oauth2(token) // Using Bearer token
                .body(requestBody)
                .when()
                .post();

        // Step 4: Verify response
        response.then().statusCode(200); // or 201 depending on API behavior
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());
    }
}
