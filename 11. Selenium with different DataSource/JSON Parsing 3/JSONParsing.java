import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class JSONParsing {
    JSONArray order;
    public static WebDriver driver;
    Object obj;
    JSONObject jsonObject;
    static String baseUrl = "https://webapps.tekstac.com/CustomerRegistration/";

    public static WebDriver createDriver() {
        //Implement code to create Driver from DriverSetup and set to 'static' driver variable. And navigate to the baseUrl.
        //Return the driver.
		driver=DriverSetup.getDriver();
		driver.get(baseUrl);
		
		return driver;
    }

    public JSONArray readFile(String fileName) {
        //Implement code to read the file and return Customer information as JSON array from the CustomerDetails.json file
        //Use the variable 'fileName' to create the file instance
        
		JSONParser parser=new JSONParser();
		try{
		    jsonObject=(JSONObject)parser.parse(new FileReader(fileName));
		    order=(JSONArray)jsonObject.get("Customer");
		}catch(Exception e){
		    e.printStackTrace();
		}
		return order;
    }

    public String getCustomerName(int index) {
        //Implement code to return Customer Name from Customer in the json file
        JSONObject o=(JSONObject) order.get(index);
        return (String)o.get("Name");
    }

    public int getAge(int index) {
        //Implement code to return Customer Age from Customer in the json file
        JSONObject o=(JSONObject) order.get(index);
        int a=Integer.parseInt((String)o.get("Age"));
        return a;
        
    }

    public String getAddress(int index) {
        //Implement code to return Customer address from Customer in the json file
        JSONObject o=(JSONObject) order.get(index);
        return (String)o.get("Address");
    }

    public String getPhoneNumber(int index) {
        //Implement code to return Customer Phone number from Customer in the json file
        JSONObject o=(JSONObject) order.get(index);
        return (String)o.get("PhoneNumber");
    }

    public String getEmail(int index) {
        //Implement code to return Customer email from Customer in the json file
        JSONObject o=(JSONObject) order.get(index);
        return (String)o.get("Email");
    }

    public String submitForm() {

        // Implement code to submit form with values got from json.
		// Call the get methods to retrieve the Json values
		// Submit the form
	    // Locate the result displayed after form submit and return the same
	    driver.findElement(By.name("cname")).sendKeys(getCustomerName(0));
	    driver.findElement(By.name("age")).sendKeys(String.valueOf(getAge(0)));
	    driver.findElement(By.name("address")).sendKeys(getAddress(0));
	    driver.findElement(By.name("phonenumber")).sendKeys(getPhoneNumber(0));
	    driver.findElement(By.name("email")).sendKeys(getEmail(0));
        driver.findElement(By.id("submit")).click();    
        
        WebElement result=driver.findElement(By.tagName("h2"));
        return result.getText();
    }

    public static void main(String[] args) {
        JSONParsing pagLocator = new JSONParsing();
        //Call the required methods from the main
	    //Pass the file name as "CustomerDetails.json"
	    //Pass the index value as '0'
	    pagLocator.createDriver();
	    pagLocator.readFile("CustomerDetails.json");
	    pagLocator.submitForm();
    }
}
