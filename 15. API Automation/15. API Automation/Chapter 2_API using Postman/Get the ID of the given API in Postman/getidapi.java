package Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getidapi {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

    @Test
    public void createNewPost() {
        // 🔧 Bypass SSL certificate validation
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.config = RestAssuredConfig.config()
            .sslConfig(new SSLConfig().relaxedHTTPSValidation());

        // 📝 Request body
        String requestBody = "{\n" +
                "  \"title\": \"Automation with RestAssured\",\n" +
                "  \"body\": \"This is a test post created using Rest Assured.\",\n" +
                "  \"userId\": 101\n" +
                "}";

        // 🚀 Send POST request
        Response response = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post(BASE_URL)
            .then()
                .extract().response();

        // 📋 Log response
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:\n" + response.prettyPrint());

        // ✅ Validate status code
        Assert.assertEquals(response.statusCode(), 201, "Expected status code 201");

        // 🔍 Validate response body
        Assert.assertEquals(response.jsonPath().getString("title"), "Automation with RestAssured");
        Assert.assertEquals(response.jsonPath().getString("body"), "This is a test post created using Rest Assured.");
        Assert.assertEquals(response.jsonPath().getInt("userId"), 101);
        Assert.assertTrue(response.jsonPath().getInt("id") > 0, "ID should be auto-generated and greater than 0");

        System.out.println("✅ Post created successfully with ID: " + response.jsonPath().getInt("id"));
    }
}
