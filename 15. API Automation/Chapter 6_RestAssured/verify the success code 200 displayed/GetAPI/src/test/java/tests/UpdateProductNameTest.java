package tests;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static io.restassured.RestAssured.given;
public class UpdateProductNameTest {

    @Test
    public void updateProductNameAndVerifyStatusCode() {
        RestAssured.baseURI = "https://api.restful-api.dev/objects";
        RestAssured.config = RestAssured.config()
            .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

        // ✅ Step 1: Create product first to get a valid ID
        String createBody = """
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

        Response postResponse = given()
            .contentType(ContentType.JSON)
            .body(createBody)
            .when()
            .post()
            .then()
            .statusCode(200)
            .extract().response();

        String productId = postResponse.jsonPath().getString("id");
        System.out.println("Created Product ID: " + productId);

        // ✅ Step 2: Update only the name using PATCH
        String patchBody = """
        {
            "name": "Apple MacBook Pro 16 (Updated Name)"
        }
        """;

        Response patchResponse = given()
            .contentType(ContentType.JSON)
            .body(patchBody)
            .when()
            .patch("/" + productId)
            .then()
            .extract().response();

        System.out.println("Status Code: " + patchResponse.getStatusCode());
        System.out.println("Response Body:\n" + patchResponse.asPrettyString());

        // ✅ Final assertion
        assertEquals(patchResponse.getStatusCode(), 200, "Expected status code 200");
    }
}
