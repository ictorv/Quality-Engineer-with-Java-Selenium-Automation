package chapter6;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdatePriceExistProductPrintUpdatePrice {

    @Test
    public void updateProductAndPrintPrice() {
        RestAssured.useRelaxedHTTPSValidation();

        String jsonBody = """
            {
                "name": "Apple MacBook Pro 16",
                "data": {
                    "year": 2019,
                    "price": 4000.99,
                    "CPU model": "Intel Core i9",
                    "Hard disk size": "1 TB",
                    "color": "silver"
                }
            }
        """;



        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .get("https://api.restful-api.dev/objects/7");

        // ✅ Verify success status
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        // 📦 Extract and print updated price
        float updatedPrice = response.jsonPath().getFloat("data.price");
        System.out.println("Updated Price: $" + updatedPrice);
    }
}
