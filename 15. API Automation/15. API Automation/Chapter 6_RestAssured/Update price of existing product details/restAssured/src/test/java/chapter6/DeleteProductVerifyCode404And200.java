package chapter6;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteProductVerifyCode404And200 {
	@Test(priority = 1)
	public void deleteExistingProduct() {
	    RestAssured.useRelaxedHTTPSValidation();

	    Response response = RestAssured.given()
	            .delete("https://api.restful-api.dev/objects/7");

	    System.out.println("Status: " + response.getStatusCode());
	    Assert.assertEquals(response.getStatusCode(), 200);
	}


	@Test(priority = 2)
	public void deleteNonExistentProduct() {
		RestAssured.useRelaxedHTTPSValidation();
	    Response response = RestAssured.given()
	            .delete("https://api.restful-api.dev/objects/99999"); // Likely nonexistent
	    System.out.println("Status: " + response.getStatusCode());
	    Assert.assertEquals(response.getStatusCode(), 404);
	}

}
