package chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetPostResponseApiRequest {
	
	@Test
    public static String getResponseByApiRequest() {
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
        return response.jsonPath().getString("token");
	}
	
	@Test
	public void getPostResponseApiRequest() throws IOException {
	    String requestBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\2407322\\eclipse-workspace\\restAssured\\src\\test\\resources\\input.json")));

	    Response response = RestAssured
	        .given()
	            .contentType(ContentType.JSON)
	            .body(requestBody)
	        .when()
	            .post("https://restful-booker.herokuapp.com/booking");

	    System.out.println("Status Code: " + response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());
	}

}
