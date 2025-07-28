package chapter4;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SendingFirstApiRequest {
	@Test
    public void sendFirstApiRequestVerify() {
        Response response = RestAssured
            .given()
                .baseUri("https://simple-tool-rental-api.glitch.me")
            .when()
                .get("/status");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
