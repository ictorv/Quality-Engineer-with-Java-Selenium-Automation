package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class verify {

    @Test
    public void uploadFileTest() {
        // Path to the file in the resources folder
        File fileToUpload = new File("src/main/resources/sample.txt");

        // Make sure the file exists
        Assert.assertTrue(fileToUpload.exists(), "File does not exist in resources folder");

        // Send POST request with multipart file
        Response response = RestAssured
                .given()
                .multiPart("file", fileToUpload)
                .post("https://the-internet.herokuapp.com/upload");

        // Print response for debugging
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());

        // Verify status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
    }
}
