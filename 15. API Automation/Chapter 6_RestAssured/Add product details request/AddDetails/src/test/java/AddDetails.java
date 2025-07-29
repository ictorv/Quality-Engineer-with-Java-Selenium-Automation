
		import io.restassured.RestAssured;
		import io.restassured.response.Response;
		import org.testng.Assert;
		import org.testng.annotations.Test;

		public class AddDetails {

		    @Test
		    public void printSuccessResponse() {
		        String jsonBody = """
		        {
		            "name": "Dell I5",
		            "data": {
		                "year": 2023,
		                "price": 20000,
		                "CPU model": "Intel Core i9",
		                "Hard disk size": "2 TB"
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

		        // Print success response
		        System.out.println("Success Response:");
		        System.out.println(response.getBody().asPrettyString());
		    }
		}


