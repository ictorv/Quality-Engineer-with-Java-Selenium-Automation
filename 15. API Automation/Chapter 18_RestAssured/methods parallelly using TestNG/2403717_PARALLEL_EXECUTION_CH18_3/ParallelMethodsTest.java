package com.example.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ParallelMethodsTest {

    @Test
    public void testGetPost1() {
        RestAssured
            .given()
            .when()
            .get("https://jsonplaceholder.typicode.com/posts/1")
            .then()
            .statusCode(200);
    }

    @Test
    public void testGetPost2() {
        RestAssured
            .given()
            .when()
            .get("https://jsonplaceholder.typicode.com/posts/2")
            .then()
            .statusCode(200);
    }

    @Test
    public void testGetPost3() {
        RestAssured
            .given()
            .when()
            .get("https://jsonplaceholder.typicode.com/posts/3")
            .then()
            .statusCode(200);
    }
}
