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

public class JSONParsing
{
	JSONArray order;
	public static WebDriver driver;
	Object obj;
	public static WebElement totalAmount;
	JSONObject jsonObject;
	static String baseUrl = "https://webapps.tekstac.com/SeleniumApp2/OrderFood/order.html";
	
	public static WebDriver createDriver() //DO NOT change the method signature
	{
	   //Implement code to create Driver from DriverSetup and set to 'static' driver variable. And navigate to the base Url.
	   // Return the driver.
		driver=DriverSetup.getDriver();
		driver.get(baseUrl);
		return driver;
		
// 		return null;
	}
	
	public JSONArray readFile(String fileName)   //DO NOT change the method signature
    {
        //Implement code to read and return the JSON array from the OrderDetails.json file
        //Use the variable 'fileName' to create file instance
        JSONParser parser=new JSONParser();
        try{
            Object obj=parser.parse(new FileReader(fileName));
            JSONObject jsonobject=(JSONObject)obj;
            order=(JSONArray)jsonobject.get("Order");
        }catch(Exception e){
            e.printStackTrace();
        }
		return order;
    }
    
    public String getItemName(int index) {
       	//Implement code to return Item from Order in the json file
       	
        JSONObject o=(JSONObject)order.get(index);
        return (String)o.get("Item");
    }

	
	public String getSpecialItem(int index) { //Implement code to return contactname
	//Implement code to return SpecialItem from Order in the json file
	
        JSONObject o=(JSONObject)order.get(index);
        return (String)o.get("SpecialItem");
	  }

	public String getDelivery(int index) { 
// 	Implement code to return Delivery from Order in the json file
	
        JSONObject o=(JSONObject)order.get(index);
        return (String)o.get("Delivery");
	}	 	  	      		      	   	     	     	 	

	public String getQuantity(int index) { 
			//Implement code to return Quantity from Order in the json file
			
        JSONObject o=(JSONObject)order.get(index);
        return (String)o.get("Quantity");
	}

	public String getArea(int index) { 
		//Implement code to return Area from Order in the json file
		
        JSONObject o=(JSONObject)order.get(index);
        return (String)o.get("Area");
	}


	
	public String submitForm() { // Implement code to submit form with values got
		
		// Implement code to submit form with values got from json.
		// Call the get methods to retrieve the Json values
		// For ex - driver.findElement(By.id("food_quantity")).sendKeys(getQuantity(0));
		
		// Submit the form
		
	    // Locate the WebElement corresponding to the Total Cost displayed after form submit
	    // Store it in a static variable 'totalAmount' and return it as String
		
        WebElement foodDrp=driver.findElement(By.id("food_item"));
	    Select foodOptions=new Select(foodDrp);
	    foodOptions.selectByVisibleText(getItemName(0));
	    
        WebElement specialDrp=driver.findElement(By.id("special_item"));
	    Select specialOptions=new Select(specialDrp);
	    specialOptions.selectByVisibleText(getSpecialItem(0));
	    
	    driver.findElement(By.id("food_quantity")).sendKeys(getQuantity(0));
	    
	    WebElement deliveryDrp=driver.findElement(By.id("delivery_type"));
	    Select deliveryOptions=new Select(deliveryDrp);
	    deliveryOptions.selectByVisibleText(getDelivery(0));
	    
	    
        WebElement areaDrp=driver.findElement(By.id("delivery_area"));
	    Select areaOptions=new Select(areaDrp);
	    areaOptions.selectByVisibleText(getArea(0));
	    
	    driver.findElement(By.id("submitBtn")).click();
	    
	    WebElement result=driver.findElement(By.xpath("//*[@id='ttab']/tbody/tr[6]/td[2]"));
	    return result.getText();
	}	 	  	      		      	   	     	     	 	
	 
	public static void main(String[] args) {

	    //Call the required methods from the main
	    //Pass the file name as "OrderDetails.json"
	    //Pass the index value as '0'
	    JSONParsing pageLocator=new JSONParsing();
	    pageLocator.createDriver();
	    pageLocator.readFile("OrderDetails.json");
	    String result=pageLocator.submitForm();
		
	}

}