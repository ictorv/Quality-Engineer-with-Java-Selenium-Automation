package com.HandlingAlertConfirmPrompt;

//Importing the Necessary Packages for Functionality
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import com.Screenshot.Screenshot;

//The Class handling the Normal Alert
public class HandlingAlertWithOk {
	
	//Defining the variables
	public WebDriver driver;

	//Constructor for Initializing the driver and PageFactory
	public HandlingAlertWithOk(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Initializing the XPaths using FindBy annotation with PageFactory
	@FindBy(xpath = "//div[contains(@class,'col-md-12')]/div/div[2]/div/button[contains(@class,'btn btn')]")
	WebElement alertBox;
	@FindBy(linkText = "Alert with OK")
	WebElement alertLink;

	//Handling Alert Main Function
	public String handleAlert() {
		String outputText="";
		try {

			String fileName = "Handling_Normal_Alert";
			
			//Creating Wait Object to wait the operation for till the Locator is find out.
			Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
					.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
			
			//Clicking the Button
			alertLink.click();
			System.out.println("Clicked on the link named as \"Alert with OK\".");
			
			//Clicking the Normal Alert Box (OK) Button
			alertBox.click();
			System.out.println("Clicked on the \"click the button to display an alert box:\".");
			
			//Waiting whether the alert is Present or Not
			wait.until(ExpectedConditions.alertIsPresent());
			
			//Creating the Alert's object to perform the Alert actions and driver is switching the cursor to alert
			Alert alert1 = driver.switchTo().alert();
			
			//Storing the alert text
			outputText = alert1.getText();		
			System.out.println("Alert is appeared successfully and the text in Alert box is: "+outputText);
			
			//Accepting the Alert box using accept()
			alert1.accept();
			
			//Taking ScreenShot
			Screenshot.ScreenShot(driver, fileName);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (NoAlertPresentException e) {
            System.out.println("No alert appeared!");
        }
		finally {
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println();
		}
		return outputText;

	}
}
