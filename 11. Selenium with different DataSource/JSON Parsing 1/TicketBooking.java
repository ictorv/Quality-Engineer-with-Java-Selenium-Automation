import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TicketBooking {
	public static WebDriver driver;
	String baseUrl = "https://webapps.tekstac.com/CricTickets/Index.html";
	
	public WebDriver createDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE
	    
	    // Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    driver=DriverSetup.getDriver();
	    driver.get(baseUrl);
	    return driver;
	}
	
	public String bookTicket() throws Exception {
		//Read the values fron the JSON file and set it to the webpage.
	    //Submit the form by clicking the Book button
	    // Return the result displayed after clicking the button
		JSONParser parser =new JSONParser();
		Object obj=parser.parse(new FileReader("CricTickets.json"));
		JSONObject jsonObject=(JSONObject)obj;
		JSONArray bookingDetails=(JSONArray)jsonObject.get("BookingDetails");
		JSONObject booking=(JSONObject) bookingDetails.get(0);
		
		String id=(String)booking.get("id");
		String contactNumber=(String)booking.get("ContactNumber");
		String emailId=(String)booking.get("EmailId");
		String city=(String)booking.get("City");
		String matchDate=(String)booking.get("MatchDate");
		String matchTime=(String)booking.get("MatchTime");
		String number=(String)booking.get("NumberOfTickets");
		String matchName=(String)booking.get("MatchName");
		String snacks=(String)booking.get("Snacks");
		
		driver.findElement(By.id("contactNumber")).sendKeys(contactNumber);
		driver.findElement(By.id("email")).sendKeys(emailId);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.id("date")).sendKeys(matchDate);
		
		List<WebElement> radioOptions=driver.findElements(By.xpath("//*[@type='radio']"));
		for(WebElement ele: radioOptions){
		    if(ele.getAttribute("value").equals(matchTime)){
		        ele.click();
		        break;
		    }
		}
		driver.findElement(By.id("ticket_count")).sendKeys(number);
		
		WebElement match_list=driver.findElement(By.id("match_list"));
		
		Select select=new Select(match_list);
		select.selectByValue(matchName);
		
		List<WebElement> snackOptions=driver.findElements(By.xpath("//*[@type='checkbox']"));
        for(WebElement ele: snackOptions){
		    if(ele.getAttribute("value").equals(snacks)){
		        ele.click();
		        break;
		    }
		}
		
		driver.findElement(By.id("submit")).click();
		Thread.sleep(5000);
		WebElement result=driver.findElement(By.id("result1"));
		return result.getText();	
	}
	
	public static void main(String[] args) throws Exception {
		
		TicketBooking res=new TicketBooking();
		res.createDriver();
		System.out.println(res.bookTicket());
	   
		}

}
