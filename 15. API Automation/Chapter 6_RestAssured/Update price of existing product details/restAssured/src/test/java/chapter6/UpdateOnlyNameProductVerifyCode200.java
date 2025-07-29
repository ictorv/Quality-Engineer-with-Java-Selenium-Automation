package chapter6;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateOnlyNameProductVerifyCode200 {

    @Test
    public void updateProductNameAndVerifyStatusCode() {
        RestAssured.useRelaxedHTTPSValidation();

        String jsonBody = """
            {
                "name": "Apple MacBook Pro 16 (Updated Name)"
            }
        """;

      

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .get("https://api.restful-api.dev/objects");

        // ✅ Verify HTTP 200 success
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        // 🖨️ Optional: Print response
        System.out.println("Updated Name Response:\n" + response.getBody().asPrettyString());
    }
}
