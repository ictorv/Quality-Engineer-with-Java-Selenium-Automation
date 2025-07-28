
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

public class Xpaths {
	public static WebDriver driver;
	String baseUrl = "https://webapps.tekstac.com/RentaCar/";

	public Xpaths(WebDriver driver) {
		this.driver = driver;
	}

	public Xpaths() {
	}

	public WebDriver setupDriver() { // Do not change the method signature
        driver=DriverSetup.getDriver();
        driver.get(baseUrl);
		//Create driver,store it in static variable driver, navigate to baseUrl and return it
        return driver;
	}

	public void setName(String name) {
	// Locate the WebElement corresponding to 'Name' using XPath Contains() function. 
    
    // Using sendKeys method, fill the WebElement  'Name' with 'name'
	
	    driver.findElement( By.xpath("//input[contains(@id,'name')]")).sendKeys(name);
	}

	public void setContactNumber(String phone) {
	// Locate the WebElement corresponding to 'Contact Number' using Xpath Using AND Function. 
    
    // Using sendKeys method, fill the WebElement  'Contact Number' with 'phone'
        driver.findElement(By.xpath("//input[@id='number' and @type='tel']")).sendKeys(phone);
	}

	public void setAddress(String address) {
	// Locate the WebElement corresponding to 'Address' using Xpath Using Index Function. 
    
    // Using sendKeys method, fill the WebElement  'Address' with 'address'
	    driver.findElement(By.xpath("/html/body/div[2]/form/table/tbody/tr[3]/td[2]/textarea")).sendKeys(address);
	}

	public void setTravelDate(String traveldate) {
	// Locate the WebElement corresponding to 'Travel Date' using Xpath Using OR Function. 
        driver.findElement(By.xpath("//input[@id='traveldate' or @type='date']")).sendKeys(traveldate);
    // Using sendKeys method, fill the WebElement  'Travel Date' with 'traveldate'
	}

	public void setCarType(String cartype) {
	// Locate the WebElement corresponding to 'Car Type' using Xpath Using Following-sibling-Child  Function.
        Select type=new Select(driver.findElement(By.xpath("//td[text()='Car Type']/following-sibling::td/select")));
        type.selectByVisibleText(cartype);
    // Using selectByVisibleText, select the drop down box of the 'Car Type' with 'cartype'
	}

	public void setDuration(String duration) {
	// Locate the WebElement corresponding to 'Duration' using XPath Starts-with() function. 
        driver.findElement(By.xpath("//input[starts-with(@id,'duration')]")).sendKeys(duration);
    // Using sendKeys method, fill the WebElement  'Duration' with 'duration'
	}

	public void setLicenseNumber(String license) {
	// Locate the WebElement corresponding to 'License Number' using XPath using Preceding Function. 
        driver.findElement(By.xpath("//*[text()='Payment Mode']/preceding::input[1]")).sendKeys(license);
    // Using sendKeys method, fill the WebElement  'License Number' with 'license'
	}

	public void setPaymentMode(String payment) {
	// Locate the WebElement corresponding to 'Payment Mode' using XPath using Following Attribute XPath or Following Element with Attribute XPath.  
        driver.findElement(By.xpath("//td[text()='Payment Mode']/following::input[@value='"+payment+"']")).click();
    // Using click method, select the 'Payment Mode' based on 'payment'
	}

	public void setConfirm() {
	// Locate the WebElement corresponding to 'Confirm' using the XPath Text() function.
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();
    // Using click method, click on the WebElement 'Confirm' with 'submit'
	}

	public WebElement getMessage() {
	    // Retrieve the text from the WebElement
	    // Get the text content of the WebElement
	    // Return the WebElement containing the text
		return driver.findElement(By.xpath("//*[@id='result']"));
	}

	public static void main(String[] args) {
		Xpaths reg = new Xpaths();
		reg.setupDriver();
		reg.setName("Peter");
		reg.setContactNumber("9876543234");
		reg.setAddress("Texas");
		reg.setTravelDate("2024-02-02");
		reg.setCarType("SUV");
		reg.setDuration("5");
		reg.setLicenseNumber("3224");
		reg.setPaymentMode("Net Banking");
		reg.setConfirm();
		reg.getMessage();

	}
}
