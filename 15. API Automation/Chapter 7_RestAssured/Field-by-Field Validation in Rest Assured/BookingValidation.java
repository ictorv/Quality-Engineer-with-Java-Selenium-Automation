
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingValidation {
    public static void main(String[] args) {
        // Set the base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Define the request body
        String requestBody = "{\n" +
                "    \"firstname\": \"Jim\",\n" +
                "    \"lastname\": \"Brown\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        // Send POST request and get response
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/booking");

        // Print the response
        System.out.println("Response:");
        response.prettyPrint();

        // Validate individual fields in the response
        response.then().body("booking.firstname", equalTo("Jim"));
        response.then().body("booking.lastname", equalTo("Brown"));
        response.then().body("booking.totalprice", equalTo(111));
        response.then().body("booking.depositpaid", equalTo(true));
        response.then().body("booking.bookingdates.checkin", equalTo("2018-01-01"));
        response.then().body("booking.bookingdates.checkout", equalTo("2019-01-01"));
        response.then().body("booking.additionalneeds", equalTo("Breakfast"));
    }
}
