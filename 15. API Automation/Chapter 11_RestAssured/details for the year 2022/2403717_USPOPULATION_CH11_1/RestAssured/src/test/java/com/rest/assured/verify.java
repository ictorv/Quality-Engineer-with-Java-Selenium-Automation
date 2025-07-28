package com.rest.assured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class verify {

    RequestSpecification requestSpec;
    Response response;

    @BeforeClass
    public void setupRequestSpec() {
        requestSpec = RestAssured
                .given()
                .baseUri("https://datausa.io")
                .basePath("/api/data")
                .queryParam("drilldowns", "Nation")
                .queryParam("measures", "Population")
                .queryParam("Year", "2022");

        response = requestSpec.get();
    }

    @Test
    public void printResponseDetails() {
        System.out.println("Full Response:");
        System.out.println(response.getBody().asPrettyString());

        // Corrected JsonPath expressions with single quotes
        String idNation = response.jsonPath().getString("data[0].'ID Nation'");
        String nation = response.jsonPath().getString("data[0].Nation");
        String idYear = response.jsonPath().getString("data[0].'ID Year'");
        String population = response.jsonPath().getString("data[0].Population");
        String slugNation = response.jsonPath().getString("data[0].'Slug Nation'");
        String sourceName = response.jsonPath().getString("source[0].annotations.source_name");
        String sourceDescription = response.jsonPath().getString("source[0].annotations.source_description");

        System.out.println("ID Nation: " + idNation);
        System.out.println("Nation: " + nation);
        System.out.println("ID Year: " + idYear);
        System.out.println("Population: " + population);
        System.out.println("Slug Nation: " + slugNation);
        System.out.println("Source Name: " + sourceName);
        System.out.println("Source Description: " + sourceDescription);
    }

}
