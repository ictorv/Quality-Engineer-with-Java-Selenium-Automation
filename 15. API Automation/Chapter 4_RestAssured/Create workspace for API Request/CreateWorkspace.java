
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class CreateWorkspace {
    public static void main(String[] args) {
        // Replace with your actual Postman API key
        String apiKey = "YOUR_POSTMAN_API_KEY";

        // Base URI for Postman API
        RestAssured.baseURI = "https://api.getpostman.com";

        // Create JSON body for workspace creation
        JSONObject workspaceDetails = new JSONObject();
        JSONObject workspace = new JSONObject();
        workspace.put("name", "My API Workspace");
        workspace.put("type", "personal"); // or "team"
        workspace.put("description", "Workspace for API Request automation");
        workspaceDetails.put("workspace", workspace);

        // Send POST request to create workspace
        Response response = RestAssured
            .given()
                .header("X-Api-Key", apiKey)
                .contentType(ContentType.JSON)
                .body(workspaceDetails.toString())
            .when()
                .post("/workspaces")
            .then()
                .extract().response();

        // Print response
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
