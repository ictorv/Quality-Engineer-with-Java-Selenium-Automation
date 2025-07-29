import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingTest {

    public static void main(String[] args) {
        // Step 1: Set base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Step 2: Send GET request to /booking
        Response response = RestAssured
                .given()
                .when()
                .get("/booking");

        // Step 3: Verify the response
        response.then().statusCode(200); // Expecting HTTP 200 OK

        // Step 4: Print response body
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());
    }
}
