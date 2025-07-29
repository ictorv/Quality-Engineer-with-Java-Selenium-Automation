package chapter6;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PrintSuccessResponseAddProduct {

    @Test
    public void printSuccessResponseForAddProduct() {
        String jsonBody = """
        {
            "name": "Dell I5",
            "data": {
                "year": 2023,
                "price": 20000,
                "CPU model": "Intel Core i9",
                "Hard disk size": "2 TB"
            }
        }
        """;

        RestAssured.useRelaxedHTTPSValidation();
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .post("https://api.restful-api.dev/objects");

        // Assert that status code is 200
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");

        // Print the full success response
        System.out.println("Success Response:\n" + response.getBody().asPrettyString());
    }
}
