import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;

public class AlertTest {   //DO NOT Change the class Name

    // Use the below declared variables 
	public static WebDriver driver;
	String baseUrl = "https://webapps.tekstac.com/HotelApplication/booking.html";
	
	public WebDriver createDriver() { //DO NOT change the method signature
	
	    // Invoke the getDriver() method from the DriverSetup class 
	    // Store it in static variable 'driver', navigate to the baseUrl and return it
	    driver=DriverSetup.getDriver();
	    driver.get(baseUrl);
        return driver;
	}
	
	public Alert getAlertElement(WebDriver driver) {   //DO NOT change the method signature
	
	    //Locate the First Name and pass a valid name to it
	    driver.findElement(By.id("fName")).sendKeys("Peter");
	    //Locate the Last Name and pass a valid name to it
	    driver.findElement(By.id("lName")).sendKeys("Keran");
	    //Locate the Confirm Booking button and click it
	    driver.findElement(By.id("submit")).click();
	    
	    //Locate the 'Alert' element and return it
	    Alert myAlert=driver.switchTo().alert();
	   // myAlert.manage().accept();
    	return myAlert ;
	}   

	public static void main(String[] args) {  //DO NOT change the method signature
	      
	    AlertTest at=new AlertTest();
	    //Implement the code here
	    driver=at.createDriver();
	    Alert alert=at.getAlertElement(driver);
        
	}	 	  	      		      	   	     	     	 	

}

