package chapter6;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyStatusCode200AddProduct {

    @Test
    public void verifyStatusCodeForAddProductRequest() {
        String jsonBody = """
            {
                "name": "Apple MacBook Pro 16",
                "data": {
                    "year": 2019,
                    "price": 1849.99,
                    "CPU model": "Intel Core i9",
                    "Hard disk size": "1 TB"
                }
            }
        """;
        RestAssured.useRelaxedHTTPSValidation();
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .post("https://api.restful-api.dev/objects");

        // Assert that status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");

        // Print full response
        System.out.println("Response:\n" + response.getBody().asPrettyString());
    }
}
