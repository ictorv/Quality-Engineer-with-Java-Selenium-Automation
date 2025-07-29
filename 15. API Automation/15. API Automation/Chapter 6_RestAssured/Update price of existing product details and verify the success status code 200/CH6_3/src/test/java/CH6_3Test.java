import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.config.SSLConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CH6_3Test {

    @Test
    public void testUpdateProduct() {
        // Create the product
        String createRequestBody = "{\n" +
                "    \"name\": \"Apple MacBook Pro 16\",\n" +
                "    \"data\": {\n" +
                "       \"year\": 2019,\n" +
                "       \"price\": 1849.99,\n" +
                "       \"CPU model\": \"Intel Core i9\",\n" +
                "       \"Hard disk size\": \"1 TB\"\n" +
                "    }\n" +
                " }";

        RestAssured.baseURI = "https://api.restful-api.dev/objects";
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

        String createResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createRequestBody)
                .post()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println("Create Response: " + createResponse);

        // Extract the ID of the created product
        String productId = createResponse.split("\"id\":\"")[1].split("\"")[0];

        // Update the product
        String updateRequestBody = "{\n" +
                "    \"name\": \"Apple MacBook Pro 16\",\n" +
                "    \"data\": {\n" +
                "       \"year\": 2019,\n" +
                "       \"price\": 3000.99,\n" +
                "       \"CPU model\": \"Intel Core i9\",\n" +
                "       \"Hard disk size\": \"1 TB\",\n" +
                "       \"color\": \"silver\"\n" +
                "    }\n" +
                " }";

        int statusCode = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateRequestBody)
                .put("/" + productId)
                .getStatusCode();

        System.out.println("Status Code: " + statusCode);
        Assert.assertEquals(statusCode, 200, "Status code is not 200");

        String updateResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateRequestBody)
                .put("/" + productId)
                .then()
                .extract()
                .response()
                .asString();

        System.out.println("Update Response: " + updateResponse);
        Assert.assertTrue(updateResponse.contains("\"price\":3000.99"), "Response does not contain updated price");
    }
}
