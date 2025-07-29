package chapter3;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Validate301StatusCodeAssured {
	@Test
	public void verifyStatusCode301() {
		
		Response response=RestAssured
				.given()
				.redirects().follow(false) // Prevent automatic redirect
				.when()
				.get("https://the-internet.herokuapp.com/status_codes/301");
		System.out.println("status code : "+response.getStatusCode());
		
		Assert.assertEquals(response.getStatusCode(), 301,"Expected status code is 301");
		
	}
}
