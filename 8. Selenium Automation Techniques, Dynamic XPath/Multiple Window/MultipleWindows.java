import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Point;

public class MultipleWindows { //Do not change the class name
    
    //Use these declarations to store respective values
    static String parentWinHandle,parentWinTitle;
    static String childWinHandle,childWinTitle;
    Set<String> winHandles;
    
    static WebDriver driver;
    String baseUrl = "https://webapps.tekstac.com/SeleniumApp1/TrainReservation/login.html";
    
	
	public WebDriver createDriver() { //Do not change the method signature
	
	    // Invoke the getWebDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    
	    driver=DriverSetup.getWebDriver();
	    // Please do NOT remove the below URL navigation code
	    driver.get(baseUrl);
		
		return driver;
	}

    public String getParentWindow() throws Exception { //Do not change the method signature
        
        // Get  Parent Window Handle as string and return it
        //Get the page title and store it in 'parentWinTitle'
        try{
            parentWinHandle=driver.getWindowHandle();
            parentWinTitle=driver.getTitle();
            System.out.println(parentWinTitle);
            driver.findElement(By.linkText(("Signup Using Google"))).click();
        }
        catch(Exception e){
            System.out.println("Invalid");
        }
        return parentWinTitle;

    }
    
    public String  getChildWindow() throws Exception { //Do not change the method signature
        
        // Click the href link. Find the child window's handle and return it.
        //Hint: WAIT for child page to load and find the child window handle.
        //Get the page title and store it in 'childWinTitle'
        try{
            winHandles=driver.getWindowHandles();
            for(String handle:winHandles){
                if(!handle.equals(parentWinHandle)){
                    childWinHandle=handle;
                    driver.switchTo().window(childWinHandle);
                    childWinTitle=driver.getTitle();
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("Error");
        }
        System.out.println(childWinTitle);
        return childWinTitle;
    }
    
      
    
    public static void main(String[] args) throws Exception { //Do not change the method signature
	    MultipleWindows win=new MultipleWindows();
	    //Implement code here
	    win.createDriver();
	    win.getParentWindow();
	    win.getChildWindow();
	   
	}
}