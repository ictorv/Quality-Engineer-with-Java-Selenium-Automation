package chapter4;


import com.opencsv.CSVReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.FileReader;

import org.testng.annotations.Test;

public class DataParameterizedCSVPostman {
	
	@Test
    public void dataparameterizedCSV()  {
        String csvFilePath = "src/test/resources/data.csv";
        String apiUrl = "https://jsonplaceholder.typicode.com/posts";
        RestAssured.useRelaxedHTTPSValidation();
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            reader.readNext(); // Skip header

            while ((nextLine = reader.readNext()) != null) {
                String title = nextLine[0];
                String body = nextLine[1];
                String userId = nextLine[2];

                Response response = RestAssured.given()
                        .header("Content-type", "application/json")
                        .body(String.format("{\"title\":\"%s\", \"body\":\"%s\", \"userId\": %s}", title, body, userId))
                        .post(apiUrl);

                System.out.println("Response: " + response.getBody().asString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
