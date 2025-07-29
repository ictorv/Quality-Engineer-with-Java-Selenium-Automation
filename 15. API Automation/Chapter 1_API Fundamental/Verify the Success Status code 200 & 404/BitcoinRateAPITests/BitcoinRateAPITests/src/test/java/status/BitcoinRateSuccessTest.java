package status;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BitcoinRateSuccessTest {

    @Test
    public void verifyStatusCode200() {
        Response response = RestAssured.get("https://api.coindesk.com/v1/bpi/currentprice.json");
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
    }
}