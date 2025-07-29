package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class verify {

    ResponseSpecification responseSpec;
    Response response;

    @BeforeClass
    public void setupResponseSpec() {
        // Set base URI and path
        RestAssured.baseURI = "https://datausa.io";
        RestAssured.basePath = "/api/data";

        // Initial response spec (can be reused or overwritten)
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        // Overwrite response spec for year 2020
        response = RestAssured
                .given()
                .queryParam("drilldowns", "Nation")
                .queryParam("measures", "Population")
                .queryParam("Year", "2020")
                .when()
                .get()
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    @Test
    public void printResponseFields() {
        System.out.println("ID Nation: " + response.jsonPath().getString("data[0].'ID Nation'"));
        System.out.println("Nation: " + response.jsonPath().getString("data[0].Nation"));
        System.out.println("ID Year: " + response.jsonPath().getString("data[0].'ID Year'"));
        System.out.println("Population: " + response.jsonPath().getString("data[0].Population"));
        System.out.println("Slug Nation: " + response.jsonPath().getString("data[0].'Slug Nation'"));
        System.out.println("Source Name: " + response.jsonPath().getString("source[0].annotations.source_name"));
        System.out.println("Source Description: " + response.jsonPath().getString("source[0].annotations.source_description"));
    }
}
