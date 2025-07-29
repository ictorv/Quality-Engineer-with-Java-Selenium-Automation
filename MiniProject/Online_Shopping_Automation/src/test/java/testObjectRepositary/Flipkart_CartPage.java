package testObjectRepositary;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import userDefinedLibraries.BrowserUtil;

public class Flipkart_CartPage {
	
	WebDriver driver;
	BrowserUtil objBrowserUtil=new BrowserUtil();
	
	//constructor
	public Flipkart_CartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	@FindBy(xpath = "//div[@class='k9WPjB' and text()='Price (2 items)']/following::span[@class='b5rp0W']")
	WebElement price;
	
	@FindBy(xpath = "//div[@class='k9WPjB' and text()='Discount']/following::span[@class='b5rp0W']")
	WebElement discount;
	

	
	@FindBy(xpath = "//div[@class='k9WPjB' and contains(text(),'Coupons')]/following::span[@class='b5rp0W']")
	WebElement coupons;
	
	@FindBy(xpath = "//div[@class='k9WPjB' and contains(text(),'Delivery Charges')]/following::span[@class='b5rp0W']")
	WebElement deliveryCharge;
	
	@FindBy(xpath = "//span[@class='LAlF6k re6bBo']")
	List<WebElement> itemsTotaList;
	
	
	@FindBy(xpath="//div[@class='k9WPjB' and contains(text(),'Protect Promise Fee')]/following::span[@class='b5rp0W']")
	WebElement protect_fee;
	
	@FindBy(xpath="//div[@class='k9WPjB' and contains(text(),'Handling Fee')]/following::span[@class='b5rp0W']")
	WebElement handling_fee;
	
	
	@FindBy(xpath="//div[@class='PWd9A7 xvz6eC']/div[@class='_1Y9Lgu']/span")
	WebElement total_Amount;
	
	@FindBy(xpath="//div[@class='k9WPjB' and contains(text(),'Buy more & save more')]/following::span[@class='b5rp0W']")
	WebElement buy_More_Save_More;
	
	
	//ACtion methods
	public String getPrice() {
		return price.getText();
	}
	
	public String getDiscount() {
		return discount.getText();
	}

	public String getCoupons() {
		try {
			return coupons.getText();
		} catch (Exception e) {
			return "0";
		}
		
	}
	
	public String getDeliveryCharges() {
		try {
			return deliveryCharge.getText();
		} catch (Exception e) {
			return "0";
		}
					
	}
	
	public String getHandlingCharges() {
		try {
			return handling_fee.getText();
		} catch (Exception e) {
			return "0";
		}
					
	}
	
	public String getBuyMoreSaveMore() {
		try {
			return buy_More_Save_More.getText();
		} catch (Exception e) {
			return "0";
		}
					
	}
	
	
	public String getTotalAmount() {
		
		try {
			return total_Amount.getText();
			}catch (Exception e) {
				objBrowserUtil.refresh_Page(driver);
		         return total_Amount.getText();
			}
	}
	
	public String getProtectFee() {
		
		try {
			return protect_fee.getText();
		} catch (Exception e) {
			return "0";
		}
	}
	
}

