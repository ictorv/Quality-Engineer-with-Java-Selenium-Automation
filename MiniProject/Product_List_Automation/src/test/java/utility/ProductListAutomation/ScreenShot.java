package utility.ProductListAutomation;



import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot 
{
	public static String filepath = "./TestScreenshots/";
	public static String screenShotTC(WebDriver scdriver,String fileName)throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		Date date = new Date();
		File src = ((TakesScreenshot) scdriver).getScreenshotAs(OutputType.FILE);
		String destination = filepath + fileName + "_" + dateFormat.format(date) + ".png";
		
		File dest = new File(destination);
		try 
		{
			
			FileHandler.copy(src, dest);
			return destination;
 
		} 
		catch (IOException e) 
		{
			throw new RuntimeException("Screenshot capture failed: " + e.getMessage());
		}
	}
}
