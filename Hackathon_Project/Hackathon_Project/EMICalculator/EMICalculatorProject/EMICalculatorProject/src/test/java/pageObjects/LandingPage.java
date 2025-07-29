package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage{
	
	public LandingPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[contains(@class,'col-lg-4 control-label')][1]")
	WebElement label;
	
	
	public String getConfirmMsg() {
		return label.getText();
	}
	
	
	
	
}
