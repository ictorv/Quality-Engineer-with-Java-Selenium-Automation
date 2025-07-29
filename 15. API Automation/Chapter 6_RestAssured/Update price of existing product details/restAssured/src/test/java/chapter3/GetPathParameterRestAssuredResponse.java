package chapter3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetPathParameterRestAssuredResponse {

    @Test
    public void getPostById() {
    	
    	RestAssured.useRelaxedHTTPSValidation();
        Response response = RestAssured
            .given()
            .pathParam("id", 5)
            .when()
            .get("https://jsonplaceholder.typicode.com/posts/{id}");

        // Print full response body
        System.out.println("Response Body:\n" + response.getBody().asString());

        // Example: Print specific fields
        String title = response.jsonPath().getString("title");
        String body = response.jsonPath().getString("body");
        System.out.println("Title: " + title);
        System.out.println("Body: " + body);
    }
}
