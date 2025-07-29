package objectRepository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityFiles.ExcelUtil;
import utilityFiles.WaitUtil;

public class SearchResultPage {
	WebDriver driver;
	
	
	public SearchResultPage(WebDriver driver){
		this.driver = driver;
		
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//div[@class='q-box qu-borderBottom qu-pt--small qu-pb--small qu-bg--raised']//div[@class='q-text qu-dynamicFontSize--regular qu-medium qu-color--gray_dark qu-passColorToLinks']")
	WebElement actualText;
	
	@FindBy(xpath = "/html/body/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div[2]")
	WebElement profileIcon;
	
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div/div[3]/div[2]/div/div/div/div/div")
	WebElement logOut;
	
	/*
	 * public void titleVerification(String expectedText, String expectedUrl) {
	 * String currentUrl = driver.getCurrentUrl();
	 * System.out.println(actualText.getText());
	 * if(expectedText.equals(actualText.getText()) &&
	 * expectedUrl.equals(currentUrl) ) {
	 * System.out.println("Verification for Search Result Page titled \" "
	 * +expectedText+" \" is successful"); } else {
	 * System.out.println("Verification for Search Result Page titled \" "
	 * +expectedText+" \" is not successful"); } }
	 */
	
	public void titleVerification(String expectedText, String expectedUrl) {
	    String currentUrl = driver.getCurrentUrl();
	    String actual = actualText.getText();
	    String result;
	    String sheet = "dataSheet";
    	String path = "src/test/resources/testData/testData.xlsx";
	    System.out.println(actual);
	    try {
	    	if (expectedText.equals(actual) && expectedUrl.equals(currentUrl)) {
		        result = "Pass.Page Verified";
		        System.out.println("Verification for Search Result Page titled \"" + expectedText + "\" is successful");
		        ExcelUtil.fillGreenColor(path, sheet, 1, 8);
		    } else {
		        result = "Fail";
		        System.out.println("Verification for Search Result Page titled \"" + expectedText + "\" is not successful");
		        ExcelUtil.fillRedColor(path, sheet, 1, 8);
		    }

	        ExcelUtil.setCellData(path, sheet, 1, 6, actual);      // Actual Text
	        ExcelUtil.setCellData(path, sheet, 1, 7, currentUrl);  // Actual URL
	        ExcelUtil.setCellData(path, sheet, 1, 8, result);     // Result
	    } catch (IOException e) {
	        System.out.println("Error writing result to Excel: " + e.getMessage());
	    }
	}
	
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div/div[1]/a/div/div[2]/div/span/span/div/span/span")
	WebElement check;
	
	@FindBy(xpath="//div[contains(@class,'q-flex qu-flexDirection--column qu-bg--raised')]/div")
	List<WebElement> toScroll;

	public void clickProfile() throws TimeoutException {
		driver.navigate().refresh();
		WaitUtil.waitForVisibility(driver, profileIcon, 10);
		profileIcon.click();
		
		WaitUtil.waitForVisibility(driver, check, 10);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for(WebElement ele : toScroll) {
			js.executeScript("arguments[0].scrollIntoView(false);", ele);
			
		}
		
	}
	
	public void clickLogout() {
		logOut.click();
	}
}
