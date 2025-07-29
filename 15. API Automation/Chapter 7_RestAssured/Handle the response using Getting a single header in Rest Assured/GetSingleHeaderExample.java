
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetSingleHeaderExample {
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
                .when()
                .post("/booking");

        // Print the response body
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());

        // Get and print a single header from the response
        String contentType = response.getHeader("Content-Type");
        System.out.println("Content-Type Header: " + contentType);

        // Verify the status code
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
    }
}
