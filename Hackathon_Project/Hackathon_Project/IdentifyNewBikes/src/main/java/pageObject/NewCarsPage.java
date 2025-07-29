package pageObject;
 
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class NewCarsPage extends BasePage {
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public NewCarsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='zw-cmnSilder']/div/div/div[1]/ul/li/a/div")
	List<WebElement> quickResearchElement;	
	
	//goto quick research and select service centers
	public void serviceCenters(String serviceCenter ) {
		for(WebElement qR:quickResearchElement) {
        	String option=qR.getText();
        	if(option.contains(serviceCenter)) {
        		js.executeScript("arguments[0].click();",qR);
        		break;
        	}
     
        }		
	}
	
	//goto quick research and select emi calculator
	public void emiCalculator(String emiCalculator) {
		for(WebElement qR:quickResearchElement) {
        	String option=qR.getText();
        	if(option.contains(emiCalculator)) {
        		js.executeScript("arguments[0].click();",qR);
        		break;
        	}
		}
	
	}
}
 
 