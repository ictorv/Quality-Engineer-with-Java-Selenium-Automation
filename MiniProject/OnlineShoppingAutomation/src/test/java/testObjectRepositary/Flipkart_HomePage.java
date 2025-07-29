package testObjectRepositary;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import userDefinedLibraries.BrowserUtil;

public class Flipkart_HomePage {
	
	WebDriver driver;
	BrowserUtil objBrowserUtil;
	
	//Constructor with PageFactory
	public Flipkart_HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	@FindBy(xpath = "//input[starts-with(@title,'Search')]")
	WebElement text_searchBox;
	
	//Action Methods
	public void setAndClickSearchBox(String search_input) {
		
		try {
			text_searchBox.sendKeys(search_input+Keys.ENTER);
			}
		catch (Exception e) {
				 objBrowserUtil = new BrowserUtil();
		         objBrowserUtil.refresh_Page(driver);
		         
		         text_searchBox.sendKeys(search_input+Keys.ENTER);
			}
	}
	

	
}
