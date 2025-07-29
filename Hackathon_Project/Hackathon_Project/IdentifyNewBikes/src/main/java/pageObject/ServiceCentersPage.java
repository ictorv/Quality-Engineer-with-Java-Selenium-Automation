package pageObject;
 
import java.time.Duration;
 
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class ServiceCentersPage extends BasePage {
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public ServiceCentersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(id="dealer_city")
	WebElement cityNameElement;
	@FindBy(id="dealer_make")
	WebElement carMakeElement;
	@FindBy(xpath="//*[@id='zwn-search']/div/div/div[1]/div[1]/div/div/div[3]/button")
	WebElement searchButton;
	
	
	//Select City
	public void city(String city) {
		cityNameElement.sendKeys(city ,Keys.TAB);
	}
	
	//Select Car Make
	public void carMake(String carMake) {
		wait.until(ExpectedConditions.elementToBeClickable(carMakeElement)).click();
		Select sel=new Select(carMakeElement);
        sel.selectByVisibleText(carMake);
	}
	
	//Click search
	public void search() {
		searchButton.click();
	}
}
 
 