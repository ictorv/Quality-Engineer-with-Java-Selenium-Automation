
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LinkTexts {
	public static WebDriver driver;
	String baseUrl = "https://webapps.tekstac.com/SeleniumApp2/CallTaxiService/Home.html";

	public LinkTexts(WebDriver driver) {
		this.driver = driver;
	}

	public LinkTexts() {
	}

	public WebDriver setupDriver() { // Do not change the method signature

		//Create driver,store it in static variable driver, navigate to baseUrl and return it
		driver=DriverSetup.getDriver();
		driver.get(baseUrl);
		return driver;

	}

	public WebElement clickServicesLink()  {
		
        //Locate the WebElement "Services" using partialLinkText Locator and click it.
        //Locate the WebElement "Type of Cab Services" and return it as a WebElement.
		driver.findElement(By.partialLinkText("Services")).click();
		WebElement cabType=driver.findElement(By.xpath("//table[@id='cabType']"));
		return cabType;
	}
	
	public WebElement clickBookingLink() {
		
		//Locate the WebElement "Booking" using linkText Locator and click it.
        //Locate the WebElement "Booking Details" and return it as a WebElement.
	    driver.findElement(By.linkText("Booking")).click();
		WebElement book=driver.findElement(By.xpath("//td[@id='bdetails']"));
		return book;
	}

	public static void main(String[] args)  {
		LinkTexts reg = new LinkTexts();
		reg.setupDriver();
		reg.clickServicesLink();
		reg.clickBookingLink();

	}

}
