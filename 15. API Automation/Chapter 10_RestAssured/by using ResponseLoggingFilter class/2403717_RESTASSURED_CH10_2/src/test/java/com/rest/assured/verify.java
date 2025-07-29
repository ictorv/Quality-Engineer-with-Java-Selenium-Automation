package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class verify {

    @Test
    public void testGetUniversitiesInIndia() {
        String uri = "http://universities.hipolabs.com/search?country=India";

        Response response = RestAssured
                .given()
                .filter(new ResponseLoggingFilter()) // Logs response using filter
                .when()
                .get(uri)
                .then()
                .extract()
                .response();

        int universityCount = response.jsonPath().getList("$").size();
        System.out.println("Number of universities in India: " + universityCount);
    }
}
