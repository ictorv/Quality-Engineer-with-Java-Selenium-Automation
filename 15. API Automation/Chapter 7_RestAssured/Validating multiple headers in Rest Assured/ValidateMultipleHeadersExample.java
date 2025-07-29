
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ValidateMultipleHeadersExample {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

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

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/booking");

        // Validate multiple headers
        response.then().assertThat().headers(
                "Content-Type", containsString("application/json"),
                "Server", notNullValue()
        );

        // Print response body and headers
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());

        System.out.println("\nResponse Headers:");
        response.getHeaders().forEach(header -> 
            System.out.println(header.getName() + ": " + header.getValue())
        );
    }
}
