import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

public class APITestWithHeadersMap {

    public static void main(String[] args) {

        // Base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Request body
        String requestBody = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        // Headers in a Map
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        // Send POST request
        Response response = RestAssured
                .given()
                    .headers(headers)
                    .body(requestBody)
                .when()
                    .post("/auth")
                .then()
                    .statusCode(200) // Validate status code
                    .extract()
                    .response();

        // Print the response
        System.out.println("Response Body:");
        System.out.println(response.getBody().asPrettyString());
    }
}