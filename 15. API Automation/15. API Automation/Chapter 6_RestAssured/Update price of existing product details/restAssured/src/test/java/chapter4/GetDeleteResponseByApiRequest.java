package chapter4;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetDeleteResponseByApiRequest {

    @Test
    public void deleteBookingById() {
        int bookingId = 3; // Replace with actual booking ID

        Response response = RestAssured
            .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .auth()
                    .preemptive()
                    .basic("admin", "password123")
                .contentType(ContentType.JSON)
            .when()
                .delete("/booking/" + bookingId);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
