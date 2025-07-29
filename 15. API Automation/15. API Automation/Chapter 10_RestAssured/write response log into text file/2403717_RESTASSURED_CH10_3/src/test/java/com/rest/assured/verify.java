package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

public class verify {

    @Test
    public void testGetUniversitiesInCanada() {
        String uri = "http://universities.hipolabs.com/search?country=canada";

        // Send GET request with response logging filter
        Response response = RestAssured
                .given()
                .filter(new ResponseLoggingFilter())
                .when()
                .get(uri)
                .then()
                .extract()
                .response();

        // Count universities
        int universityCount = response.jsonPath().getList("$").size();
        System.out.println("Number of universities in Canada: " + universityCount);

        // Write response to a text file
        try (FileWriter writer = new FileWriter("canada_universities_response.txt")) {
            writer.write(response.asPrettyString());
            System.out.println("Response written to canada_universities_response.txt");
        } catch (IOException e) {
            System.err.println("Error writing response to file: " + e.getMessage());
        }
    }
}
