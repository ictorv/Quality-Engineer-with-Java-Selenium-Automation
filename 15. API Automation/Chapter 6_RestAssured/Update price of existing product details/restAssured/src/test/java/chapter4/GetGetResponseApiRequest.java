package chapter4;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetGetResponseApiRequest {
	@Test
    public void getResponseByApiRequest() {
        String jsonBody = """
        {
            "username": "admin",
            "password": "password123"
        }""";

        Response response = RestAssured
            .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(jsonBody)
            .when()
                .post("/auth");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
