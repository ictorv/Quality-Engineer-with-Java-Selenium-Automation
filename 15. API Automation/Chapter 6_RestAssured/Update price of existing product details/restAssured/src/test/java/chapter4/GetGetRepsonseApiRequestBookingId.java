package chapter4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetGetRepsonseApiRequestBookingId {

    @Test
    public void getBookingIds() {
        Response response = RestAssured
            .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
            .when()
                .get("/booking");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
