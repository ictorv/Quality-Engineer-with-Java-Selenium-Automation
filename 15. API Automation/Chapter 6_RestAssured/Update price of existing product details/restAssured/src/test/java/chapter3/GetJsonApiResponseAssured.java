package chapter3;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetJsonApiResponseAssured {

	@Test
	public void getBookingData() {
		Response response=RestAssured
				.given()
				.when()
				.get(" https://restful-booker.herokuapp.com/booking");
		List<Integer> bookingIDs=response.jsonPath().getList("bookingid");
		System.out.println("Booking ID's and Count id is ="+bookingIDs.size());
		for(Integer id:bookingIDs) {
			System.out.println(id);
		}
		
	}
}
