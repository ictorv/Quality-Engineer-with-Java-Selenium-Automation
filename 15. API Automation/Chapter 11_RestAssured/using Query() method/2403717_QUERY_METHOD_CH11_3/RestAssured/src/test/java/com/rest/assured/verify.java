
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
    public void setup() {
        RestAssured.baseURI = "https://datausa.io";
        RestAssured.basePath = "/api/data";

        requestSpec = RestAssured
                .given()
                .queryParam("drilldowns", "Nation")
                .queryParam("measures", "Population")
                .queryParam("Year", "2021");

        response = requestSpec.get();
    }

    @Test
    public void printRequestDetails() {
        // Print Base URI and Base Path
        System.out.println("Base URI: " + RestAssured.baseURI);
        System.out.println("Base Path: " + RestAssured.basePath);

        // Print Query Parameters manually
        System.out.println("Query Parameters:");
        System.out.println("  drilldowns = Nation");
        System.out.println("  measures = Population");
        System.out.println("  Year = 2021");

        // Print Headers from the response
        System.out.println("Response Headers:");
        response.getHeaders().forEach(header ->
                System.out.println("  " + header.getName() + ": " + header.getValue()));
    }
}
