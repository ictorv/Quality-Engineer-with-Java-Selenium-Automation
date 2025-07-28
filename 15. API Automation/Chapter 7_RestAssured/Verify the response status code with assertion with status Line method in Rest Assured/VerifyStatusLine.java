
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class VerifyStatusLine {
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
                .post("/booking");

        // Print response body
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());

        // Verify status line
        String expectedStatusLine = "HTTP/1.1 200 OK";
        String actualStatusLine = response.getStatusLine();
        System.out.println("Status Line: " + actualStatusLine);
        assertThat(actualStatusLine, equalTo(expectedStatusLine));
    }
}
