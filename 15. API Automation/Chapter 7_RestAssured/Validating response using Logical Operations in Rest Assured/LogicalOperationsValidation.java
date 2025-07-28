
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LogicalOperationsValidation {
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

        // Send POST request and perform logical validations
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/booking");

        // Print the response
        System.out.println("Response:");
        response.prettyPrint();

        // Perform logical assertions
        response.then().assertThat()
                .statusCode(200)
                .body("booking.firstname", equalTo("Jim"))
                .body("booking.lastname", equalTo("Brown"))
                .body("booking.totalprice", anyOf(equalTo(111), greaterThan(100))) // OR condition
                .body("booking.depositpaid", not(equalTo(false))) // NOT condition
                .body("booking.additionalneeds", allOf(notNullValue(), equalTo("Breakfast"))); // AND condition
    }
}
