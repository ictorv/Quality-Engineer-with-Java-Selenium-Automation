package chapter4;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetPutResponseApiRequest {

    @Test
    public void updateBookingById() throws IOException {
        int bookingId = 1; // Replace with actual booking ID
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/input.json")));

        Response response = RestAssured
            .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .auth()
                    .preemptive()
                    .basic("admin", "password123")
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .put("/booking/" + bookingId);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
