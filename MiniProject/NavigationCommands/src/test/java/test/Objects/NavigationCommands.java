package test.Objects;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationCommands {

	 WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public NavigationCommands(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js=(JavascriptExecutor)driver;
        PageFactory.initElements(driver,this);
    }

    
    
    
	
	  @FindBy(id="sb_form_q") WebElement srchbx;
	  
	  @FindBy(xpath="(//div[text()='OrangeHRM'])[1]") WebElement orangeHrm;
	  
	  @FindBy(xpath="//a[normalize-space()='OrangeHRM, Inc']") WebElement
	  orangeHrmInc;
	 
    
    public void pageUrl(String searchValue) throws InterruptedException 
    {
    	srchbx.sendKeys(searchValue);
    	srchbx.submit();
    	driver.navigate().back();
    	driver.navigate().forward();
    	String mainWindow=driver.getWindowHandle();
    	wait.until(ExpectedConditions.visibilityOf(orangeHrm));
    	new Actions(driver).moveToElement(orangeHrm).click().perform();
    	
    	
    	Set<String> allWindows=driver.getWindowHandles(); 
    	String secondWindow=null;
		  for(String window:allWindows) { 
			  if(!window.equals(mainWindow)) 
			  {
				  driver.switchTo().window(window); 
				  secondWindow=window; 
				  break;
				  } 
			  }
		  
		  
		
		  wait.until(ExpectedConditions.elementToBeClickable(orangeHrmInc));
		  js.executeScript("arguments[0].scrollIntoView(true);",orangeHrmInc);
		  orangeHrmInc.click();
		                                                       
		  Set<String>windows=driver.getWindowHandles();
		  for(String handle:windows) 
		  { 
			  if(!handle.equals(mainWindow)&&!handle.equals(secondWindow)) 
			  {
				 driver.switchTo().window(handle);
				 break; 
			  }
		  }
    }
}
