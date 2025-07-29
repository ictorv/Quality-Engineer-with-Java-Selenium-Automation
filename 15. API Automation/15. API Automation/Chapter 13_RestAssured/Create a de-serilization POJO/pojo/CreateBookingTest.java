import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Booking;
import pojo.BookingDates;

public class CreateBookingTest {
    public static void main(String[] args) {
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");

        Booking booking = new Booking();
        booking.setFirstname("Jim");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(booking)
            .when()
            .post("https://example.com/booking")
            .then()
            .statusCode(200);
    }
}
