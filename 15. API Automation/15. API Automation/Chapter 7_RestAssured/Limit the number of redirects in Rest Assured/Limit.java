package API;


	import io.restassured.RestAssured;
	import io.restassured.response.Response;
	import static io.restassured.RestAssured.*;
	import static org.hamcrest.Matchers.*;

	public class Limit {
	    public static void main(String[] args) {
	        // Set base URI
	        RestAssured.baseURI = "https://simple-tool-rental-api.glitch.me";

	        // Send GET request and limit redirects
	        Response response = given()
	            .redirects().max(1)
	        .when()
	            .get("/status")
	        .then()
	            .statusCode(200) // Validate status code
	            .body("status", notNullValue()) // Validate response body
	            .extract().response();

	        // Print the response
	        System.out.println("Response Body:");
	        System.out.println(response.asPrettyString());
	    }
	}


