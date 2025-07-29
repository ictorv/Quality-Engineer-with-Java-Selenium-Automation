
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class InlineSchemaValidation {
    public static void main(String[] args) {
        // Base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Inline XSD Schema Definition
        String xsdSchema = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "  <xs:element name=\"booking\">\n" +
                "    <xs:complexType>\n" +
                "      <xs:sequence>\n" +
                "        <xs:element name=\"firstname\" type=\"xs:string\"/>\n" +
                "        <xs:element name=\"lastname\" type=\"xs:string\"/>\n" +
                "        <xs:element name=\"totalprice\" type=\"xs:int\"/>\n" +
                "        <xs:element name=\"depositpaid\" type=\"xs:boolean\"/>\n" +
                "        <xs:element name=\"bookingdates\">\n" +
                "          <xs:complexType>\n" +
                "            <xs:sequence>\n" +
                "              <xs:element name=\"checkin\" type=\"xs:string\"/>\n" +
                "              <xs:element name=\"checkout\" type=\"xs:string\"/>\n" +
                "            </xs:sequence>\n" +
                "          </xs:complexType>\n" +
                "        </xs:element>\n" +
                "        <xs:element name=\"additionalneeds\" type=\"xs:string\"/>\n" +
                "      </xs:sequence>\n" +
                "    </xs:complexType>\n" +
                "  </xs:element>\n" +
                "</xs:schema>";

        // Send GET request and validate response against inline XSD
        Response response = given()
            .contentType("application/xml")
            .accept("application/xml")
        .when()
            .get("/booking/119")
        .then()
            .assertThat()
            .body(matchesXsd(xsdSchema))
            .extract().response();

        // Print the response
        System.out.println("Response:");
        System.out.println(response.asString());
    }
}
