
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.Headers;
import io.restassured.http.Header;

import static io.restassured.RestAssured.given;

public class GetAllHeadersExample {
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

        // Send POST request and get the response
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/booking");

        // Print status code
        System.out.println("Status Code: " + response.getStatusCode());

        // Get all headers
        Headers headers = response.getHeaders();
        System.out.println("Response Headers:");
        for (Header header : headers) {
            System.out.println(header.getName() + ": " + header.getValue());
        }

        // Print response body
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());
    }
}
