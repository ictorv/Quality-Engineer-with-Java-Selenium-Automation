package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*Represents the landing page of the application.
 * Contains actions related to searching for books using the search bar.*/
public class HomePage extends BasePage{
	
	// Constructor that initializes the web elements on the HomePage
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this); // Re-initialization for local elements
	}
	
	// Web element for the search input field
	@FindBy(id="inputbar")
	WebElement searchBar;
	
	// Web element for the search button
	@FindBy(id="btnTopSearch")
	WebElement searchBtn;
	
	// Enters the search term in the search bar
	public void setSearchBar(String val) {
		searchBar.sendKeys(val);
	}
	
	// Clicks the search button to trigger the search
	public void setSearchBtn() {
		searchBtn.click();
	}
	
}
