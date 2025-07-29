package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FreeListingPhoneNumberPage extends BasePage{

	public FreeListingPhoneNumberPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="(//input[starts-with(@class, 'entermobilenumber_input__eCrdc')])[1]")
	WebElement phoneNumberTab;
	
	@FindBy(className="businesslistfree_whitearrow__RLoYg")
	WebElement startNowButton;
	
	@FindBy(xpath="//span[contains(@class, 'entermobilenumber_error__text__uPM09')]")
	WebElement errorMessage;
	//actions
	public void setPhoneNumber(String number) {
		phoneNumberTab.sendKeys(number);
	}
	
	public void clickStartNowButton() {
		startNowButton.click();
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}
	
}
