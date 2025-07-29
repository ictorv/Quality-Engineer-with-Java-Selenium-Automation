
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class VerifyStatusCodeWithAssert {
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

        // Verify status code using assertThat
        MatcherAssert.assertThat("Status code is not 200", response.getStatusCode(), equalTo(200));
        System.out.println("Status code verified successfully.");
    }
}
