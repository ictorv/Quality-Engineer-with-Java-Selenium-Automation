
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class MultipleHeadersAPIRequest {
    public static void main(String[] args) {
        // Set base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Create request body as a JSON string
        String requestBody = "{\n" +
                             "    \"username\" : \"admin\",\n" +
                             "    \"password\" : \"password123\"\n" +
                             "}";

        // Send POST request with multiple headers and body
        Response response = given()
                                .header("Content-Type", "application/json")
                                .header("Accept", "application/json")
                                .body(requestBody)
                            .when()
                                .post("/auth")
                            .then()
                                .statusCode(200)
                                .extract().response();

        // Print response
        System.out.println("Response: " + response.asString());
    }
}
