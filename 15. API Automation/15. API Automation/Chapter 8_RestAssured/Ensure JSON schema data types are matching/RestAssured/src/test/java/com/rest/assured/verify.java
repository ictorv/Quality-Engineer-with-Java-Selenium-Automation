package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class verify {

    @Test
    public void testPostSchemaValidation() {
        String uri = "https://jsonplaceholder.typicode.com/posts/2";
        RestAssured.useRelaxedHTTPSValidation();

        Response response = RestAssured
                .given()
                .when()
                .get(uri)
                .then()
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/post_schema.json")))
                .extract()
                .response();

        // Additional assertions (optional)
        Assert.assertTrue(response.jsonPath().getInt("userId") > 0, "userId should be a positive integer");
        Assert.assertTrue(response.jsonPath().getString("title").length() > 0, "title should not be empty");
    }
}
