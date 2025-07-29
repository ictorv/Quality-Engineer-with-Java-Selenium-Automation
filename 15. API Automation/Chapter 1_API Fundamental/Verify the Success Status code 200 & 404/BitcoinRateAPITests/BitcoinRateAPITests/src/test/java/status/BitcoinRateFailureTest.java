package status;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BitcoinRateFailureTest {

    @Test
    public void verifyStatusCode404() {
        Response response = RestAssured.get("https://api.coindesk.com/v1/bpi/nonexistentendpoint.json");
        Assert.assertEquals(response.getStatusCode(), 404, "Expected status code 404");
    }
}
