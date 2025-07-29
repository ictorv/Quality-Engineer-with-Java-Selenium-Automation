package utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {
	public static String filepath = "./screenshots/";

	public static String screenShotTC(WebDriver scdriver, String fileName) {
		//ExtentReportManager extentTest = new ExtentReportManager();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss a z");
		Date date = new Date();
		File src = ((TakesScreenshot) scdriver).getScreenshotAs(OutputType.FILE);
		String destination = filepath + fileName + "_" + dateFormat.format(date) + ".png";
		//String reportimg = "./screenshots/"
		File dest = new File(destination);
		try { 
				// screenshot to desired location using copyFile method
				// FileUtils.copyFile(src, new)
				// File(filepath+fileName+"_"+dateFormat.format(date)+".png")
			FileHandler.copy(src, dest);
			return destination;

		} catch (IOException e) {
			throw new RuntimeException("Screenshot capture failed: " + e.getMessage());
		}
	}

}
