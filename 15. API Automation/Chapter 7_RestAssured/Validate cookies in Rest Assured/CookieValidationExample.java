import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;

public class CookieValidationExample {

    public static void main(String[] args) {

        // Base URI
        RestAssured.baseURI = "https://postman-echo.com";

        // Send GET request with cookie
        Response response = RestAssured
                .given()
                    .cookie("Key", "Communication")
                .when()
                    .get("/cookies/set?Key=Communication")
                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        // Extract and print all cookies
        Cookies allCookies = response.detailedCookies();
        System.out.println("All Cookies:");
        allCookies.asList().forEach(System.out::println);

        // Extract specific cookie
        Cookie specificCookie = allCookies.get("Key");
        System.out.println("\nSpecific Cookie:");
        System.out.println(specificCookie);

        // Validate the specific cookie value
        if (specificCookie != null && "Communication".equals(specificCookie.getValue())) {
            System.out.println("\nCookie validation passed: Key=Communication");
        } else {
            System.out.println("\nCookie validation failed!");
        }

        // Validate cookie exists
        if (response.getCookies().containsKey("Key")) {
            System.out.println("Cookie 'Key' exists in the response.");
        } else {
            System.out.println("Cookie 'Key' does not exist in the response.");
        }
    }
}