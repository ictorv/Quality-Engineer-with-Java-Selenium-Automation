package testObjectRepositary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import userDefinedLibraries.BrowserUtil;

public class Flipkart_AppliancesPage {
	
	WebDriver driver;
	BrowserUtil objBrowserUtil=new BrowserUtil();
	
	//constructor
	public Flipkart_AppliancesPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	@FindBy(xpath = "//div[@class='_75nlfW'][1]/div[1]/div[@class='slAVV4'][1]")
	WebElement first_Product;
	
	@FindBy(xpath="(//div[@class='_75nlfW'][1]/div/div[@class='slAVV4'])[4]")
	WebElement next_Product;
	
	//Action methods
	public void click_First_Product() {
		
		try {
			first_Product.click();
			}catch (Exception e) {
				objBrowserUtil = new BrowserUtil();
		         objBrowserUtil.refresh_Page(driver);
		         first_Product.click();
			}
	}
	
	public void click_Next_Product() {
		
		objBrowserUtil.switchToMainPage(driver);
		
		try {
			next_Product.click();
			}catch (Exception e) {
				objBrowserUtil = new BrowserUtil();
		        objBrowserUtil.refresh_Page(driver);
		         next_Product.click();
			}
		
	}
	
	
}
