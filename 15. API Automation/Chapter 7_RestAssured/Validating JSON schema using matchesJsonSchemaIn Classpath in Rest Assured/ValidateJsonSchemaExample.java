
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;

public class ValidateJsonSchemaExample {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String requestBody = "{\n" +
                "    \"firstname\": \"Jim\",\n" +
                "    \"lastname\": \"Brown\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        Response response = given()
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .post("/booking")
        .then()
            .assertThat()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("booking-schema.json"))
            .extract().response();

        System.out.println("Response Body:");
        System.out.println(response.asString());
    }
}
