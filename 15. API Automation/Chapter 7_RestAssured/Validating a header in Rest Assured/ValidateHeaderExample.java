
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class ValidateHeaderExample {
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

        // Send POST request and validate header
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/booking");

        // Validate the header using Hamcrest matcher
        response.then().header("Content-Type", Matchers.containsString("application/json"));

        // Print response body
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());

        // Print all headers
        System.out.println("\nResponse Headers:");
        response.getHeaders().forEach(header -> System.out.println(header.getName() + ": " + header.getValue()));
    }
}
