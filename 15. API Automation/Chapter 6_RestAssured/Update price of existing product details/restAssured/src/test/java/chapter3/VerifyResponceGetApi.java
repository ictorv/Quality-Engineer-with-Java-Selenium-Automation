package chapter3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyResponceGetApi {

    @Test
    public void verifyBasicAuthResponse() {
        // Bypass SSL certificate validation for testing
        RestAssured.useRelaxedHTTPSValidation();

        Response response = RestAssured
            .given()
            .auth()
            .preemptive() // Sends credentials without waiting for 401 challenge
            .basic("postman", "password")
            .when()
            .get("https://postman-echo.com/basic-auth");

        // Verify status code
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Verify response body
        boolean authenticated = response.jsonPath().getBoolean("authenticated");
        System.out.println("Response : "+authenticated);
        Assert.assertTrue(authenticated, "Authentication should be successful");
    }
}
