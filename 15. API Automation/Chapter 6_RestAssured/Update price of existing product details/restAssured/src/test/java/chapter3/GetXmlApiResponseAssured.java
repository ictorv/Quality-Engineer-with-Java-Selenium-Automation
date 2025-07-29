package chapter3;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetXmlApiResponseAssured {

	@Test
	public void getBookingXmlRespose() {
		Response response=RestAssured
				.given()
				.contentType("application/xml")
				.accept("application/xml")
				.when()
				.get("https://restful-booker.herokuapp.com/booking/119");
		// Print raw XML response
		String xmlBody=response.getBody().asString();
		System.out.println("Raw XML Response : \n"+ xmlBody);
		
		// Parse XML using XmlPath
		XmlPath xmlPath=new XmlPath(xmlBody);
		
		// Example : Extract ad print values
		String firstName=xmlPath.getString("booking.firstname");
		String lastName=xmlPath.getString("booking.lastname");
		
		System.out.println("Frist Name : "+firstName);
		System.out.println("Last Name : "+lastName);
	}
}
