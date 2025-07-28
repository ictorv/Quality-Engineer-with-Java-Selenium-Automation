
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class CreatePostmanEnvironment {
    public static void main(String[] args) {
        // Replace with your actual Postman API key
        String apiKey = "YOUR_POSTMAN_API_KEY";

        // Base URI for Postman API
        RestAssured.baseURI = "https://api.getpostman.com/environments";

        // Create JSON body for the environment
        JSONObject environment = new JSONObject();
        environment.put("name", "MyEnvironment");

        JSONObject variable = new JSONObject();
        variable.put("key", "baseUrl");
        variable.put("value", "https://example.com");
        variable.put("enabled", true);

        environment.put("values", new org.json.JSONArray().put(variable));

        JSONObject requestBody = new JSONObject();
        requestBody.put("environment", environment);

        // Send POST request to create environment
        Response response = RestAssured
                .given()
                .header("X-Api-Key", apiKey)
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post();

        // Print response
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
