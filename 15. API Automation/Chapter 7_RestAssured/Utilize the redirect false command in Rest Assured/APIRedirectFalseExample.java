import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIRedirectFalseExample {

    public static void main(String[] args) {

        // Base URI
        RestAssured.baseURI = "https://simple-tool-rental-api.glitch.me";

        // Send GET request with redirect disabled
        Response response = RestAssured
                .given()
                    .redirects().follow(false)
                .when()
                    .get("/status")
                .then()
                    .extract()
                    .response();

        // Print status code and response body
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:");
        System.out.println(response.getBody().asPrettyString());
    }
}