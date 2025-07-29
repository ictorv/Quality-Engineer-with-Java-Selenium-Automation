package test.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactSales {

    WebDriver driver;
	public ContactSales(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//*[@id='navbarSupportedContent']/div[2]/ul/li[2]/a/button")
	WebElement contact_sales;
	public void clickContactSales() {
		contact_sales.click();
		
	}
}
