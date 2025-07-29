
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

public class SetEnvironmentVariableExample {
    public static void main(String[] args) {
        // Base URI
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        // Bearer token (replace with your actual token)
        String token = "YOUR_ACCESS_TOKEN";

        // Sample request body
        String requestBody = "{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"gender\": \"male\",\n" +
                "  \"email\": \"john.doe." + System.currentTimeMillis() + "@example.com\",\n" +
                "  \"status\": \"active\"\n" +
                "}";

        // Send POST request to create user
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .extract()
                .response();

        // Verify status code is 201
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        if (statusCode == 201) {
            System.out.println("User created successfully.");
        } else {
            System.out.println("Failed to create user.");
        }

        // Print response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        // Extract user ID and set as global variable (simulated)
        JsonPath jsonPath = new JsonPath(responseBody);
        int userId = jsonPath.getInt("id");
        System.out.println("Extracted User ID (APIID): " + userId);

        // Simulate setting global variable
        System.setProperty("APIID", String.valueOf(userId));
    }
}
