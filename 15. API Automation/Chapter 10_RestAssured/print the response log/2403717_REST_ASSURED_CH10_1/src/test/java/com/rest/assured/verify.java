package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class verify {

    @Test
    public void testGetUniversitiesInUS() {
        String uri = "http://universities.hipolabs.com/search?country=United+States";

        Response response = RestAssured
                .given()
                .when()
                .get(uri)
                .then()
                .log().all() // Logs the full response
                .extract()
                .response();

        int universityCount = response.jsonPath().getList("$").size();
        System.out.println("Number of universities in United States: " + universityCount);
    }
}
