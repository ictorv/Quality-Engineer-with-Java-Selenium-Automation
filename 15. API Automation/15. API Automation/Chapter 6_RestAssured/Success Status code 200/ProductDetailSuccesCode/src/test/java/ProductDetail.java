import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductDetail {

    @Test
    public void verifyAddProductStatusCode() {
        String jsonBody = """
        {
            "name": "Apple MacBook Pro 16",
            "data": {
                "year": 2019,
                "price": 1849.99,
                "CPU model": "Intel Core i9",
                "Hard disk size": "1 TB"
            }
        }
        """;

        Response response = RestAssured
            .given()
            .header("Content-Type", "application/json")
            .body(jsonBody)
            .post("https://api.restful-api.dev/objects");

        // Assert status code
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
    }
}

