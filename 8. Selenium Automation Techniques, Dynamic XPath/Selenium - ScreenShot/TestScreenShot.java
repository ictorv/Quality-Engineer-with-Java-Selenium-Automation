
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//Do not change the class name
public class TestScreenShot { 

    //Use the below declared variable
	private static WebDriver driver;
	private static File src=null;
    private static String baseUrl = "https://webapps.tekstac.com/Shopify/";
	
	public TestScreenShot() {}
	public TestScreenShot(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public static WebDriver createDriver()
	{
	   //Create driver,store it in static variable driver, navigate to baseUrl and return it
	   driver=DriverSetup.getDriver();
	   driver.get(baseUrl);
	   return driver;
	}
	public static File takeScreenShot() throws IOException
	{
	    //write the code to take Screenshot
	    //take the screenshot and stored in src variable and return it.
	    
	    TakesScreenshot sc=(TakesScreenshot)driver;
	    File ss=sc.getScreenshotAs(OutputType.FILE);
      return ss;
	}

	public static void main(String[] args) throws IOException {
	
		// 'main' method is NOT evaluated. You can have your own implementation
		
	}
}
