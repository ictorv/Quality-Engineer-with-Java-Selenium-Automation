package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	//Constructor
	public LoginPage(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver,this);	
	}
	
	//Locators
	@FindBy(xpath = "//div[contains(text(),'Sign In')]")
	WebElement signIn;
	
	@FindBy(xpath = "//div[normalize-space()='Login']")
	WebElement logInLink;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement emailId;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement password;
	
	@FindBy(xpath = "//div[contains(text(),'Login')]")
	WebElement logInButton;
	
	//Action Methods
	public void clickSignIn() {
		signIn.click();
	}
	
	public void clickLogIn() {
		logInLink.click();
	}
	
	public void setEmail(String EmailInput) {
		emailId.sendKeys(EmailInput);
	}
	
	public void setPassword(String PasswordInput) {
		password.sendKeys(PasswordInput);
	}
	
	public void clickLoginButton() {
		logInButton.click();
	}
}
