import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import java.util.List;
import java.util.Map;

public class GetBooking {

    // Static initializer block to disable SSL certificate validation for Rest Assured.
    // WARNING: This is generally NOT recommended for production environments as it bypasses
    // critical security checks. Use it only for testing purposes where you explicitly
    // trust the target server and understand the security implications.
    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    // Define the base URI for the API
    private static final String BASE_URI = "https://restful-booker.herokuapp.com";
    // Define the endpoint for getting all bookings
    private static final String BOOKING_ENDPOINT = "/booking";

    @Test
    public void testGetAllBookingsAndPrintResponse() {
        // Set the base URI for Rest Assured
        RestAssured.baseURI = BASE_URI;

        System.out.println("Attempting to access: " + RestAssured.baseURI + BOOKING_ENDPOINT);

        // Perform the GET request
        Response response = RestAssured.given()
                .when()
                .get(BOOKING_ENDPOINT); // Send the GET request

        System.out.println("Response Status Code: " + response.getStatusCode());

        // 4. Retrieve the response body and parse it into JSON
        // The /booking endpoint returns a JSON array of booking IDs.
        // We can parse it as a List of Maps or a List of Integers if we only expect IDs.
        // For demonstration, we'll get the raw string and then use jsonPath() to extract.

        String responseBody = response.getBody().asString();
        System.out.println("Raw Response Body: " + responseBody);

        // Verify status code is 200 OK (common for successful GET requests)
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        System.out.println("Status Code Verification Passed: Expected 200, Actual " + response.getStatusCode());


        // 5. Print the values of JSON
        // The response is expected to be a JSON array of objects, each with a "bookingid"
        // Example: [{"bookingid": 1}, {"bookingid": 2}]
        try {
            List<Map<String, Integer>> bookings = response.jsonPath().getList("$");

            if (bookings.isEmpty()) {
                System.out.println("No bookings found in the response.");
            } else {
                System.out.println("\nParsed JSON Values (Booking IDs):");
                for (Map<String, Integer> booking : bookings) {
                    // Assuming each item in the array is an object like {"bookingid": 123}
                    if (booking.containsKey("bookingid")) {
                        System.out.println("Booking ID: " + booking.get("bookingid"));
                    } else {
                        System.out.println("Found an item without 'bookingid' key: " + booking);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing JSON response or response was not a JSON array of objects: " + e.getMessage());
            // If the response is just a simple array of numbers like [1, 2, 3]
            try {
                List<Integer> bookingIds = response.jsonPath().getList("$");
                System.out.println("\nParsed JSON Values (Booking IDs as Integers):");
                for (Integer id : bookingIds) {
                    System.out.println("Booking ID: " + id);
                }
            } catch (Exception innerE) {
                System.err.println("Could not parse response as a list of integers either: " + innerE.getMessage());
                System.err.println("Original response body: " + responseBody);
            }
        }
    }
}