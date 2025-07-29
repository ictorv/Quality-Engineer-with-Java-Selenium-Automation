package com.urban.pageObject;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
 
public class TrackDetails extends BasePage {
	
	public TrackDetails(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = ("//input[@placeholder = 'Order Number']"))
	WebElement orderId;
	
	@FindBy(xpath = ("//input[@placeholder = 'Phone Number']"))
	WebElement phoneNo;
	
	@FindBy(xpath = ("//span[normalize-space()='Submit']"))
	WebElement submitBtn;
	
	@FindBy(xpath = ("//div[@id='app-container']//main//p[contains(text(),'not found')]"))
	WebElement msg;
	
	public void orderInput(String orderID) {
//		WaitUtils.waitForVisibility(driver,orderId , 10);
		orderId.sendKeys(orderID);
	}
	
	public void phoneNoInput(String phoneNO) {
//		WaitUtils.waitForVisibility(driver, phoneNo, 10);
		phoneNo.sendKeys(phoneNO);
	}
	
	public void Click() {
//		WaitUtils.waitForClickability(driver, submitBtn, 10);
		submitBtn.click();
		//WaitUtils.waitForVisibility(driver, msg, 10);
	}
	
	public String message() throws InterruptedException {
//		wait.until(ExpectedConditions.visibilityOf(orderId));
//		Thread.sleep(3000);
		return msg.getText();
	}
	
}



//p[@class='_33bg3']