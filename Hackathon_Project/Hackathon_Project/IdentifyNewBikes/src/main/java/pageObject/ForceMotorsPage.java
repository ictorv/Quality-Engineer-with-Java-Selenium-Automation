package pageObject;
 
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class ForceMotorsPage extends BasePage {
	
	JavascriptExecutor js=(JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	String mapUrl;
	
	public ForceMotorsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='zwn-search']/div[1]/div[6]/ul/li/div/div[4]/div[2]/span")
	WebElement location;
	
	//Get direction and Location of Service Center
	public void location() {
		js.executeScript("arguments[0].click();", location);
        
        Set<String> windows = driver.getWindowHandles();
        List<String> windowList = new ArrayList<>(windows);
 
        driver.switchTo().window(windowList.get(1));
        
        mapUrl=driver.getCurrentUrl();
        System.out.println(mapUrl);
	}

	public String url() {
		return mapUrl;
	}
}
 
 