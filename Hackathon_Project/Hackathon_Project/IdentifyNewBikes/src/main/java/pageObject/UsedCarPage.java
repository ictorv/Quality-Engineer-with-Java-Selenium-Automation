package pageObject;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class UsedCarPage extends BasePage{
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//Constructor
		public UsedCarPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		}
		
	//Locators
	//Location Element
	@FindBy(xpath="//*[@id='popularCityList']/li/a")
	List<WebElement> locationElement;
	
	//PopularModelsCheckBoxList Element
	@FindBy(xpath="//ul[contains(@class,'usedCarMakeModelList')]//li//span//input")
	List<WebElement> popularModelsCheckBoxList;
	
	//To locate the end of scrolling
	@FindBy(id="thatsAllFolks")
	WebElement EndFolks;
	
	//To Locate all Popular Cars
	@FindBy(xpath="//*[@id=\"data-set-body\"]/div/div[1]/div[3]/div[2]/a")
	List<WebElement> usedCarElements;
	
	//Clicking back to homePage
	@FindBy(xpath="//a//img[@title='ZigWheels Home']")
	WebElement homePage;
	
	//Expected Number of Cars
	@FindBy(xpath="//*[@id='mainPage']/div[1]/div/div[1]/span/span")
	WebElement result;
	
	//Actions
	//1)Click location as Chennai.
	public void Location(String location) {
		System.out.println(location);
		for(WebElement loc:locationElement) {
			//System.out.println(loc.getText());
			String option=loc.getText();
			if(option.contains(location)) {
				//wait.until(ExpectedConditions.elementToBeClickable(loc));
	            loc.click();
	            break;
			}
		}
	}
	
	//2)Apply Popular Models CheckBoxList Filter
	public void PopularFilter() {
		//long startTime = System.currentTimeMillis();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		for(WebElement eachElement : popularModelsCheckBoxList ){ 
			js.executeScript("arguments[0].click();",eachElement); 
		}
		while(!EndFolks.isDisplayed()) { 
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		}
		
		//Just to calculate the Scrolling Time
		//long endTime = System.currentTimeMillis();
		//long durationInSeconds = (endTime - startTime) / 1000;
		//System.out.println("Scrolling completed in: " + durationInSeconds + " seconds");
	}
	
	//3)To print all Popular Cars
	public List<WebElement> Used_Cars() {
		return usedCarElements;
	}
	
	//Test whether Expected number of cars is equal to Actual number of cars
	public String loadedCars(){
		//Number of Loaded Classe
		String resNoOfCars=result.getText();
		return resNoOfCars;
	
	}
	
	// 5)Validation
	@SuppressWarnings("deprecation")
	public void usedCarsValidation() throws MalformedURLException {
		String fullURL = driver.getCurrentUrl();
		Assert.assertEquals(fullURL, "https://www.zigwheels.com/used-car");
	}
}
