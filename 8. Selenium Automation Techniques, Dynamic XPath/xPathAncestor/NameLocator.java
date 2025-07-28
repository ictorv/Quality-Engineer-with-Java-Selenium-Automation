import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NameLocator {     //DO NOT Change the class name

	//Use the declared variables for stroing required values
	static String fName;
	static WebDriver driver;
	String baseUrl = "https://webapps.tekstac.com/RentaCar/";

	public WebDriver createDriver() {  //DO NOT Change the method Signature

		// Invoke the getDriver() method from the DriverSetup File 
		// Store it in static variable 'driver', navigate and return it
		driver=DriverSetup.getDriver();
		driver.get(baseUrl);
		return driver;
	}

	public void setFormValues() {


		// Locate the WebElement corresponding to 'Name' using specific locators.                                                    
		// Using sendKeys() method, fill WebElement 'Name' with 'name'
		driver.findElement(By.id("name")).sendKeys("George");

		// Locate the WebElement corresponding to 'Contact Number' using specific locators.                                                    
		// Using sendKeys() method, fill WebElement 'Contact Number' with 'number'
		driver.findElement(By.id("number")).sendKeys("9898745342");

		// Locate the WebElement corresponding to 'Address' using specific locators.                                                    
		// Using sendKeys() method, fill WebElement 'Address' with 'address'
	    driver.findElement(By.id("address")).sendKeys("Mexico");	
    
		// Locate the WebElement corresponding to 'Travel Date' using specific locators.                                                    
		// Using JavaScriptExecutor method, select the date picker of the 'Travel Date' with 'travelDate'
		WebElement date=driver.findElement(By.id("traveldate"));
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='19-09-2023';",date);

		// Locate the WebElement corresponding to 'CarType' using specific locators.
		// Using selectByVisibleText method, select the drop down box of the 'Course Mode' with 'courseMode'
		Select carType=new Select(driver.findElement(By.id("cartype")));
		carType.selectByVisibleText("Minivan");

		// Locate the WebElement corresponding to 'Duration' using specific locators.
		// Using sendKeys() method, fill WebElement 'Duration' with 'duration'
		driver.findElement(By.id("duration")).sendKeys("5");
		

		// Locate the WebElement corresponding to 'Driving License Number' using specific locators.
		// Using sendKeys() method, fill WebElement 'Driving License Number' with 'drivingLicense'
		driver.findElement(By.id("license")).sendKeys("97856434365878");	

		// Locate the WebElement corresponding to 'Payment Mode' using specific locators.
		// Using click method, select the radio button of the 'Payment Mode' with 'paymentMode'
        driver.findElement(By.id("cod")).click();
		

		// Locate the WebElement corresponding to 'Need Driver' using specific locators.
		// Using click() method, fill WebElement 'Need Driver' with 'needDriver'
		WebElement check =driver.findElement(By.id("driver"));
		
// 		{
    		if(!check.isSelected()){
                check.click();
    		}
// 		}
        driver.findElement(By.id("submit")).click();
		//Click on the Confirm button
		
	}

	public String getNameLocator() {  //DO NOT Change the method Signature

		/*Using the driver, Find the element ancestor and its text and assign the text to 'fName' */
		fName=driver.findElement(By.xpath("//div[@id='result']//ancestor::div")).getText();
		return fName;
	}

	public static void main(String[] args) {    //DO NOT Change the method Signature

		NameLocator namLocator=new NameLocator();
		//Add required code here
		driver = namLocator.createDriver();
		namLocator.setFormValues();
		System.out.println(namLocator.getNameLocator());
	}

}

