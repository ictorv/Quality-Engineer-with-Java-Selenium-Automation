import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

public class Registration {   //DO NOT Change the class Name

	// Use the below delcared variable
	public static WebDriver driver;

	String baseUrl = "https://webapps.tekstac.com/RentaCar/";

	public WebDriver createDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE

		// Invoke the getDriver() method from the DriverSetup File 
		// Store it in static variable 'driver', navigate and return it
		driver=DriverSetup.getDriver();
		driver.get(baseUrl);
		return driver;
	}

	public void setFormValues(String needDriver) {


    	// Set the 'Name' as "George". 
        driver.findElement(By.id("name")).sendKeys("George");
        // Set the 'Contact Number' as "9898745342".         
        driver.findElement(By.id("number")).sendKeys("9898745342");
    
        // Set the 'Address' as "Mexico".                
        driver.findElement(By.id("address")).sendKeys("Mexico");
    
        // Set the 'Travel Date' as "19-09-2023".                                                     
        WebElement dtravel=driver.findElement(By.id("traveldate"));
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].value='19-09-2023';",dtravel);
        // Using JavaScriptExecutor method, select the date picker of the 'Travel Date' with 'travelDate' 
    
        // Set the 'CarType' as "Minivan" 
        Select CarType=new Select(driver.findElement(By.id("cartype")));
        CarType.selectByVisibleText("Minivan");
    
        // Set the 'Duration' as "5". 
        driver.findElement(By.id("duration")).sendKeys("5");
    
        // Set the 'Driving License Number' as "97856434365878". 
        driver.findElement(By.id("license")).sendKeys("97856434365878");
        
        driver.findElement(By.id("cod")).click();
        // Set the 'Payment Mode' as "Cash on Delivery". 
    
        //			Locate the WebElement corresponding to 'Need Driver' using specific locators.
    	//			Using click() method, fill WebElement 'Need Driver' with 'needDriver'
    	WebElement driv=driver.findElement(By.id("driver"));
    	if(needDriver.equalsIgnoreCase("yes")){
        	if(!driv.isSelected()){
                driv.click();
        	}
    	}
        else{
        	if(driv.isSelected()){
                driv.click();
        	}
        }
		driver.findElement(By.id("submit")).click();
	}

	public String getResult(){
		// Return the text value from the form 
		WebElement result=driver.findElement(By.id("result"));
		
		return result.getText();
	}


	public static void main(String[] args) {    // DO NOT CHANGE THE METHOD SIGNATURE

		Registration reg=new Registration();
		reg.createDriver();
		reg.setFormValues("");

		System.out.println(reg.getResult());

	}
}

