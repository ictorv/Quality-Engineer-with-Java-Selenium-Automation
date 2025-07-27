//DO NOT CHANGE THE GIVEN TEMPLATE CODE.
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxBinary;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.After;
import org.junit.*;
public class StepDefinition {
	WebDriver driver;
	String verifyTitle = null;
	String verifyName = null;
	//DO NOT CHANGE GIVEN SET OF CODE TO CREATE 'DRIVER'
	@Given("^Start firefox browser and open the application$")
	public void SetUp() {
	    driver=DriverSetup.getDriver();
        driver.get("http://webapps.tekstac.com/CustomerRegistrationCucumber/");
	}
	//Do not change Given set of Code Templates
	@When("^Text \"(.*)\" is present in h1 tag$")
	    public void checkTitle(String Title){
	        //Fill in the required codes
			WebElement heading = driver.findElement(By.tagName("h1"));
			verifyTitle = heading.getText();
		    Assert.assertEquals(Title, verifyTitle);
	    }
 
	//Do not change Given set of Code Templates		
	@When("^customer details are \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
		public void enterCustomerDetail(String Name,String Gender,String Address,String Email,String Mobile_Number,String Location,String Course){		
			//Fill in the required codes
			driver.findElement(By.id("customername")).sendKeys(Name);
			driver.findElement(By.xpath("//input[@type='radio' and @value='" + Gender + "']")).click();
			driver.findElement(By.id("customeraddress")).sendKeys(Address);
			driver.findElement(By.id("email")).sendKeys(Email);
			driver.findElement(By.id("mobile")).sendKeys(Mobile_Number);
			Select locationDropdown = new Select(driver.findElement(By.id("customerlocation")));
			locationDropdown.selectByValue(Location);
			Select courseDropdown = new Select(driver.findElement(By.id("course")));
			courseDropdown.selectByValue(Course);
		}
	//Do not change Given set of Code Templates
	@Then("^click register and display \"([^\"]*)\"$")
	public void register(String name) {
		//Fill in the required codes
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//WebElement result = driver.findElement(By.xpath("//div[@id='result']/table/tbody/tr[2]/td[1]"));
		verifyName = driver.findElement(By.xpath("//div[@id='result']/table/tbody/tr[2]/td[1]")).getText();
        Assert.assertEquals(name, verifyName);
	}
	//Do not change Given set of Code Templates
	@After
	public void tearDown(){
		driver.quit();
	}
}
