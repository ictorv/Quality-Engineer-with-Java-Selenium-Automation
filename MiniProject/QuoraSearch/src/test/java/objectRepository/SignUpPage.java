package objectRepository;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityFiles.ExcelUtil;

public class SignUpPage {
	WebDriver driver;
	
	
	public SignUpPage(WebDriver driver){
		this.driver = driver;
		
		PageFactory.initElements(driver,this);
	}
	
	//Locators signUp, 
	
	@FindBy(xpath = "//div[text()='Sign up with email']") 
	WebElement signUp;
	//button[@class='q-click-wrapper b10gcu9l bobc9nh b1cg7ppz c1nud10e qu-active--bg--darken qu-mt--small qu-borderRadius--pill qu-alignItems--center qu-justifyContent--center qu-whiteSpace--nowrap qu-userSelect--none qu-display--flex qu-tapHighlight--white qu-textAlign--center qu-cursor--pointer qu-hover--bg--darken']
	
	@FindBy(xpath = "//input[@id='profile-name']")
	WebElement name;
	
	@FindBy(xpath = "//div[@class='q-box qu-px--medium qu-pt--small qu-pb--medium']//input[@id='email']")
	WebElement emailID;
	
	@FindBy(xpath = "/html/body/div[2]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div[1]/div/div[1]/div[2]/div[3]/div")
	WebElement errorMessage;
	
	
	public void clickSignUp() {
		signUp.click();
	}
	
	public void checkSingUpWindowVisibility() {
		
	}
	public void setName(String profileName) {
		name.sendKeys(profileName);	}
	
	public void setInvalidEmail(String invalidEmail) {
		emailID.sendKeys(invalidEmail);
	}
	
	/*
	 * public String getErrorMessage() { if(!(errorMessage.getText()).isEmpty())
	 * System.out.println("Verified. Error Message Present!"); return
	 * errorMessage.getText(); }
	 */
	public String getErrorMessage() {
	    String error = errorMessage.getText();
	    String result;
	    String sheet = "dataSheet";
    	String path = "src/test/resources/testData/testData.xlsx";
	    try {
		    if (!error.isEmpty()) {
		        System.out.println("Verified. Error Message Present!");
		        result = "Pass. Verified. Error Message Present!";
		        ExcelUtil.fillGreenColor(path, sheet, 1, 13);
		    } else {
		        System.out.println("No error message found.");
		        result = "Fail. No error message found.";
		        ExcelUtil.fillRedColor(path, sheet, 1, 13);
		    }
	        ExcelUtil.setCellData(path, sheet, 1, 12, error);  
	        ExcelUtil.setCellData(path, sheet, 1, 13, result); 
	    } catch (IOException e) {
	        System.out.println("Error writing error message to Excel: " + e.getMessage());
	    }

	    return error;
	}


}
