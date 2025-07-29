package chapter6;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdatePriceExistDataVerifyCode200 {

    @Test
    public void verifyStatusCodeForPriceUpdate() {
        RestAssured.useRelaxedHTTPSValidation();

        // Step 1: Create the object (initial POST)
        String initialJson = """
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

        Response createResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(initialJson)
                .post("https://api.restful-api.dev/objects");

        String objectId = createResponse.jsonPath().getString("id");
        Assert.assertNotNull(objectId, "Object ID should not be null");

        // Step 2: Update with new price (PUT)
        String updatedJson = """
            {
                "name": "Apple MacBook Pro 16",
                "data": {
                    "year": 2019,
                    "price": 3000.99,
                    "CPU model": "Intel Core i9",
                    "Hard disk size": "1 TB",
                    "color": "silver"
                }
            }
        """;

        Response updateResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(updatedJson)
                .put("https://api.restful-api.dev/objects/" + objectId);

        // Step 3: Verify response
        Assert.assertEquals(updateResponse.getStatusCode(), 200, "Expected status code 200");
        System.out.println("Updated Response:\n" + updateResponse.getBody().asPrettyString());
    }
}
