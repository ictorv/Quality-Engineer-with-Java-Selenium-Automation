package Test;



import io.restassured.RestAssured;
import io.restassured.config.SSLConfig; // Required import for SSLConfig
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class csvinpostman {

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";
    private static final String ENDPOINT = "/posts";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        // --- !!! CAUTION !!! ---
        // This line disables SSL certificate validation.
        // Use ONLY for development/testing when you're facing SSLHandshakeException
        // and cannot import the certificate into your truststore.
        // DO NOT USE IN PRODUCTION ENVIRONMENTS.
        RestAssured.useRelaxedHTTPSValidation();
        // Or more explicitly:
        // RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation());
        // --- !!! CAUTION !!! ---
    }

    /**
     * DataProvider to read test data from a CSV file.
     * Each row in the CSV will provide data for one test iteration.
     * The method parses the CSV and creates a Map for the request body.
     * @return Iterator of Object arrays, where each array contains a Map representing the POST body.
     * @throws IOException if the CSV file cannot be read.
     */
    @DataProvider(name = "postData")
    public Iterator<Object[]> getPostDataFromCsv() throws IOException {
        List<Object[]> testData = new ArrayList<>();
        // Define the path to your CSV file. It should be in src/test/resources/
        String csvFilePath = "data.csv";

        try (Reader reader = new FileReader(csvFilePath);
             // Configure CSV parser: DEFAULT format, first record as header, ignore header case, trim spaces
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord csvRecord : csvParser) {
                // Extract values by header name from the CSV record
                String title = csvRecord.get("title");
                String body = csvRecord.get("body");
                String userIdStr = csvRecord.get("userId");

                // Create a LinkedHashMap to maintain order (optional but good practice for JSON)
                Map<String, Object> postBody = new LinkedHashMap<>();
                postBody.put("title", title);
                postBody.put("body", body);
                // Convert userId to Integer as it's typically expected as a number in JSON
                postBody.put("userId", Integer.parseInt(userIdStr));

                // Add the map as an element in an Object array for the DataProvider
                testData.add(new Object[]{postBody});
            }
        }
        return testData.iterator();
    }

    /**
     * Test method to create a POST request using data provided by the DataProvider.
     * @param postData A Map containing the title, body, and userId for the POST request.
     */
    @Test(dataProvider = "postData")
    public void createPostWithCsvData(Map<String, Object> postData) {
        System.out.println("Sending POST request with data: " + postData);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON) // Set request content type to JSON
                .body(postData) // Pass the Map as the request body; Rest Assured serializes it to JSON
                .when()
                .post(ENDPOINT) // Perform the POST request to the specified endpoint
                .then()
                .log().all() // Log the entire request and response for debugging
                .statusCode(201) // Assert that the response status code is 201 (Created)
                // Assert that the response body contains the data sent in the request
                .body("title", equalTo(postData.get("title")))
                .body("body", equalTo(postData.get("body")))
                .body("userId", equalTo(postData.get("userId")))
                .body("id", notNullValue()) // Assert that a new 'id' is generated and is not null
                .extract().response(); // Extract the full response for further checks if needed

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());
    }
}