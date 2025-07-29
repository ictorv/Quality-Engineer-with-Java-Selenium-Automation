import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PatchBookingTest {

    public static void main(String[] args) {
        // Step 1: Set booking ID to update
        int bookingId = 1; // Replace with actual booking ID

        // Step 2: Read request body from input file
        String requestBody = "";
        try {
            requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/patchBooking.json")));
        } catch (Exception e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        // Step 3: Send PATCH request
        Response response = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/" + bookingId)
                .contentType(ContentType.JSON)
                .auth().preemptive().basic("admin", "password123")
                .body(requestBody)
                .when()
                .patch();

        // Step 4: Verify response
        response.then().statusCode(200); // Expecting HTTP 200 OK
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());
    }
}
