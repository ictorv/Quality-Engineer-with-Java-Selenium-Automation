package chapter3;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetDoubleQueryParameterApiResponseAssured {

    @Test
    public void getUsersWithDoubleQueryParam() {
    	RestAssured.useRelaxedHTTPSValidation();
    	
        Response response = RestAssured
            .given()
            .queryParam("page", 2)
            .queryParam("id", 5)
            .when()
            .get("https://reqres.in/api/users");

        // Print full response body
        System.out.println("Response Body:\n" + response.getBody().asString());

        // Example: Print specific user details if available
        String firstName = response.jsonPath().getString("data[0].first_name");
        String lastName = response.jsonPath().getString("data[0].last_name");
        System.out.println("User: " + firstName + " " + lastName);
    }
}
