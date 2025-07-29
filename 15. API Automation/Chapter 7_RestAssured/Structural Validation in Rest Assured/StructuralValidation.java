
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;

public class StructuralValidation {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Create request body
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

        // Send POST request and validate structure
        Response response = given()
            .header("Content-Type", "application/json")
            .body(requestParams.toString())
        .when()
            .post("/booking")
        .then()
            .statusCode(200)
            .body("bookingid", notNullValue())
            .body("booking.firstname", equalTo("Jim"))
            .body("booking.lastname", equalTo("Brown"))
            .body("booking.totalprice", equalTo(111))
            .body("booking.depositpaid", equalTo(true))
            .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
            .body("booking.bookingdates.checkout", equalTo("2019-01-01"))
            .body("booking.additionalneeds", equalTo("Breakfast"))
            .extract().response();

        // Print response
        System.out.println("Response:
" + response.asPrettyString());
    }
}
