package cucumber.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class CucumberClass {
    int responseCode;
	@Given("I send a GET request to the population API")
	public void sendingRequest() {
		Response response = RestAssured.given()
                .baseUri("https://datausa.io")
                .basePath("/api/data")
                .queryParam("drilldowns","Nation")
                .queryParam("measures","Population")
                .queryParam("Year", "2021")
             .get();
		responseCode = (int)response.getStatusCode();
		System.out.println("DATA  :::: "+response.asPrettyString());
	}

	@Then("the response status code should be {int}")
	public void verifyingStatus(int int1) {
		if(int1==responseCode) System.out.println("PASSED");
		else System.out.println("FAILED");
	}
}
