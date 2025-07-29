package chapter3;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Validate404StatusCodeAssured {
	
	@Test
	public void varifyStatusCode404() {
		
		Response response=RestAssured
				.given()
				.when()
				.get("https://the-internet.herokuapp.com/status_codes/404");
		System.out.println("status code : "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 404,"Expected status code is 404");
	}
}
