
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ListSizeValidation {
    public static void main(String[] args) {
        // Base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Request body
        JSONObject requestParams = new JSONObject();
        requestParams.put("firstname", "Jim");
        requestParams.put("lastname", "Brown");
        requestParams.put("totalprice", 111);
        requestParams.put("depositpaid", true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        requestParams.put("bookingdates", bookingDates);
        requestParams.put("additionalneeds", "Breakfast");

        // Send POST request and validate response
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toString())
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract().response();

        // Print the response
        System.out.println("Response:");
        System.out.println(response.asString());

        // Since the response does not contain a list, we demonstrate list size check on a dummy list
        // This part is illustrative and should be adapted based on actual API response structure
        given()
            .when()
            .get("/booking")
            .then()
            .statusCode(200)
            .body("bookingid", hasSize(greaterThan(0))); // assuming 'bookingid' is a list in GET /booking
    }
}
