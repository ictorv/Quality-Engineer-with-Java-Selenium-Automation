package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class verify {

    @Test
    public void testUniversitiesInUS() {
        String uri = "http://universities.hipolabs.com/search?country=United+States";

        Response response = RestAssured
                .given()
                .when()
                .get(uri)
                .then()
                .statusCode(200)
                .extract()
                .response();

        int universityCount = response.jsonPath().getList("$").size();
        System.out.println("Number of universities in United States: " + universityCount);
    }
}
