package testObjectRepositary;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import userDefinedLibraries.BrowserUtil;

public class Flipkart_ProductDetailsPage {

	WebDriver driver;
	BrowserUtil objBrowserUtil=new BrowserUtil();

	//constructor
	public Flipkart_ProductDetailsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators  /button[@class='QqFHMw vslbG+ In9uk2']
	//@FindBy(xpath="//div[@class='_1ri+WN lwANdH']/ul/li[1]/button[@class='QqFHMw vslbG+ In9uk2567']")
	//@FindBy(xpath="//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button")
	@FindBy(xpath="//button[contains(@class, 'QqFHMw') and text()='Add to cart']")
	WebElement addToCartBtn;
	
	//action methods
	public void scrollTo_addToCartBtn() {
		
		//switching the driver from main page to product page
		
		objBrowserUtil.switchToProductPage(driver);
		
		//to reload or refresh
		driver.get(driver.getCurrentUrl());
		
		//scroll to addToCart button
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			js.executeScript("arguments[0].scrollIntoView(false);", addToCartBtn);
		}
		catch (Exception e) {
	        objBrowserUtil.refresh_Page(driver);
	        try {
	        	js.executeScript("arguments[0].scrollIntoView(false);", addToCartBtn);
	        }
	        catch(Exception e1) {
	        	System.out.println("[Info]No Add to Cart button found...");
	        }
		}
	}
	
	public void click_addToCartbtn() {
		Actions actions=new Actions(driver);
		try {
			actions.moveToElement(addToCartBtn).click().build().perform();
		}
		catch (Exception e) {
			objBrowserUtil.refresh_Page(driver);    
			actions.moveToElement(addToCartBtn).click().build().perform();
		}	
	}
}
