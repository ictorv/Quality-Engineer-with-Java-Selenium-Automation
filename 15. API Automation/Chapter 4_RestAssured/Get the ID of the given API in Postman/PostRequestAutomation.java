
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestAutomation {
    public static void main(String[] args) {
        // Set the base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Define the request body
        String requestBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

        // Send POST request and validate response
        Response response = given()
            .header("Content-type", "application/json")
            .and()
            .body(requestBody)
            .when()
            .post("/posts")
            .then()
            .statusCode(201)
            .body("title", equalTo("foo"))
            .body("body", equalTo("bar"))
            .body("userId", equalTo(1))
            .extract().response();

        // Print response body
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());
    }
}
