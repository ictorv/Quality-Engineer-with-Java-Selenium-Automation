import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteBookingTest {

    public static void main(String[] args) {
        // Step 1: Set booking ID to delete
        int bookingId = 1; // Replace with actual booking ID

        // Step 2: Send DELETE request
        Response response = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/" + bookingId)
                .auth().preemptive().basic("admin", "password123")
                .when()
                .delete();

        // Step 3: Verify response
        response.then().statusCode(201); // Expecting HTTP 201 Created (as per API docs)
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());
    }
}
