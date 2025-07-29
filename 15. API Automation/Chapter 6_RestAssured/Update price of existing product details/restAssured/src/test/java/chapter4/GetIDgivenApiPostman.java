package chapter4;


import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetIDgivenApiPostman {

    @Test
    public void createPostAndValidateResponse() {
        RestAssured.useRelaxedHTTPSValidation();

        // 📝 Sample request body (can be loaded from file if needed)
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        // 🚀 Send POST request
        Response response = RestAssured
            .given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post("/posts")
            .then()
                .statusCode(201)
                .extract().response();

        // 📦 Extract ID from response
        int postId = response.jsonPath().getInt("id");
        System.out.println("✅ Created Post ID: " + postId);

        // ✅ Validate response body
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
        Assert.assertEquals(response.jsonPath().getString("body"), "bar");
        Assert.assertEquals(response.jsonPath().getInt("userId"), 1);
        Assert.assertTrue(postId > 0, "Post ID should be a positive integer");
    }
}
