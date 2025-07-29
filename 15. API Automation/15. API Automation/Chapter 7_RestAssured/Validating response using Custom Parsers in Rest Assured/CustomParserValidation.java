
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CustomParserValidation {
    public static void main(String[] args) {
        // Register a custom parser for text/plain as JSON
        RestAssured.registerParser("text/plain", Parser.JSON);

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

        // Send POST request and validate response
        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("https://restful-booker.herokuapp.com/booking");

        // Print the response
        System.out.println("Response:");
        System.out.println(response.asPrettyString());

        // Example assertion using custom parser
        response.then().statusCode(200).body("booking.firstname", equalTo("Jim"));
    }
}
