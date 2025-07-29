package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static String productId;

    @BeforeClass
    public void createProduct() {
        RestAssured.useRelaxedHTTPSValidation();

        String jsonBody = "{ \"name\": \"Test Product\", \"data\": { \"year\": 2025, \"price\": \"1999\" } }";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post("https://api.restful-api.dev/objects");

        productId = response.jsonPath().getString("id");
        System.out.println("Created product with ID: " + productId);
    }
}
