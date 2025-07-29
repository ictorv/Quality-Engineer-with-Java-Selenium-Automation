package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteNonExistingProductTest extends BaseTest {

    @Test(priority = 2)
    public void deleteNonExistingProduct() {
        RestAssured.useRelaxedHTTPSValidation();

        String uri = "https://api.restful-api.dev/objects/" + productId;

        Response response = RestAssured
                .given()
                .delete(uri);

        int statusCode = response.getStatusCode();
        System.out.println("Second DELETE status code: " + statusCode);

        Assert.assertTrue(
            statusCode == 404 || statusCode == 200,
            "Expected status code 404 or 200 for non-existing product deletion, but got: " + statusCode
        );
    }
}
