
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ListOfValuesValidation {
    public static void main(String[] args) {
        // Set base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Define request body
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
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Print response
        System.out.println("Response:");
        System.out.println(response.asString());

        // Convert response to JSON document
        JsonPath jsonPath = new JsonPath(response.asString());

        // Validate list of values if present
        // Example: Check if bookingdates contains both checkin and checkout
        jsonPath.getMap("booking.bookingdates").containsKey("checkin");
        jsonPath.getMap("booking.bookingdates").containsKey("checkout");

        // Example assertion using Rest Assured
        response.then().body("booking.bookingdates.checkin", equalTo("2018-01-01"));
        response.then().body("booking.bookingdates.checkout", equalTo("2019-01-01"));
    }
}
