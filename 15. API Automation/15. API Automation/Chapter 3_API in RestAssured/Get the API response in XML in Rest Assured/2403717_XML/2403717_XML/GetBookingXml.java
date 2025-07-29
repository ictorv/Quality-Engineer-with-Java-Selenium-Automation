import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class GetBookingXmlTest {

    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    // Define the base URI for the API
    private static final String BASE_URI = "https://restful-booker.herokuapp.com";
    // Define the endpoint for getting a specific booking by ID
    private static final String BOOKING_ENDPOINT = "/booking/{id}";
    // Define the ID to be used in the request
    private static final int BOOKING_ID = 119; // Using the ID provided in the prompt

    @Test
    public void testGetBookingXmlResponse() {
        // Set the base URI for Rest Assured
        RestAssured.baseURI = BASE_URI;

        System.out.println("Attempting to access: " + RestAssured.baseURI + BOOKING_ENDPOINT.replace("{id}", String.valueOf(BOOKING_ID)));
        System.out.println("Requesting Content-Type: application/xml and Accept: application/xml");

        // Perform the GET request with specified headers and path parameter
        Response response = RestAssured.given()
                .header("Content-Type", "application/xml") // Set Content-Type header
                .header("Accept", "application/xml")       // Set Accept header
                .pathParam("id", BOOKING_ID)               // Set the path parameter
                .when()
                .get(BOOKING_ENDPOINT); // Send the GET request

        System.out.println("Response Status Code: " + response.getStatusCode());

        // Verify status code is 200 OK (common for successful GET requests)
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        System.out.println("Status Code Verification Passed: Expected 200, Actual " + response.getStatusCode());

        // Print the values of the response in the console
        String responseBody = response.getBody().asString();
        System.out.println("\nResponse Body (XML):");
        System.out.println(responseBody);

        // Optional: You can also use xmlPath() to extract specific values from the XML
        // For example, to get the first name:
        // String firstName = response.xmlPath().getString("booking.firstname");
        // System.out.println("First Name from XML: " + firstName);
        // Note: The structure of the XML response might vary, so adjust the path accordingly.
    }
}