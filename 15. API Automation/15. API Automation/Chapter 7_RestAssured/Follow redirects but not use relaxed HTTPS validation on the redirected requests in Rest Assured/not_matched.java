package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertFalse;
//import static org.testng.Assert.assertTrue;




	public class not_matched {

	    @Test
	    public void validateJsonPlaceholderPostSchemaMismatch() {
	        // Send GET request
	        Response response = RestAssured
	            .given()
	            .relaxedHTTPSValidation()
	            .when()
	            .get("https://jsonplaceholder.typicode.com/posts/2")
	            .then()
	            .statusCode(200)
	            .extract().response();

	        // Print response
	        System.out.println("Response Body:");
	        System.out.println(response.asPrettyString());

	        // Try schema validation and catch mismatch
	        boolean schemaMatches = true;
	        try {
	            response.then().body(matchesJsonSchemaInClasspath("schema/post-schema.json"));
	        } catch (AssertionError e) {
	            schemaMatches = false;
	            System.out.println("Schema mismatch detected: " + e.getMessage());
	        }

	        // Assert that schema does NOT match
	        assertFalse(schemaMatches, "Expected schema mismatch but schema matched.");
	    }
	}



