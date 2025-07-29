package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BusBookingPage extends BasePage{


	public BusBookingPage(WebDriver driver) {
		super(driver);
	}
	//actions
	@FindBy(id="srcName")
	WebElement departureCity;
	
	@FindBy(id="destName")
	WebElement destinationCity;
	
	@FindBy(id="doj")
	WebElement dateOfJourney;
	
	@FindBy(xpath = "//a[@title = 'Next']")
	WebElement nextMonth;
	
	@FindBy(xpath="//table[@class = 'ui-datepicker-calendar']//td/a[text() = '15']")
	WebElement selectDate;
	
	@FindBy(xpath="//input[contains(@class, 'inputbtn')]")
	WebElement searchButton;
	
	
	//actions
	public void setDepartureCity(String departure) {
		departureCity.clear();
		departureCity.sendKeys(departure);
	}
	
	public void setDestinationCity(String destination) {
		destinationCity.sendKeys(destination);
	}
	
	public void clickDateOfJourney() {
		dateOfJourney.click();
	}
	
	public void clickNextMonth() {
		nextMonth.click();
	}
	
	public void selectDate() {
		selectDate.click();
	}
	
	public void clickSearchButton() {
		searchButton.click();
	}
	
}
