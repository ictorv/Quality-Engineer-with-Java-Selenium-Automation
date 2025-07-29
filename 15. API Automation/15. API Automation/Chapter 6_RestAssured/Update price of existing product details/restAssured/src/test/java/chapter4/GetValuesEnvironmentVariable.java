package chapter4;


import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetValuesEnvironmentVariable {

    @Test
    public void createAndRetrieveUser() {
        RestAssured.useRelaxedHTTPSValidation();

        // 🔐 Access token (replace with your actual token or load from env/config)
        String token = "148462805e457d36d9e4e6ea46addfc0ae8eb6b0b1727f57fd8e582502549192";

        // 📧 Dynamic email to avoid duplication
        String email = "john.doe" + System.currentTimeMillis() + "@example.com";

        // 🚀 Step 1: Create user (POST)
        Response postResponse = RestAssured
            .given()
                .baseUri("https://gorest.co.in/public/v2")
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"John Doe\", \"gender\": \"male\", \"email\": \"" + email + "\", \"status\": \"active\" }")
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .extract().response();

        int userId = postResponse.jsonPath().getInt("id");
        System.out.println("✅ Created User ID: " + userId);

        // ✅ Validate POST response
        Assert.assertEquals(postResponse.jsonPath().getString("name"), "John Doe");
        Assert.assertEquals(postResponse.jsonPath().getString("status"), "active");

        // 🔍 Step 2: Retrieve user (GET)
        Response getResponse = RestAssured
            .given()
                .baseUri("https://gorest.co.in/public/v2")
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
            .when()
                .get("/users/" + userId)
            .then()
                .statusCode(200)
                .extract().response();

        // ✅ Validate GET response
        Assert.assertEquals(getResponse.jsonPath().getInt("id"), userId);
        Assert.assertEquals(getResponse.jsonPath().getString("email"), email);
        Assert.assertEquals(getResponse.jsonPath().getString("status"), "active");
        System.out.println("📦 Retrieved User: " + getResponse.asPrettyString());
    }
}
