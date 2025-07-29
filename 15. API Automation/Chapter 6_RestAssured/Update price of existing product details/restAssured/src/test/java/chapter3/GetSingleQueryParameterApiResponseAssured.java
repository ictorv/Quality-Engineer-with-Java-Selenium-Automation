package chapter3;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetSingleQueryParameterApiResponseAssured {
	@Test
	public void getUsersPage2() {
		// Bypass SSL certificate validation for testing
		RestAssured.useRelaxedHTTPSValidation();
		Response response=RestAssured
				.given()
				.queryParam("page", 2)
				.when()
				.get("https://reqres.in/api/users");
		
		// Print full response body
		System.out.println("Response  Body:\n"+response.getBody().asString());
		
		//Example : Print first user's name
		String firstName=response.jsonPath().getString("data[0].first_name");
		String lastName=response.jsonPath().getString("data[0].last_name");
		System.out.println("First user on Page 2: "+firstName+" "+lastName);
	}
}
