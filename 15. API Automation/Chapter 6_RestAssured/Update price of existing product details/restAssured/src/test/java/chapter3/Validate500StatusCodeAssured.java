package chapter3;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Validate500StatusCodeAssured {
	
	@Test
	public void varifyStatusCode500() {
		
		Response response=RestAssured
				.given()
				.when()
				.get("https://the-internet.herokuapp.com/status_codes/500");
		System.out.println("status code : "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 500,"Expected status code is 500");
	}
}
