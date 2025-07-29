package chapter3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Validate200StatusCodeAssured {

    @Test
    public void verifyStatusCode200() {
        Response response = RestAssured
            .given()
            .when()
            .get("https://the-internet.herokuapp.com/status_codes/200");

        // Validate status code
        System.out.println("status code : "+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
    }
}
