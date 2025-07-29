package chapter3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetBaseUriResponseRestAssured {

    @Test
    public void getPostByIdUsingBaseUri() {
    	
    	RestAssured.useRelaxedHTTPSValidation();
        // Step 5: Set base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Step 4: Use path parameter id=5
        Response response = RestAssured
            .given()
            .pathParam("id", 5)
            .when()
            .get("/posts/{id}");

        // Step 6: Print response values
        System.out.println("Response Body:\n" + response.getBody().asString());

        String title = response.jsonPath().getString("title");
        String body = response.jsonPath().getString("body");
        System.out.println("Title: " + title);
        System.out.println("Body: " + body);
    }
}
