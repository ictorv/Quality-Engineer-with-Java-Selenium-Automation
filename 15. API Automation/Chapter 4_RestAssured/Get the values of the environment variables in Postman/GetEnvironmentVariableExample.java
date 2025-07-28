
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

public class GetEnvironmentVariableExample {
    public static void main(String[] args) {
        // Replace with your actual Bearer token
        String bearerToken = "YOUR_BEARER_TOKEN";
        // Replace with your actual APIID
        String apiId = "YOUR_API_ID";

        // Set the base URI
        RestAssured.baseURI = "https://gorest.co.in";

        // Send GET request to the API
        Response response = RestAssured
            .given()
                .header("Authorization", "Bearer " + bearerToken)
            .when()
                .get("/public/v2/users/" + apiId)
            .then()
                .statusCode(200)
                .extract().response();

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body:");
        System.out.println(responseBody);

        // Extract the ID from the response
        JsonPath jsonPath = new JsonPath(responseBody);
        int extractedId = jsonPath.getInt("id");
        System.out.println("Extracted User ID: " + extractedId);

        // Simulate retrieving environment variable (in Postman this would be dynamic)
        String environmentVariable = System.getenv("APIID");
        System.out.println("Environment Variable APIID: " + environmentVariable);
    }
}
