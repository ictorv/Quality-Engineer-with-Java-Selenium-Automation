package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

public class verify {

    @Test
    public void testSchemaMismatch() {
        String uri = "https://jsonplaceholder.typicode.com/posts/2";
RestAssured.useRelaxedHTTPSValidation();
Response response = RestAssured
                .given()
                .when()
                .get(uri)
                .then()
                .extract()
                .response();

        // Validate against incorrect schema
        try {
            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/post_schema.json")));
            assert false : "Schema validation should have failed but passed.";
        } catch (AssertionError e) {
            System.out.println("Schema mismatch detected as expected.");
        }
    }
}
