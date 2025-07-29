import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Booking;
import pojo.BookingDates;

public class CreateBookingTest {
    public static void main(String[] args) {
        try {
            // Create BookingDates object
            BookingDates bookingDates = new BookingDates();
            bookingDates.setCheckin("2018-01-01");
            bookingDates.setCheckout("2019-01-01");

            // Create Booking object
            Booking booking = new Booking();
            booking.setFirstname("Jim");
            booking.setLastname("Brown");
            booking.setTotalprice(111);
            booking.setDepositpaid(true);
            booking.setBookingdates(bookingDates);
            booking.setAdditionalneeds("Breakfast");

            // Serialize Booking object to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(booking);

            // Send POST request and print response
            String response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract()
                .asString();

            System.out.println("Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
